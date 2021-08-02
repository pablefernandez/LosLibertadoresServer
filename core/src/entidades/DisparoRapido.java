package entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import utiles.Constantes;
import utiles.Recursos;

public class DisparoRapido extends PowerUp {
	private float cadenciaDisparo = 0.1f;
	private float tiempo = 3;
	
	public DisparoRapido(Vector2 posicion) {
		super(posicion, 2, 2);
		textura = new Texture(Recursos.DISPARO_RAPIDO);
	}
	
	public DisparoRapido() {
		super();
	}

	@Override
	public void usarPowerUp() {
		System.out.println("USO POWER UP");
		Constantes.cadenciaDisparoActual = cadenciaDisparo;
	}
	
	public float getTiempo() {
		return tiempo;
	}
}
