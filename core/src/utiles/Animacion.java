package utiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//public class Animacion {
//	private int filasSprite = 2 ,columnasSprite = 5;
//	private float width = 4, height = 6.3f;
//	private TextureRegion[] vector;
//	private Animation<TextureRegion> animacion;
//	private String ruta;
////	private float tiempoTranscurrido;
//	
//	public Animacion(String ruta, int filasSprite, int columnasSprite, float width, float height) {
//		this.filasSprite = filasSprite;
//		this.columnasSprite = columnasSprite;
//		this.ruta = ruta;
//		this.width = width;
//		this.height = height;
//		crearAnimacion();
//	}
//	public Animacion(String ruta, int filasSprite, int columnasSprite) {
//		this(ruta,filasSprite,columnasSprite,4,6.3f);
//	}
//	public Animacion(String ruta) {
//		this(ruta, 1, 1);
//	}
//	private void crearAnimacion() {
//		Texture textura = new Texture(ruta);
//		TextureRegion[][] matriz = TextureRegion.split(textura, textura.getWidth() / columnasSprite, textura.getHeight() / filasSprite);
//		vector = new TextureRegion[matriz.length*matriz[0].length];
//		int indice = 0;
//		for (int i = 0; i < matriz.length; i++) {
//			for (int j = 0; j < matriz[i].length; j++) {
//				vector[indice++] = matriz[i][j];
//			}
//		}
//		setAnimacion(1f/15f);
//	}
////	public boolean busqueda() {
////		for (int i = 0; i < vector.length; i++) {
////			if(getFrame(tiempoTranscurrido,false) == vector[i]) {
////				if(i == vector.length - 1) return true;
////			}
////		}
////		return false;
////	}
//	public void setAnimacion(float delay) {
//		animacion = new Animation<TextureRegion>(delay,vector);
//	}
//	public TextureRegion getFrame(float tiempoTranscurrido, boolean repite) { 
////		this.tiempoTranscurrido = tiempoTranscurrido;
////		busqueda();
//		return animacion.getKeyFrame(tiempoTranscurrido, repite);
//	}
//	
//	public float getHeight() {
//		return height;
//	}
//	public float getWidth() {
//		return width;
//	}
//	public void setHeight(float height) {
//		this.height = height;
//	}
//	
//	public void setWidth(float width) {
//		this.width = width;
//	}
//}
public class Animacion {
	private TextureRegion[] frames;
	private float tiempo;
	private float delay;
	private int currentFrame;
	private float width = 3f , height = 6;
	private int vecesEjecutada = 0;
	
	public Animacion(String ruta,int columnas,float width,float height) {
		Texture tex = new Texture(ruta);
		this.width = width;
		this.height = height;
		TextureRegion[] sprites = TextureRegion.split(tex, tex.getWidth() / columnas , tex.getHeight())[0];
		setFrames(sprites);
	}
	
	public Animacion(TextureRegion[] frames) {
		this(frames, 1 / 12f);
	}
	
	public Animacion(TextureRegion[] frames, float delay) {
		this.frames = frames;
		this.delay = delay;
		tiempo = 0;
		currentFrame = 0;
	}
	
	public void setDelay(float f) { delay = f; }
	public void setCurrentFrame(int i) { if(i < frames.length) currentFrame = i; }
	public void setFrames(TextureRegion[] frames) {
		setFrames(frames, 1 / 12f);
	}
	public void setFrames(TextureRegion[] frames, float delay) {
		this.frames = frames;
		tiempo = 0;
		currentFrame = 0;
		this.delay = delay;
	}
	
	public void update(float dt) {
		if(delay <= 0) return;
		tiempo += dt;
		while(tiempo >= delay) {
			ejecucion();
		}
	}
	
	private void ejecucion() {
		tiempo -= delay;
		currentFrame++;
		if(currentFrame == frames.length) {
			currentFrame = 0;
			vecesEjecutada++;
		}
	}
	
	public TextureRegion getFrame() { return frames[currentFrame]; }
	public int getVecesEjecutada() { return vecesEjecutada; }	
	
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
}
