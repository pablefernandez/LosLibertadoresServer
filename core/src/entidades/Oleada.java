package entidades;


import com.badlogic.gdx.math.Vector2;

import utiles.Constantes;

public class Oleada {
	private Enemigo[] enemigos;
//	private Random r = new Random();
	private Vector2 posicion;
	private boolean bandera = false;
	private int distancia = 0;
	public Oleada() {
		
	}

	public void setEnemigos(int cantidad) {
		enemigos = new Enemigo[cantidad];
	
	}
	
	public void setPosicion(Vector2 posicion) {
		this.posicion = posicion;
	}
	
	public void crearEnemigos(int oleada) {	
		for (int i = 0; i < enemigos.length; i++) {
//			int rand = r.nextInt(2);
			// -((Constantes.V_WIDTH / 2) / Constantes.PPM - posicion.x + i)
			enemigos[i] = new Zombie(new Vector2((Constantes.V_WIDTH / 2) / Constantes.PPM + posicion.x + distancia, 4));
			float finalPantalla = (Constantes.V_WIDTH / 2) / Constantes.PPM + posicion.x + distancia;
			distancia += 3;
			Constantes.hs.enviarMensajeATodos("Crear*Zombies*" + finalPantalla + "*" + 4 + "*" + i + "*" + oleada);
		}
	}
	
	public void update(float delta, int oleada) {
		for (int i = 0; i < enemigos.length; i++) {
			enemigos[i].update();
			Constantes.hs.enviarMensajeATodos("Actualizar*Zombies*Posicion*"+ enemigos[i].getBody().getPosition().x + "*" + enemigos[i].getBody().getPosition().y + "*" + i + "*" + oleada);
			Constantes.hs.enviarMensajeATodos("Actualizar*Zombies*Velocidad*"+ enemigos[i].getBody().getLinearVelocity().x + "*" + enemigos[i].getBody().getLinearVelocity().y + "*" + i + "*" + oleada);
			Constantes.hs.enviarMensajeATodos("Actualizar*Zombies*Destruidos*"+ enemigos[i].isDestruir() + "*" + i + "*" + oleada);
		}
	}
	
	public void render(float delta) {
		for (int i = 0; i < enemigos.length; i++) {
			enemigos[i].render(delta);
		}	
		
	}
	public boolean isBandera() {
		return bandera;
	}
	
	public Enemigo[] getEnemigos() {
		return enemigos;
	}
	
	public Vector2 getPosicion() {
		return posicion;
	}
	
	
}
