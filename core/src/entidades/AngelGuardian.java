package entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import utiles.Recursos;

public class AngelGuardian extends PowerUp {
	private boolean shield = false; 
	public AngelGuardian(Vector2 posicion) {
		super(posicion,2,2);	
		textura = new Texture(Recursos.ANGEL_GUARDIAN);
	}
	
	public AngelGuardian() {
		super();
	}
	
	public boolean getShield() {
		shield = true;
		return shield;
	}

	@Override
	public void usarPowerUp() {
		return;
	}
	

}
