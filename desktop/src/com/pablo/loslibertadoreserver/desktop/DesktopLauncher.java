package com.pablo.loslibertadoreserver.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pablo.loslibertadoreserver.LosLibertadoreServer;

import utiles.Constantes;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SERVER";
		config.resizable = true;
		config.width = Constantes.V_WIDTH;
		config.height = Constantes.V_HEIGHT;
		config.fullscreen = false;
		new LwjglApplication(new LosLibertadoreServer(), config);
	}
}
