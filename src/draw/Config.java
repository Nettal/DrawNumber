package draw;

import java.awt.Rectangle;
import java.io.Serializable;
public class Config implements Serializable {

	private static final long serialVersionUID = -8486594206615491973L;
	public Config() {
		
	}
	public int minValue = 0 ;
	public int maxValue = 0 ;
	public Rectangle shape = null ;
	public boolean repeatable =false;
}
