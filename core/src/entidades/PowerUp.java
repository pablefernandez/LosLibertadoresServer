package entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import utiles.Constantes;
import utiles.Render;

public abstract class PowerUp extends ObjetoBox2D {
	final int cantidadMaxima = 1;
	protected Texture textura;
	protected boolean destruir = false;
	protected boolean setDestruir = false;
	
	public PowerUp(Vector2 posicion,float width,float height) {
		super(posicion);
		this.width = width;
		this.height = height;
		crearObjetoBox2D();
		
		
	}
	public PowerUp() {
		super();
	}
	
	@Override
	public void crearObjetoBox2D() {
		bodyDef.position.set(posicion);
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2,height / 2);
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = Constantes.BIT_POWER_UP;
		fixtureDef.filter.maskBits = Constantes.BIT_SUELO | Constantes.BIT_PLAYER;
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData(this);
	}
	public void update() {
		if(setDestruir && !destruir) {
			destruir = true;
			world.destroyBody(body);
		}
	}
	
	public void render(float delta) {
		if(!destruir) {
			Render.batch.begin();
				Render.batch.draw(textura,body.getPosition().x - (width / 2), body.getPosition().y - (height / 2),width,height);
			Render.batch.end();
		}
	}
	
	public void setDestruir() {
		setDestruir = true;
	}
	
//	public boolean handleInput() {
//		if(Gdx.input.isKeyPressed(Keys.Z)) {
//			usarPowerUp();
//			return true;
//		}
//		return false;
//	}
	public abstract void usarPowerUp();
}
