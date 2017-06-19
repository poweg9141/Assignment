/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiLauncher;

import static coreComponents.Display.Title;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import coreComponents.Display;
import graphics.Textures;

//class extends JFrame so it can be used without creating an object
public class Launcher extends JFrame {
	// this is here because java said it had to be
	private static final long serialVersionUID = 1L;

	// creates a new JPanel to store the multiple JButtons declared below
	JPanel panel = new JPanel();
	JButton start, options, tutorial, quit;

	// sets the width and height of the JFrame
	private int width = 200;
	private int height = 300;

	// sets the width and height of all the buttons
	private int buttonWidth = 150;
	private int buttonHeight = 50;

	// Constructor to be called when an object of this class is created
	public Launcher() {
		// sets the title and size of the frame
		setTitle("Hide n' Seek Launcher");
		setSize(new Dimension(width, height));
		// sets the default close operation and centers the frame in the screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// adds the JPanel to the JFrame
		getContentPane().add(panel);
		// makes the frame not resizable and makes it visible
		setResizable(false);
		setVisible(true);
		// sets the layout of the JPanel to nothing, so we can use absolute
		// positioning
		panel.setLayout(null);
		// makes the panel visible in the JFrame
		panel.setVisible(true);

		// generates the initial buttons on the JFrame
		initialButtons();
	}

	private void initialButtons() {
		// Names all the buttons
		start = new JButton("START");
		options = new JButton("OPTIONS");
		tutorial = new JButton("TUTORIAL");
		quit = new JButton("QUIT");

		// sets all their positions
		start.setBounds((width / 2) - (buttonWidth / 2), 10, buttonWidth, buttonHeight);
		options.setBounds((width / 2) - (buttonWidth / 2), 70, buttonWidth, buttonHeight);
		tutorial.setBounds((width / 2) - (buttonWidth / 2), 130, buttonWidth, buttonHeight);
		quit.setBounds((width / 2) - (buttonWidth / 2), 190, buttonWidth, buttonHeight);

		// makes them all visible
		start.setVisible(true);
		options.setVisible(true);
		tutorial.setVisible(true);
		quit.setVisible(true);

		// adds the buttons to the panel
		panel.add(start);
		panel.add(options);
		panel.add(tutorial);
		panel.add(quit);

		// makes an action listener to all the buttons and makes them call their
		// respective classes when pressed
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				LaunchGame();
			}
		});

		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				setVisible(false);
				OptionsMenu m = new OptionsMenu();
				m.setVisible(true);
			}
		});

		tutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new TutorialMenu();
			}
		});
		// quits the game if quit is pressed
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public final void LaunchGame() {

		// creates an object of the Display class (main class), the one we are
		// in now
		Display game = new Display();
		// creates a JFrame object, which is where the game will be displayed

		// adds this classes object (game) to the JFrame
		add(game);
		// tells java what to do when the x button is pressed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// sets the title of the screen
		setTitle(Title);
		// code below is obsolete because of the Dimension object declared below
		// frame.setSize(WIDTH, HEIGHT);
		// sets it so the user cannot resize the frame once it appears
		setResizable(false);
		// packs the frame, java's way of ensuring everything displayed fits
		// into the set dimensions
		pack();
		// makes the screen appear in the center of the computer screen
		setLocationRelativeTo(null);
		// makes the frame actually visible so that the user can see and
		// interact with it
		setVisible(true);

		// calls the start method from the main class using the object created
		// above
		game.start();
	}
}
