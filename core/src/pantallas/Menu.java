package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import utiles.Constantes;
import utiles.Imagen;
import utiles.Recursos;
import utiles.Render;
import utiles.Utiles;

public class Menu extends PantallaBase {
	public Menu() {
		ventanaGrafica = new StretchViewport(Constantes.V_WIDTH,Constantes.V_HEIGHT);
		stage = new Stage(ventanaGrafica);
//		Gdx.input.setInputProcessor(stage);
		fondo = new Imagen(Recursos.FONDOMENU,Constantes.V_WIDTH,Constantes.V_HEIGHT);
		Utiles.menu = this;
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		Skin skin = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
		Label titulo = new Label("Los",skin,"title");
		Label titulo2 = new Label("Libertadores",skin,"title");
		titulo.setPosition(Constantes.V_WIDTH / 2 - (titulo.getWidth() / 2), Constantes.V_HEIGHT - 100);
		titulo2.setPosition(Constantes.V_WIDTH / 2 - (titulo2.getWidth() / 2),titulo.getY() - titulo.getHeight() - 10);
		stage.addActor(titulo);
		stage.addActor(titulo2);
		TextButton nuevaPartida = new TextButton("Nueva Partida", skin);
		TextButton configuracion = new TextButton("Configuraciones", skin);
		TextButton salir = new TextButton("Salir", skin);
		table.add(nuevaPartida).fillX().uniformX().height(100);
		table.row().pad(10,0,10,0);
		table.add(configuracion).fillX().uniformX().height(100);
		table.row();
		table.add(salir).fillX().uniformX().height(100);
		
		nuevaPartida.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Utiles.prueba.cambiarPantalla(new PantallaSeleccionarModo());			
			}
		});
		
		salir.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();			
			}
		});
		
		configuracion.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Utiles.prueba.cambiarPantalla(((Utiles.preferenciasMenu != null)?Utiles.preferenciasMenu : new PreferenciasMenu()));			
			}
		});
		
	}
	@Override
	public void render(float delta) {
		super.render(delta);
		Render.batch.begin();
		fondo.dibujar();
		Render.batch.end();
		stage.act();
		stage.draw();	
//		stage.setDebugAll(true);
	}


}
