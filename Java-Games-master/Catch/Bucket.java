import java.awt.*;

public class Bucket
{

	protected int x, y;
	protected int speed = 5;
	protected Color color;

	public static final int WIDTH = 20;
	public static final int HEIGHT = 18;

	public static final Color RED_COLOR = Color.RED;
	public static final Color BLACK_COLOR = Color.BLACK;
    public static final Color BLUE_COLOR = Color.BLUE;  // Asegúrate de que este color esté definido
    public static final Color ORANGE_COLOR = Color.ORANGE;  // Verificando la definición
    public static final Color PURPLE_COLOR = Color.MAGENTA;

	public Bucket(int x, int y, Color color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
		System.out.println("Initialized Bucket with speed");
	}

	protected Bucket moveTo(int x, int y)
	{
		return new Bucket(x,y, this.color);
	}

	protected Bucket move(int dx, int dy)
	{
		return new Bucket(x+dx, y+dy, this.color);
	}

	public Bucket moveLeft()
	{
		return move(-speed, 0);
	}

	public Bucket moveRight()
	{
		return move(speed, 0);
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
		g.setColor(this.color);
		g.fillRect(x-WIDTH/2, y-HEIGHT/2, WIDTH, HEIGHT);
	}
}