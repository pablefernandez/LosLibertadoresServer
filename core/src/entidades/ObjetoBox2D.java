package entidades;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import utiles.Constantes;

public abstract class ObjetoBox2D {
	
	protected Body body;
	protected FixtureDef fixtureDef;
	protected BodyDef bodyDef;
	protected World world;
	protected Fixture fixture;
	protected Vector2 posicion;
	protected float width, height;
	public ObjetoBox2D(Vector2 posicion) {
		fixtureDef = new FixtureDef();
		bodyDef = new BodyDef();
		world = Constantes.world;
		this.posicion = posicion;
	}
	public ObjetoBox2D() {
		this(new Vector2(0,0));
	}
	public abstract void crearObjetoBox2D();
	
	public Body getBody() {
		return body;
	}
	
	public Vector2 getPosicion() {
		return body.getPosition();
	}
	
	public Vector2 getVelocidad() {
		return body.getLinearVelocity();
	}
	
}
