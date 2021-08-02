package utiles;

import com.badlogic.gdx.physics.box2d.World;

import red.HiloServidor;

public abstract class Constantes {
	public static final float PPM = 32f;
	public static final short BIT_ENEMIGO = 1;
	public static final short BIT_SUELO = 2;
	public static final short BIT_PLAYER = 4;
	public static final short BIT_BALA = 8;
	public static final short BIT_POWER_UP = 16;
	public static World world;
	public static int V_WIDTH = 1600;
	public static int V_HEIGHT = 900;
	public static final float WIDTH_CUERPO_BOX2D= 1f;
	public static final float HEIGHT_CUERPO_BOX2D= 2.7f;
	public static boolean shoot = false;
	public static int tic;
	public static boolean prueba = false;
	public static boolean termina = false;
	public static float cadenciaDisparoActual = 0.3f;
	//RED
	public static boolean empiezaPartida = false;
	public static boolean cooperativo = false;
	public static boolean personaje1 = false;
	public static HiloServidor hs;
}
