package utiles;

import java.util.ArrayList;
import java.util.EventListener;

import com.pablo.loslibertadoreserver.LosLibertadoreServer;
import pantallas.Menu;
import pantallas.PreferenciasMenu;

public abstract class Utiles {
	public static LosLibertadoreServer prueba;
	public static Menu menu;
	public static PreferenciasMenu preferenciasMenu;
	public static ArrayList<EventListener> listener = new ArrayList<EventListener>();
}
