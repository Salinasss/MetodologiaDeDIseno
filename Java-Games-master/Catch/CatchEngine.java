import java.awt.*;
import java.util.*;

public class CatchEngine
{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;

	public static final int MIN_BALL_DELAY = 30;
	public static final int MAX_BALL_DELAY = 150;
	public static final int SPEED_UP_DELAY = 20;

	private Vector<Ball> balls;
	private Bucket bucket;
	private int points = 0;
	private int lives = 6;
	private int speedDelay = 0;
	private int addBallDelay = MAX_BALL_DELAY;
	private int ballDelay = 0;
	private HighScoreManager highScoreManager;
	private boolean isFast = true; // Variable de control para indicar si el bucket está de color azul

	public CatchEngine()
	{
		balls = new Vector();

		bucket = new FastBucket(WIDTH/2, HEIGHT-Bucket.HEIGHT/2,Bucket.RED_COLOR);
		// bucket = new Bucket(WIDTH/2, HEIGHT-Bucket.HEIGHT/2,Bucket.BLACK_COLOR);
		

		highScoreManager = new HighScoreManager();
	}

	public void moveLeft()
	{
		if(lives > 0)
		{
			bucket.moveLeft();
			if(bucket.getLocation().getX() < -Bucket.WIDTH/2){
				bucket.moveTo(WIDTH+Bucket.WIDTH/2, (int)bucket.getLocation().getY());
			}
		}
	}

	public void moveRight()
	{
		if(lives > 0)
		{
			bucket.moveRight();
			if(bucket.getLocation().getX() > WIDTH+Bucket.WIDTH/2){
				bucket.moveTo(-Bucket.WIDTH/2, (int)bucket.getLocation().getY());
			}
		}
	}

	public void addBall()
	{
		balls.add(new Ball((int)(Math.random()*WIDTH), 0, Math.random()>.5));
	}

	public void moveBalls()
	{
		for(int i = 0; i < balls.size(); i++)
		{
			balls.add(i, balls.get(i).moveDown());
			balls.remove(i+1);
		}
	}

	public void testBallCatch()
	{
		for(int i = 0; i < balls.size(); i++)
		{
			if(bucket.contains(balls.get(i)))
			{
				if (balls.get(i).isGood())
				{
					lives++;
					points++;
				}
				else {
					if(balls.get(i).isGood())
						points++;
					else
						lives--;
				}
				balls.remove(i);
				i--;
			}
			else if(balls.get(i).getLocation().getY() >= HEIGHT+Ball.RADIUS)
			{
				if(balls.get(i).isGood())
					lives--;
				balls.remove(i);
				i--;
			}
		}
	}

	public void update()
	{
		if(lives > 0)
		{
			ballDelay++;
			speedDelay++;
			if(ballDelay >= addBallDelay)
			{
				ballDelay = 0;
				addBall();
			}
			if(speedDelay >= SPEED_UP_DELAY)
			{
				speedDelay = 0;
				addBallDelay--;
				if(addBallDelay < MIN_BALL_DELAY)
					addBallDelay = MIN_BALL_DELAY;
			}
			moveBalls();
			testBallCatch();
			highScoreManager.checkScore(points);
		} 

        if (points >= 2) {
            // Mantén la posición actual del balde y cambia su color
            Point currentPosition = bucket.getLocation();
			if(isFast){
            	bucket = new FastBucket(currentPosition.x,currentPosition.y, Bucket.BLUE_COLOR);
			}
			else{
				bucket = new Bucket(currentPosition.x,currentPosition.y, Bucket.BLUE_COLOR);
			}
        }
		if(points >= 6){
			Point currentPosition = bucket.getLocation();
			if(isFast){
            	bucket = new FastBucket(currentPosition.x,currentPosition.y, Bucket.ORANGE_COLOR);
			}
			else{
				bucket = new Bucket(currentPosition.x,currentPosition.y, Bucket.ORANGE_COLOR);
			}
		}
		if (points >=20)
		{
			Point currentPosition = bucket.getLocation();
			if(isFast){
            	bucket = new FastBucket(currentPosition.x,currentPosition.y, Bucket.PURPLE_COLOR);
			}
			else{
				bucket = new Bucket(currentPosition.x,currentPosition.y, Bucket.PURPLE_COLOR);
			}
		}
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawString("Points: " + points, 10, 20);
		g.drawString("Lives: " + lives, 10, 30);
		if(lives <= 0)
		{
			g.setColor(Color.RED);
			g.drawString("You Lose", WIDTH/2-20, HEIGHT/2);
		}
		for(int i = 0; i < balls.size(); i++)
			balls.get(i).draw(g);

		bucket.draw(g);
		g.setColor(Color.BLACK);
        g.drawString("HighScore: " + + highScoreManager.getHighScore(), 10, 45);
	}
}
