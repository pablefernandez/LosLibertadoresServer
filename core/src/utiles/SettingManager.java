package utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public abstract class SettingManager {
	private static final String PREF_MUSICA_VOLUMEN = "musica_volumen";
	private static final String PREF_MUSICA_ACTIVADA = "musica_activada";
	private static final String PREF_SONIDOS_ACTIVADOS= "sonidos_activados";
	private static final String PREF_SONIDOS_VOLUMEN = "sonidos_volumen";
	private static final String PREF_NOMBRE = "LosLibertadores";
	private static Preferences preferencias = Gdx.app.getPreferences(PREF_NOMBRE);
	
	public static boolean isSonidosActivados() {
		return preferencias.getBoolean(PREF_SONIDOS_ACTIVADOS,true);
	}
	
	public static void setSonidosActivados(boolean sonidoActivado) {
		preferencias.putBoolean(PREF_SONIDOS_ACTIVADOS, sonidoActivado);
		preferencias.flush();
	}
	
	public static boolean isMusicaActivada() {
		return preferencias.getBoolean(PREF_MUSICA_ACTIVADA, true);
	}
	
	public static void setMusicaActivada(boolean musicaActivada) {
		preferencias.putBoolean(PREF_MUSICA_ACTIVADA, musicaActivada);
		preferencias.flush();
	}
	
	public static float getMusicaVolumen() {
		return preferencias.getFloat(PREF_MUSICA_VOLUMEN, 0.5f);
	}
	
	public static void setMusicaVolumen(float musicaVolumen) {
		preferencias.putFloat(PREF_MUSICA_VOLUMEN, musicaVolumen);
		preferencias.flush();
	}
	
	public static float getSonidosVolumen() {
		return preferencias.getFloat(PREF_SONIDOS_VOLUMEN, 0.5f);
	}
	
	public static void setSonidosVolumen(float sonidosVolumen) {
		preferencias.putFloat(PREF_SONIDOS_VOLUMEN, sonidosVolumen);
		preferencias.flush();
	}
}
