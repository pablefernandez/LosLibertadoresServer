package pantallas;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import entidades.Bala;
import entidades.MundoBox2D;
import enums.DireccionesPersonaje;
import enums.Estados;
import eventos.TeclasListener;
import red.HiloServidor;
import utiles.Constantes;
import utiles.Imagen;
import utiles.Recursos;
import utiles.Render;
import utiles.Utiles;

public abstract class PantallaJuego extends PantallaBase{
	protected OrthographicCamera camaraJuego; 
	protected MundoBox2D mundo;;
	protected float[] shooTimer = new float[2];
	private int[][] teclasClientes = new int[2][6];
	private HiloServidor hs;
	public PantallaJuego() {
		if(Constantes.cooperativo) {
			hs = new HiloServidor(this);
			Constantes.hs = hs;
			Constantes.hs.start();
			mundo = new MundoBox2D();
		}
	}
	
	@Override
	public void show() {
		camaraJuego = new OrthographicCamera(); 
		ventanaGrafica = new FitViewport(Constantes.V_WIDTH / Constantes.PPM, Constantes.V_HEIGHT / Constantes.PPM,camaraJuego); // seteo cuanto va ser el area de mi mundo que vea mi camara,aunque redimensione la ventana
		camaraJuego.position.set(ventanaGrafica.getWorldWidth() / 2, ventanaGrafica.getWorldHeight()/ 2, 0); 
		stage = new Stage();
		fondo = new Imagen(Recursos.FONDOMENU,Constantes.V_WIDTH,Constantes.V_HEIGHT);
		Skin skin = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
		Label mensaje= new Label("Esperando jugador",skin);
		mensaje.setPosition(Constantes.V_WIDTH / 2 - mensaje.getWidth() / 2, Constantes.V_HEIGHT / 2 - mensaje.getHeight() / 2);
		stage.addActor(mensaje);
	}
	
	public void handleInput(float dt) {
		for (int i = 0; i < teclasClientes.length; i++) {
			if (mundo.getPersonajes()[i].getEstados() != Estados.DEAD) {
				if (teclasClientes[i][0] == 1 && mundo.getPersonajes()[i].getBody().getLinearVelocity().y <= 3 && mundo.getContactListener().isJugadorEnSuelo()[i]) {
					mundo.getPersonajes()[i].salta();
				}
				if (teclasClientes[i][1] == 1 && mundo.getPersonajes()[i].getBody().getLinearVelocity().x <= 6) {
					mundo.getPersonajes()[i].moverDerecha(true);
				}
				if (teclasClientes[i][2] == 1 && mundo.getPersonajes()[i].getBody().getLinearVelocity().x >= -6) {
					mundo.getPersonajes()[i].moverDerecha(false);
				}
				if (teclasClientes[i][3] == 1) {
					mundo.getPersonajes()[i].miraArriba();
				}
				if (teclasClientes[i][4] == 1 && shooTimer[i] >= Constantes.cadenciaDisparoActual) {// mundo.getPersonaje().getCadenciaDisparo() 
					disparar(i);
				}
				if(teclasClientes[i][5] == 1 && mundo.getPersonajes()[i].isTienePowerUp()) {  
					mundo.getPersonajes()[i].getPowerUp().usarPowerUp();
					mundo.getPersonajes()[i].setTienePowerUp(false);
				}
			}
		}
	}

