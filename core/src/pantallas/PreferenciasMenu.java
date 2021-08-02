package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import utiles.Constantes;
import utiles.Imagen;
import utiles.Recursos;
import utiles.Render;
import utiles.SettingManager;
import utiles.Utiles;

public class PreferenciasMenu extends PantallaBase {
	private Label tituloLabel;
	private Label volumenMusicaLabel;
	private Label volumenSonidosLabel;
	private Label musicaOnOffLabel;
	private Label sonidosOnOffLabel;
	
	public PreferenciasMenu() {
		ventanaGrafica = new FitViewport(Constantes.V_WIDTH,Constantes.V_HEIGHT);
		fondo = new Imagen(Recursos.FONDOMENU,Constantes.V_WIDTH,Constantes.V_HEIGHT);
		stage = new Stage();
//		Gdx.input.setInputProcessor(stage);
		Utiles.preferenciasMenu = this;
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		Skin skin = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
		tituloLabel = new Label("Preferencias",skin);
		volumenMusicaLabel = new Label("Musica Volumen",skin);
		volumenSonidosLabel = new Label("Sonido Volumen",skin);
		musicaOnOffLabel = new Label("Musica",skin);
		sonidosOnOffLabel = new Label("Sonido",skin);
		final Slider volumenMusicaSlider = new Slider(0f,1f,0.1f,false, skin); // minimo - maximo - cuanto aumenta - vertical
		volumenMusicaSlider.setValue(SettingManager.getMusicaVolumen());
		volumenMusicaSlider.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				SettingManager.setMusicaVolumen(volumenMusicaSlider.getValue());
				return false;
			}
		});
		final Slider volumenSonidoSlider = new Slider(0f,1f,0.1f,false, skin);
		volumenSonidoSlider.setValue(SettingManager.getSonidosVolumen());
		volumenSonidoSlider.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				SettingManager.setSonidosVolumen(volumenSonidoSlider.getValue());
				return false;
			}
		});
		final CheckBox musicaCheckbox = new CheckBox(null, skin);
		musicaCheckbox.setChecked(SettingManager.isMusicaActivada());
		musicaCheckbox.addListener(new EventListener() {
		   	@Override
			public boolean handle(Event event) {
		       	boolean enabled = musicaCheckbox.isChecked();
		       	SettingManager.setMusicaActivada(enabled);
		       	return false;
			}
		});
		final CheckBox sonidoCheckbox = new CheckBox(null, skin);
		sonidoCheckbox.setChecked(SettingManager.isSonidosActivados());
		sonidoCheckbox.addListener(new EventListener() {
		   	@Override
			public boolean handle(Event event) {
		       	boolean enabled = sonidoCheckbox.isChecked();
		       	SettingManager.setSonidosActivados(enabled);;
		       	return false;
			}
		});
		final TextButton botonAtras = new TextButton("Volver", skin); // the extra argument here "small" is used to set the button to the smaller version instead of the big default version
		botonAtras.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Utiles.prueba.cambiarPantalla(Utiles.menu);
			}
		});
		int top = 50,right = 50;
		table.add(tituloLabel).colspan(2);
		table.row().pad(100,0,0,right);
		table.add(volumenMusicaLabel).left();
		table.add(volumenMusicaSlider);
		table.row().pad(top,0,0,right);
		table.add(musicaOnOffLabel).left();
		table.add(musicaCheckbox);
		table.row().pad(top,0,0,right);
		table.add(volumenSonidosLabel).left();
		table.add(volumenSonidoSlider);
		table.row().pad(top,0,0,right);
		table.add(sonidosOnOffLabel).left();
		table.add(sonidoCheckbox);
		table.row().pad(top,0,0,right);
		table.add(botonAtras).colspan(2);
	}
	
	@Override
	public void hide() {
		stage.clear();
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
