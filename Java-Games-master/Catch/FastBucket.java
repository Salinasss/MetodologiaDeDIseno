import java.awt.*;

public class FastBucket extends Bucket{
	
	public static final int fastSpeed = 10;

	public FastBucket(int x, int y, Color color) {
		super(x, y, color);
		this.speed = fastSpeed;
	}

}
