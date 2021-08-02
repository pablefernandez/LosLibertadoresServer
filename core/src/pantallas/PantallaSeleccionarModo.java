package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import com.badlogic.gdx.utils.viewport.FitViewport;

import utiles.Constantes;
import utiles.Imagen;
import utiles.Recursos;
import utiles.Render;
import utiles.Utiles;

public class PantallaSeleccionarModo extends PantallaBase {
	
	public PantallaSeleccionarModo() {
		ventanaGrafica = new FitViewport(Constantes.V_WIDTH, Constantes.V_HEIGHT);
		stage = new Stage();
		fondo = new Imagen(Recursos.FONDOMENU,Constantes.V_WIDTH,Constantes.V_HEIGHT);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		Skin skin = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
		TextButton solitario = new TextButton("Solitario", skin);
		TextButton cooperativo = new TextButton("Cooperativo", skin);
		TextButton volver = new TextButton("Volver", skin);
		table.add(solitario).fillX().uniformX().height(100);
		table.row().pad(10,0,10,0);
		table.add(cooperativo).fillX().uniformX().height(100);
		table.row().pad(10, 0, 10, 0);
		table.add(volver).fillX().uniformX().height(100);
		
		solitario.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Utiles.prueba.cambiarPantalla(new Pantalla());			
			}
		});
		
		volver.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Utiles.prueba.cambiarPantalla(Utiles.menu);		
			}
		});
		
		cooperativo.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Constantes.cooperativo = true;
				Utiles.prueba.cambiarPantalla(new Pantalla());			
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
	}
	
	@Override
	public void dispose() {
		
	}
	
}
