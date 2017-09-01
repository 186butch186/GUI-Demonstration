import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

/*
 * Graphical User Interface for the SpaceShip example
 * creates and loads the frame using the Ship class
 *
 * @Michael Butchko
 */


public class MoveShapes {
	
	//constants to represent the frame width and height
	//notice that they are public
	public static final int FRAMEHEIGHT = 500;
	public static final int FRAMEWIDTH = 500;
	private static final int DELAY = 100;
	
	//graphical objects declared at class level
	private JFrame frame = new JFrame();
	private JPanel buttonPanel = new JPanel();
	private JButton btnShow = new JButton("Show");
	private JButton btnAdd = new JButton("Add");
	private JButton Removebtn = new JButton("Remove");
	private JButton exitbtn = new JButton("Exit");
	
	private JPanel checkBoxPanel = new JPanel();
	private JCheckBox planeBox = new JCheckBox("Airplaine");
	private JCheckBox clockBox = new JCheckBox("Clock");
	private JCheckBox boatBox = new JCheckBox("Boat");
	
	
	//Subwindow properties
	private JFrame frame2 = new JFrame();
	private JPanel actionPanel = new JPanel();
	private JPanel playArea = new JPanel();
	private JButton hidebtn = new JButton("Hide");
	private JButton exitbtn2 = new JButton("Remove");

	
	//a single spaceship for use in the game 
	private Shape shape;
	private Shape[]boatArray = new Shape[100];
	private Shape[]planeArray = new Shape[100];
	private Shape[]clockArray = new Shape[100];
	
	private int boatCount = 0;
    private int planeCount = 0;
	private int clockCount = 0;
	
	ImageIcon spaceShip = new ImageIcon("spaceship.png");
	private boolean complete = false;
    
	
	/**
	 * Constructor for the SpaceShip class loads and displays the frame
	 */
	public MoveShapes(){
		createControlFrame();
		createPlayingFrame();
		createObjects();
		MoveObjectsOnScreen();
	}
	
	private void createControlFrame()
	{
		btnShow.setLocation(50, 10);
		btnShow.setSize(50, 20);
		btnShow.addActionListener( new ShowClickHandler() );
		
		btnAdd.setLocation(150, 10);
		btnAdd.setSize(50, 20);
		btnAdd.addActionListener( new addButtonAction() );
		
		Removebtn.setLocation(220, 10);
		Removebtn.setSize(50, 20);
		Removebtn.addActionListener( new RemoveButtonClickHandler() );
		
		exitbtn.setLocation(280, 10);
		exitbtn.setSize(50, 20);
		exitbtn.addActionListener(new ExitButtonHandler());
		
		buttonPanel.setSize(400, 40);
		buttonPanel.setLocation(0, 0);
		
		playArea.setSize(400, 200 - buttonPanel.getHeight());
		playArea.setLocation(0, buttonPanel.getHeight());
		playArea.setLayout(null);
		
		buttonPanel.add(btnShow);
		buttonPanel.add(btnAdd);
		buttonPanel.add(Removebtn);
		buttonPanel.add(exitbtn);
		
		frame.add(checkBoxPanel);
		checkBoxPanel.setLayout(new GridLayout(1,3));
		checkBoxPanel.add(planeBox);
		checkBoxPanel.add(boatBox);
		checkBoxPanel.add(clockBox);
		
		frame.add(buttonPanel,BorderLayout.CENTER);
		frame.add(checkBoxPanel,BorderLayout.CENTER);
		frame.setSize(400, 200);
		frame.setResizable(false);
		frame.setVisible(true); 
	}
	
	private void createPlayingFrame()
	{
		
		actionPanel.setSize(FRAMEWIDTH, 40);
		actionPanel.setLocation(0, 0);
		actionPanel.add(hidebtn);
		actionPanel.add(exitbtn2);
		hidebtn.addActionListener( new HideClickHandler() );
		exitbtn2.addActionListener( new exitBtn2ClickHandler() );
		
		playArea.setSize(FRAMEWIDTH, FRAMEHEIGHT - actionPanel.getHeight());
		playArea.setLocation(0, actionPanel.getHeight());
		playArea.setLayout(null);
		
		frame2.setLocation(200,200);
		frame2.add(actionPanel);
		frame2.add(playArea);
		frame2.setSize(FRAMEWIDTH, FRAMEHEIGHT);
		frame2.setResizable(false);
		//frame.setVisible(true); 
	}
	
