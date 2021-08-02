package pantallas;

public class Pantalla extends PantallaJuego{
//	private OrthographicCamera camaraJuego;
//	private Viewport ventanaGrafica;
//	private MundoBox2D mundo;
//	private KeysListener teclas;
//	private float shooTimer = 0f;
//	private static float SHOOT_WAIT_TIME = 0.3f;
	;
	public Pantalla() {
		
		
	}
//	@Override
//	public void show() {
//		camaraJuego = new OrthographicCamera(); 
//		ventanaGrafica = new StretchViewport(Constantes.V_WIDTH / Constantes.PPM, Constantes.V_HEIGHT / Constantes.PPM,camaraJuego); // seteo cuanto va ser el area de mi mundo que vea mi camara,aunque redimensione la ventana
//		camaraJuego.position.set(ventanaGrafica.getWorldWidth() / 2, ventanaGrafica.getWorldHeight()/ 2, 0); 
////		System.out.println(camaraJuego.position.x );
//		mundo = new MundoBox2D();
//		teclas = new KeysListener();
//		Gdx.input.setInputProcessor(teclas);
//	}

//	public void handleInput(float dt) {
//		if(mundo.getPersonaje().getEstados() != Estados.DEAD) {
//			for (int i = 0; i < Utiles.listener.size(); i++) {
//				try {
//					TeclasMovimientoListener tm = ((TeclasMovimientoListener)Utiles.listener.get(i));
//					if(teclas.isJump() && mundo.getPersonaje().getBody().getLinearVelocity().y <= 3 && mundo.getContactListener().isJugadorEnSuelo()) {
//						tm.jump();
//					}
//					if(teclas.isRight() && mundo.getPersonaje().getBody().getLinearVelocity().x <= 6) {
//						tm.right();
//					}
//					if(teclas.isLeft() && mundo.getPersonaje().getBody().getLinearVelocity().x >= -6) {
//						tm.left();
//					}
//					if(teclas.isUp()) {
//						tm.up();
//					}
//				} catch (Exception e) {}
//			}
//			
//			if(Gdx.input.isKeyJustPressed(Keys.SPACE) && shooTimer >= Constantes.cadenciaDisparoActual) {//mundo.getPersonaje().getCadenciaDisparo()
//				shooTimer = 0;
//				Bala bala = new Bala(mundo.getPersonaje().getBody().getPosition());
//				boolean dispara = false;
//				for (int i = 0; i < Utiles.listener.size(); i++) {
//					try {
//						TeclasListener tl = (TeclasListener)Utiles.listener.get(i);
//						if(mundo.getPersonaje().getDireccionPersonaje() == DireccionesPersonaje.DERECHA) {
//							tl.right();
//							dispara = true;
//							
//						} else if(mundo.getPersonaje().getDireccionPersonaje() == DireccionesPersonaje.IZQUIERDA) {
//							tl.left();
//							dispara = true;
//							
//						} else if(mundo.getPersonaje().getDireccionPersonaje() == DireccionesPersonaje.ARRIBA && mundo.getPersonaje().getBody().getLinearVelocity().x == 0) {
//							tl.up();
//							dispara = true;
//							
//						}
//					} catch (Exception e) {}
//				}
//				if(dispara) {
//					bala.crearObjetoBox2D();
//					mundo.getPersonaje().getBalas().add(bala);
//					dispara = false;
//				}
//				
//			}
//		}
//	}
//	public void update(float delta) {
//		shooTimer += delta;
//		handleInput(delta);
//		camaraJuego.update();
//		mundo.update(delta, camaraJuego);
//	
//	}
//	@Override
//	public void render(float delta) {
//		update(delta);
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		camaraJuego.position.x = mundo.getPersonaje().getBody().getPosition().x;
////		float startX = camaraJuego.viewportWidth / 2;
////		float startY = camaraJuego.viewportHeight / 2;
////		boundary(startX, startY, mundo.getLevelWidth() * 50 - startX * 2, mundo.getLevelHeight() * 50 - startY * 2);
//		Render.batch.setProjectionMatrix(camaraJuego.combined);
//		mundo.render(delta, camaraJuego);
//		Constantes.shoot = false;
//	}

//	@Override
//	public void resize(int width, int height) {
//		ventanaGrafica.update(width,height);
//	}

//	@Override
//	public void pause() {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public void resume() {
//
//		
//	}

//	@Override
//	public void hide() {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public void dispose() {
//		Render.batch.dispose();
//	}
	
	public void boundary(float startX,float startY,float width, float height) {
		
		if(camaraJuego.position.x < startX) {
			camaraJuego.position.x = startX;
		}
		if(camaraJuego.position.y < startY) {
			camaraJuego.position.y = startY;
		}
		if(camaraJuego.position.x > startX + width) {
			camaraJuego.position.x = startX + width;
		}
		if(camaraJuego.position.y > startY + height) {
			camaraJuego.position.y = startY + height;
		}
		camaraJuego.position.set(camaraJuego.position);
		camaraJuego.update();
	}

	
}
