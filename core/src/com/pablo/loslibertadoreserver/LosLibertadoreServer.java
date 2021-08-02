package com.pablo.loslibertadoreserver;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import pantallas.Menu;
import pantallas.PantallaSeleccionarModo;
import utiles.Utiles;

public class LosLibertadoreServer extends Game {

	
	@Override
	public void create () {
		Utiles.prueba = this;
		this.setScreen(new PantallaSeleccionarModo());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	
	}
	
	public void cambiarPantalla(Screen pantalla) {
		this.setScreen(pantalla);
	}
}