	private void disparar(int i) {
		shooTimer[i] = 0;
		Bala bala;
		if(i == 0) {
			bala = new Bala(mundo.getPersonajes()[i].getBody().getPosition(),mundo.getPersonajes()[i+1].getBody().getPosition());
		} else {
			bala = new Bala(mundo.getPersonajes()[i].getBody().getPosition(),mundo.getPersonajes()[i-1].getBody().getPosition());
		}	
		hs.enviarMensajeATodos("Crear*Bala*" + mundo.getPersonajes()[i].getBody().getPosition().x + "*" + mundo.getPersonajes()[i].getBody().getPosition().y + "*" + i);
		for (int j = 0; j < Utiles.listener.size(); j++) {
			try {
				TeclasListener tl = (TeclasListener) Utiles.listener.get(j);
				if (mundo.getPersonajes()[i].getDireccionPersonaje() == DireccionesPersonaje.DERECHA) {
					tl.right();
					

				} else if (mundo.getPersonajes()[i].getDireccionPersonaje() == DireccionesPersonaje.IZQUIERDA) {
					tl.left();

				} else if (mundo.getPersonajes()[i].getDireccionPersonaje() == DireccionesPersonaje.ARRIBA
						&& mundo.getPersonajes()[i].getBody().getLinearVelocity().x == 0) {
					tl.up();
					

				}
			} catch (Exception e) {
			}
		}

		bala.crearObjetoBox2D();
		mundo.getPersonajes()[0].getBalas().add(bala);
		
			
	}
	public void update(float delta) {
		for (int i = 0; i < shooTimer.length; i++) {
			shooTimer[i] += delta;
		}
		handleInput(delta);
		camaraJuego.update();
		mundo.update(delta, camaraJuego);
	
	}
	@Override
	public void render(float delta) {
		super.render(delta);
		if(Constantes.cooperativo && !Constantes.empiezaPartida) {
			Render.batch.begin();
			fondo.dibujar();
			Render.batch.end();
			stage.act();
			stage.draw();
		} else {
			update(delta);
			if(mundo.getPersonajes()[0].getBody().getPosition().x > mundo.getPersonajes()[1].getBody().getPosition().x) {
				camaraJuego.position.x = mundo.getPersonajes()[0].getBody().getPosition().x;
			} else {
				camaraJuego.position.x = mundo.getPersonajes()[1].getBody().getPosition().x;
			}
			hs.enviarMensajeATodos("Actualizar*Camara*" + camaraJuego.position.x);
			mundo.render(delta, camaraJuego);
			for (int i = 0; i < mundo.getPersonajes().length; i++) {
				mundo.getPersonajes()[i].render(delta);
				for (int j = 0; j < mundo.getPersonajes()[i].getBalas().size(); j++) {	
					Bala balaActual = mundo.getPersonajes()[i].getBalas().get(j);
					balaActual.update();
					if (balaActual.shouldRemove()) {
						mundo.getPersonajes()[i].getBalas().remove(j);
						Constantes.world.destroyBody(balaActual.getBody());
						hs.enviarMensajeATodos("Actualizar*" + i + "*Bala*Remover*" + balaActual.shouldRemove() + "*" + j);
						j--;
					}
					if (j >= 0 ) {
						balaActual.dibujar();
						hs.enviarMensajeATodos("Actualizar*" + i + "*Bala*Posicion*" + balaActual.getPosicion().x + "*"+ balaActual.getPosicion().y + "*" + j);
					}
				}
				hs.enviarMensajeATodos("Actualizar*" + i + "*Vida*" + mundo.getPersonajes()[i].getVidaActual());
				if(!mundo.getPersonajes()[i].isMuertoFinal()) {
					hs.enviarMensajeATodos("Actualizar*" + i + "*Estado*" + mundo.getPersonajes()[i].isMuertoTemporal());
					hs.enviarMensajeATodos("Actualizar*" + i + "*Posicion*" + mundo.getPersonajes()[i].getPosicion().x + "*" + mundo.getPersonajes()[i].getPosicion().y);
					hs.enviarMensajeATodos("Actualizar*" + i + "*Velocidad*" + mundo.getPersonajes()[i].getVelocidad().x + "*" + mundo.getPersonajes()[i].getVelocidad().y);
				} else {
					hs.enviarMensajeATodos("Actualizar*" + i + "*Dibujar");
				}
			}	
			Render.batch.setProjectionMatrix(camaraJuego.combined);
			Constantes.shoot = false;
		}
			
	}
		
	public int[][] getTeclasClientes() {
		return teclasClientes;
	}
	
	@Override
	public void dispose() {
		
	}
	
	public MundoBox2D getMundo() {
		return mundo;
	}
	
}
