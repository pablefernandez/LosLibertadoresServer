package entidades;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import utiles.Constantes;
import utiles.MiContactListener;

public class MundoBox2D {
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private Box2DDebugRenderer lineas;
	private Personaje[] personajes = new Personaje[2];
	private MiContactListener cl;
	private int levelWidth = 0, levelHeight = 0, tileWidth = 0, tileHeight = 0;
	private float timer = 4;
	private int iflag = 1;
	private Oleada[] oleadas;
	private boolean bandera;
	public MundoBox2D() {
		map = new TmxMapLoader().load("mapas2/Mapita2.tmx"); 
		renderer = new OrthogonalTiledMapRenderer(map, 1 / Constantes.PPM);
		Constantes.world = new World(new Vector2(0,-9.81f), true);
		MapProperties propiedades = map.getProperties();
		levelWidth = propiedades.get("width",Integer.class);
		levelHeight = propiedades.get("height", Integer.class);
		tileWidth = propiedades.get("tilewidth",Integer.class);
		tileHeight = propiedades.get("tileheight", Integer.class);
		cl = new MiContactListener();
		Constantes.world.setContactListener(cl);
		lineas = new Box2DDebugRenderer();
		new ObjetosTiledMap(Constantes.world, map);
		for (int i = 0; i < personajes.length; i++) {
			personajes[i] = new PersonajeAzul(i);
		}

		oleadas = new Oleada[9];
	
		for (int i = 0; i < oleadas.length; i++) {
			oleadas[i] = new Oleada();
			oleadas[i].setEnemigos(3);	
		}

	}
	
	public void cargarMapa(String fuente) {
		map = new TmxMapLoader().load(fuente);
	}
	
	public void update(float delta,OrthographicCamera camara) {
		renderer.setView(camara);
	}
	
	public void render(float delta,OrthographicCamera camara) {

		Constantes.world.step(1/60f,6,2);
		if (!bandera) {
			Constantes.hs.enviarMensajeATodos("Cantidad*Oleadas*" + oleadas.length + "*" + oleadas[0].getEnemigos().length + "*" + 0);
			if (personajes[0].getPosicion().x >= personajes[1].getPosicion().x) {
				oleadas[0].setPosicion(personajes[0].getPosicion());
			} else {
				oleadas[0].setPosicion(personajes[1].getPosicion());	

			}
			oleadas[0].crearEnemigos(0);
			bandera = true;
		}

		if(timer >= 0) {
			timer -= delta;
			if(timer <= 0 && iflag < oleadas.length) {
				if(personajes[0].getPosicion().x >= personajes[1].getPosicion().x) {
					oleadas[iflag].setPosicion(personajes[0].getPosicion());
				} else {
					oleadas[iflag].setPosicion(personajes[1].getPosicion());
				}
				Constantes.hs.enviarMensajeATodos("Cantidad*Oleadas*" + oleadas.length + "*" + oleadas[iflag].getEnemigos().length + "*" + iflag);
				oleadas[iflag].crearEnemigos(iflag);
				timer = 4;
				iflag++;
			}
		}

		for (int i = 0; i < iflag; i++) {
			
			oleadas[i].update(delta,i);
		}

			
		
		renderer.render();
		lineas.render(Constantes.world, camara.combined);
		
		for (int i = 0; i < iflag; i++) {
			oleadas[i].render(delta);
		}
//		for (PowerUp power : powerUps) {
//			power.render(delta);
//		}
		for (int i = 0; i < personajes.length; i++) {
			if(personajes[i].isMuertoFinal()) {
				personajes[i].getBody().setActive(false);
			}
		}
		
	}
	public Personaje[] getPersonajes() {
		return personajes;
	}
	public MiContactListener getContactListener() {
		return cl;
	}
	public int getLevelHeight() {
		return levelHeight;
	}
	public int getLevelWidth() {
		return levelWidth;
	}
	public int getTileHeight() {
		return tileHeight;
	}
	public int getTileWidth() {
		return tileWidth;
	}
}
