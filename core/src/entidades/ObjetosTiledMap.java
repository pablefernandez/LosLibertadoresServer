package entidades;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import utiles.Constantes;

public class ObjetosTiledMap {
	
	public ObjetosTiledMap(World world,TiledMap map) {
			BodyDef bDef = new BodyDef();
			PolygonShape shape = new PolygonShape();
			FixtureDef fdef = new FixtureDef();
			for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
				Rectangle rect = ((RectangleMapObject) object).getRectangle();
				bDef.type = BodyDef.BodyType.StaticBody; // Defino el tipo del cuerpo en estatico
				bDef.position.set((rect.getX() + rect.getWidth() / 2) / Constantes.PPM,
						(rect.getY() + rect.getHeight() / 2) / Constantes.PPM); // Defino la posicion del cuerpo
				Body body = world.createBody(bDef); //
				shape.setAsBox(rect.getWidth() / 2 / Constantes.PPM, rect.getHeight() / 2 / Constantes.PPM);
				fdef.shape = shape;
				fdef.filter.categoryBits = Constantes.BIT_SUELO;
				fdef.filter.maskBits = Constantes.BIT_PLAYER | Constantes.BIT_BALA | Constantes.BIT_ENEMIGO | Constantes.BIT_POWER_UP;
				Fixture fixture = body.createFixture(fdef);
				fixture.setUserData("suelo");
		}
		
	}
}
