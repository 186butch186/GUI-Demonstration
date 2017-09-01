import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.*;

class Shape extends JButton{
		
	
	private int x, y, dx, dy;
	private int array[] = {-4,4};
	private Random rand = new Random();	
	/**
	 * Constructor for the Ship class 
	 */
	public Shape(ImageIcon s){
		super(s);
		Random rand = new Random();
		x = rand.nextInt(MoveShapes.FRAMEWIDTH - this.getWidth());
		y = rand.nextInt(MoveShapes.FRAMEHEIGHT - this.getHeight());
		setLocation(x,y);
		newRoute();
		setSize(25, 25);
		//setForeground(Color.RED);
		
	}   
	
	public void bounceShip(int dx, int dy){
		x = x + dx;
		y = y + dy;
		super.setLocation(x, y);
	}
	
	public void newRoute()
	{
		dx = array[rand.nextInt(2)];
		dy = array[rand.nextInt(2)];
	}
	
	public void move()
	{
		x = x + dx;
		y = y + dy;
		super.setLocation(x, y);
	}
	
	public void TopWrap()
	{
		y = 490;
	}
	public void BottomWrap()
	{
		y = 5;
	}
	public void RightWrap()
	{
		x = 5;
	}
	public void LeftWrap()
	{
		x = 490;
	}
	
	
}