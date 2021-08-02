package entidades;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import enums.Estados;
import utiles.Animacion;
import utiles.Constantes;
import utiles.Recursos;
import utiles.Render;

public class Zombie extends Enemigo {
	private Animacion animacionRun,animacionIdle,animacionActual;
	private Estados estadoActual = Estados.STANDING;
	private static final int MAX_SPEED = 8;
	
	public Zombie(Vector2 posicion) {
		super(1,3,6,posicion);
		animacionRun = new Animacion(Recursos.ANIMACION_RUN_ENEMIGO,5,width,height);
		animacionIdle = new Animacion(Recursos.ANIMACION_IDLE_ENEMIGO,1,2,6);
		animacionRun.setDelay(1/10f);
	}
	
	public void update() {
		super.update();
		if(!destruir) {
			if(body.getLinearVelocity().x >= -MAX_SPEED) {
				if(Constantes.prueba) {
					body.setLinearVelocity(new Vector2(0,0));
				} else {
					body.applyLinearImpulse(new Vector2(-1f, 0), body.getWorldCenter(), true);
				}
			}
		}
	}
	
	private TextureRegion getAnimacionActual(float delta) {
		estadoActual = getEstadoss();
		TextureRegion region = null;
		switch (estadoActual) {
		case RUNNING:
			animacionActual = animacionRun;
			animacionActual.setDelay(1f / 8f);
			region = animacionActual.getFrame();
			break;
		case STANDING:
			animacionActual = animacionIdle;
			region = animacionActual.getFrame();
			break;
			default:
				animacionActual = animacionIdle;
				break;
		}
		return region;
	}
	@Override
	public void render(float delta) {
		if(!destruir) {
			TextureRegion currentFrame = getAnimacionActual(delta);
			if(!currentFrame.isFlipX()) {
				currentFrame.flip(true, false);
			}
			Render.batch.begin();
				Render.batch.draw(currentFrame,body.getPosition().x - (animacionActual.getWidth() / 2),body.getPosition().y - (animacionActual.getHeight()/ 2),animacionActual.getWidth(),animacionActual.getHeight());
			Render.batch.end();
			animacionActual.update(delta);
		}
	}
	
	private Estados getEstadoss() {
		if (body.getLinearVelocity().x != 0) return Estados.RUNNING;
		else return Estados.STANDING;
	}
	
	@Override
	public void crearObjetoBox2D() {
//		world.QueryAABB(callback, lowerX, lowerY, upperX, upperY);
		bodyDef.position.set(posicion);
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Constantes.WIDTH_CUERPO_BOX2D, Constantes.HEIGHT_CUERPO_BOX2D);
		fixtureDef.shape = shape;
//		fixtureDef.restitution = 1f;
		fixtureDef.filter.categoryBits = Constantes.BIT_ENEMIGO;
		fixtureDef.filter.maskBits = Constantes.BIT_SUELO | Constantes.BIT_BALA | Constantes.BIT_PLAYER;
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData(this);
	}
	
}
