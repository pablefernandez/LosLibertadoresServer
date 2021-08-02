package eventos;

import java.util.EventListener;

public interface TeclasMovimientoListener extends EventListener {
	public void right();
	public void left();
	public void jump();
	public void up();
}
