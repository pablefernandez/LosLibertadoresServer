package utiles;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;

public class Imagen implements Disposable {
	private Texture t;
	private Sprite s;
	
	public Imagen(String ruta,float width,float heigth) {
		t = new Texture(ruta);
		s = new Sprite(t);
		s.setSize(width, heigth);
	}
	public void dibujar() {
		s.draw(Render.batch);
	}
	public void setTransparencia(float a) {
		s.setAlpha(a);
	}
	
	public void dispose() {
		t.dispose();
	}
	public Sprite getS() {
		return s;
	}
	
}
