package coreComponents;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import graphics.MazeGen;
import graphics.Screen;
import guiLauncher.Launcher;
import java.awt.Color;
import java.awt.Font;
import userInput.InputControl;
import userInput.UserInput;

//the main class for the game extends canvas, means it inherits all the methods from java's canvas class
//implements runnable allows the thread that will later be created to actually run
public class Display extends Canvas implements Runnable {

	// declared because games need one??
	private static final long serialVersionUID = 1L;
	// two integers to set the width and the height of the screen being
	// displayed
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	// creates a variable to be used in mouse movement
	int oldMousePos = 0;
	int newPos = 0;
	// a string to store the title (name) of the game so it can be used easier
	// later
	public static final String Title = "Wall Simulator 34D";
	// creates a thread to be run for the game
	private Thread thread = new Thread(this);
	// creates an object of the screen class we created
	private Screen screen = new Screen(WIDTH, HEIGHT);
	// stores the width and height of the screen in one object
	private Dimension size = new Dimension(WIDTH, HEIGHT);
	// creates an object of the game class
	private GameKeys game = new GameKeys();
	// create new object of the UserInput class
	private UserInput user = new UserInput();
	// creates an object of the buffered image class in java
	private BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	// a boolean that will be used to determine if the game is running or not
	private boolean running = false;
	// controlls the time limit in the game
	public static int timeLimit = 60;
	// a game over boolean to be changed to true when the time limit is up
	public boolean gameOver = false;
	// new JFrame to store our game in
	static JFrame frame = new JFrame();
	//points int
	public static int points = 0;
	// stores the X and Y position of the frame on the screen
	public static int frameX;
	public static int frameY;

	// the main class, to be run when the program starts
	public static void main(String[] args) {
		// new Launcher object, will run the launcher when the game starts
		new Launcher();

	}

	public Display() {
		// sets the preferred max and min size of the frame using the dimension
		// variable
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		// getRaster() returns a WritableRaster which allows us to write to the
		// individual pixels
		// getDataBuffer() gets the capacity and position of the bufferedImage
		// getData() returns the image as one object
		screen.pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		// adds a key, mouse, and focus listener to the object user
		addKeyListener(user);
		addMouseListener(user);
		addMouseMotionListener(user);
		addFocusListener(user);
	}

	public void start() {
		// checks if the game is already running, and if it is does nothing
		// (return means it exits the method)
		if (running) {
			return;
		}
		// if the game is not already running it sets running to true
		running = true;
		// calls the threads start method
		thread.start();
	}

	@Override
	public void run() {
		// this method will run continuously while the games running variable is
		// true (when the game is running)
		// render must be called at some point
		// sets the game variables right before the game starts
		screen.generateWall(20);
		GameVariables.playerVariables();
		System.out.println(GameVariables.walkingspeed);
		while (running && timeLimit > 0) {
			// creates two variables for current time and last time
			long lastTime = System.nanoTime() / 1000000000; // its a billion
			long currentTime = System.nanoTime() / 1000000000;
			// sets the fps to zero
			int fpsCount = 0;
			// while loop runs until one second has passed
			while (currentTime - lastTime < 1) {
				// calls all the necessary methods to run the game
				render();
				tick();
				GameVariables.liveUpdate();
				// updates the current Time variable
				currentTime = System.nanoTime() / 1000000000;
				// adds one to the fps counter since the screen has been updated
				// once
				fpsCount++;
				// gets the updated X and Y positions of the JFrame on the
				// computer screen
				frameX = frame.getX();				
				frameY = frame.getY();
				// sets the mouse to the center of the screen
//				InputControl.resetMouse();
			}
			// reduces the time limit by one every second
			timeLimit--;
			// when one second has passed, prints out the counted frames
			System.out.println(fpsCount + " fps");
		}
		// sets gameOver to true and renders the last frame of the game before
		// stopping screen updates
		gameOver = true;
		System.out.println("Game Over");
		render();
		return;
	}

	private void tick() {
		// runs the tick method in the game class
		game.tick(user.keyID);
	}

	private void render() {
		// initialized the BufferStrategey that will be used to display pixels
		// for this game
		if (gameOver) {
			System.out.println("Rendering Game over");
		}
		BufferStrategy bs = this.getBufferStrategy();
		// if the buffer stategy has not been initialized, creates a 3d buffer
		// strategy and leaves this method
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		// runs the render method in the screen class
		if (!gameOver) {
			screen.render(game);
		}

		// creates an object from the graphics class using the buffered strategy
		// defined above
		Graphics g = bs.getDrawGraphics();
		// draws the image to the screen using the following variables
		/*
		 * img -> the actual image we want to draw, specified in the display
		 * method above 0 -> the X coordinate to start drawing the image from 0
		 * -> the Y coordinate to start drawing the image from WIDTH -> the
		 * width of the image to be drawn HEIGHT -> the height of the image to
		 * be drawn null -> something to do with the image observer class last
		 * variable left blank because i don't know what it does but it must
		 * passed for width and height to be passed as well
		 */
		if (!gameOver) {
			// draws the rendered image to the screen
			g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
			// prints the remaining time to the screen
			g.setColor(Color.red);
			g.drawString("Time: " + timeLimit + " seconds", 5, 15);
			g.drawString("Points:" + points, 5, 30);
			// disposes the old image to prepare for a new one to be generated
			g.dispose();
			// makes the image visible on the JFrame
			bs.show();
		}
		if (gameOver) {
			// sets the colour to red, selects the font and size
			g.setColor(Color.red);
			Font font = new Font("Arial", Font.BOLD, 50);
			g.setFont(font);
			// prints the EndGame string to the screen
			String end = "GAME OVER";
			g.drawString("GAME OVER", WIDTH / 2 - end.length(), HEIGHT / 2);
			// disposes the old image to prepare for a new one to be generated
			// though not necessary it clears the image in case an error causes
			// a new one to be generated
			g.dispose();
			// makes the image visible on the JFrame
			bs.show();
		}
	}
}