package entidades;

import utiles.Animacion;
import utiles.Recursos;

public class PersonajeAzul extends Personaje {
	private float widthJump = 3,heightJump = 3;
	public PersonajeAzul(int nroCliente) {
		super(nroCliente);
		animacionIdle = new Animacion(Recursos.ANIMACION_IDLE,1,width,height);
		animacionRun = new Animacion(Recursos.ANIMACION_RUN	,4,width,height); //4 COLUMNAS
		animacionJump = new Animacion(Recursos.ANIMACION_JUMP,4,widthJump,heightJump);
		animacionMirArriba = new Animacion(Recursos.ANIMACION_MIRA_ARRIBA,1,width,height);
		animacionIdleShoot = new Animacion(Recursos.ANIMACION_IDLE_SHOOT,1,width,height);
		animacionMuerte = new Animacion(Recursos.ANIMACION_MUERTE_PLAYER1,1,width,2);
	}
}