	private void createObjects()
	{
		for(int i=0;i<boatArray.length;i++)
		{
			boatArray[i]=new Shape(spaceShip);
			playArea.add(boatArray[i]);
			boatArray[i].setVisible(false);
		}
		
		for(int i=0;i<planeArray.length;i++)
		{
			planeArray[i]=new Shape(spaceShip);
			playArea.add(planeArray[i]);
			planeArray[i].setVisible(false);
		}
		
		for(int i=0;i<clockArray.length;i++)
		{
			clockArray[i]=new Shape(spaceShip);
			playArea.add(clockArray[i]);
			clockArray[i].setVisible(false);
		}
		
	}
	private void MoveObjectsOnScreen()
	{
		
	try{
		while(complete = false)
		{
			for(int i=0;i<boatArray.length;i++)
			{
				boatArray[i].move();
				bounceX(boatArray[i]);
				bounceY(boatArray[i]);
				
			}
			
			for(int x=0;x<planeArray.length;x++)
			{
				planeArray[x].move();
				bounceX(planeArray[x]);
				bounceY(planeArray[x]);
			}
			
			for(int w=0;w<clockArray.length;w++)
			{
				clockArray[w].move();
				bounceX(clockArray[w]);
				bounceY(clockArray[w]);
			
			}
			Thread.sleep(DELAY);
		}
	  }
	catch (InterruptedException f )
	{
	}	
	}
	
	/**
	 * private inner class ButtonClickHandler provides the action listener for the Add Space Ship button
	 */
	
	private class HideClickHandler implements ActionListener{

		/**
		 * This method handles the tasks of creating a new space ship, adding it to the frame, 
		 * disabling the add button, and repainting the frame
		 *
		 * @param e the action event handled by this method
		 */
		
		public void actionPerformed(ActionEvent e){
			frame2.setVisible(false); 
		}
	}
	
	/**
	 * private inner class ButtonClickHandler provides the action listener for the Add Space Ship button
	 */
	
	private class exitBtn2ClickHandler implements ActionListener{

		/**
		 * This method handles the tasks of creating a new space ship, adding it to the frame, 
		 * disabling the add button, and repainting the frame
		 *
		 * @param e the action event handled by this method
		 */
		
		public void actionPerformed(ActionEvent e){
			frame2.dispose();
		}
	}
	
	/**
	 * private inner class ButtonClickHandler provides the action listener for the Add Space Ship button
	 */
	
	private class ShowClickHandler implements ActionListener{

		/**
		 * This method handles the tasks of creating a new space ship, adding it to the frame, 
		 * disabling the add button, and repainting the frame
		 *
		 * @param e the action event handled by this method
		 */
		
		public void actionPerformed(ActionEvent e){
			frame2.setVisible(true); 
		}
	}
	
	/**
	 * private inner class ButtonClickHandler provides the action listener for the Add Space Ship button
	 */
	
	private class ButtonClickHandler implements ActionListener{

		/**
		 * This method handles the tasks of creating a new space ship, adding it to the frame, 
		 * disabling the add button, and repainting the frame
		 *
		 * @param e the action event handled by this method
		 */
		
		public void actionPerformed(ActionEvent e){ 
			
			//create initial ship object add to the playArea panel
			//note the use of the ImageIcon class loading an image from a file
			ImageIcon shipImage = new ImageIcon("spaceship.png");
			shape = new Shape(shipImage);
			playArea.add(shape);
			
			//disable the add button
			//btnNew.setEnabled(false);
			
			//redraw the playArea panel to show the newly added ship
			//notice there is no need to specify dimensions here
			playArea.paintImmediately(playArea.getVisibleRect());
		}
	}
	
	/**
	 * private inner class MoveButtonClickHandler provides the action listener for the Move It button
	 */
	private class RemoveButtonClickHandler implements ActionListener{

		/**
		 * This method calls the moveShip method of the ship object and then repaints the panel 
		 *
		 * @param e the action event handled by this method
		 */
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	/**
	 * private inner class BounceButtonHandler provides the action listener for the Bounce button
	 */
	private class ExitButtonHandler implements ActionListener{
		
		/**
		 * This method calls the bounceShip method of the ship object and then repaints the panel 
		 *
		 * @param e the action event handled by this method
		 */
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}	
	
	public class addButtonAction implements ActionListener
	{
		public void actionPerformed (ActionEvent a)
		{
			if(planeBox.isSelected())
			{
				planeArray[planeCount].setVisible(true);
				planeCount++;
			}
			if(clockBox.isSelected())
			{
				clockArray[clockCount].setVisible(true);
				clockCount++;
			}
			if(boatBox.isSelected())
			{
				boatArray[boatCount].setVisible(true);
				boatCount++;
			}
		}
	}
	
	    /**
		 * This is a private helper method to determine if the ship needs to reverse course in the
		 * X dimension
		 *
		 * @return boolean representing whether or not the ship needs to bounce
		 */
		private void bounceX(Shape shape){
			if (shape.getX() <= 0) {
				shape.LeftWrap();
			}
			else if ((shape.getX() + shape.getWidth()) >= playArea.getWidth()) {
				shape.RightWrap();
			}
			
		}
		
		/**
		 * This is a private helper method to determine if the ship needs to reverse course in the
		 * Y dimension
		 *
		 * @return boolean representing whether or not the ship needs to bounce
		 */
		private void bounceY(Shape shape){
			if (shape.getY() <= 0) {
				shape.TopWrap();
			}
			else if ((shape.getY() + shape.getHeight() + 20) >= playArea.getHeight()) {
				shape.BottomWrap();
			}
			
		
		
	}
}