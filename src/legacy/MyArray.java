package legacy;
import java.util.ArrayList;

public class MyArray extends ArrayList<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyArray(int size){
		super(size);
		for(int i=1;i<=size;i++){
			this.add(Integer.valueOf(i));
		}
	}

	}