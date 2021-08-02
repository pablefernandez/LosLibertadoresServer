package eventos;

import java.util.EventListener;

public interface TeclasListener extends EventListener {
	public void right();
	public void left();
	public void up();
	public void down();
}
