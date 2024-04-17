import java.awt.*;

public class Bucket
{
	public static final int WIDTH = 20;
	public static final int HEIGHT = 18;
	public static final int SPEED = 5;

	private int x,y;
	private Color color;

	public static final Color BLACK_COLOR = Color.BLACK;
	public static final Color BLUE_COLOR = Color.blue;
	public static final Color ORAN_COLOR = Color.orange;
	public static final Color PURPLE_COLOR = Color.magenta;

	public Bucket(int x, int y, Color color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public Bucket moveTo(int x, int y)
	{
		return new Bucket(x,y,this.color);
	}

	public Bucket move(int dx, int dy)
	{
		return new Bucket(x+dx, y+dy,this.color);
	}

	public Bucket moveLeft()
	{
		return move(-SPEED, 0);
	}

	public Bucket moveRight()
	{
		return move(SPEED, 0);
	}

	public Point getLocation()
	{
		return new Point(x,y);
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x-WIDTH/2, y-HEIGHT/2, WIDTH, HEIGHT);
	}

	public boolean contains(Ball b)
	{
		return getBounds().contains(b.getBounds());
	}

	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x-WIDTH/2, y-HEIGHT/2, WIDTH, HEIGHT);
	}
}
