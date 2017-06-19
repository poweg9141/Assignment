package userInput;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import coreComponents.Display;
import graphics.Render3D;

public class UserInput implements KeyListener, MouseListener, MouseMotionListener, FocusListener {

	// a boolean used to deal with all key pressed and released actions
	public boolean[] keyID = new boolean[68836];
	// int to store the position of the mouse on the screen
	public static int mousePos;
	public static int clickX, clickY;
	
	Render3D render = new Render3D(Display.WIDTH, Display.HEIGHT);

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	// method runs when the user is no longer in the frame (i.e. clicks
	// something else)
	public void focusLost(FocusEvent e) {
		// runs through the list of every key and changes it to released
		// (boolean false)
		for (int i = 0; i < keyID.length; i++) {
			keyID[i] = false;
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos = e.getX();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clickX = e.getX();
		clickY = e.getY();
		render.clicked(clickX, clickY);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// if a key is pressed, the computer gets the code of that key
		int keyCode = e.getKeyCode();
		if (keyCode > 0 && keyCode < keyID.length) {
			// and changes the boolean for that key to true
			keyID[keyCode] = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// if a key is released, the computer gets the code of that key
		int keyCode = e.getKeyCode();
		if (keyCode > 0 && keyCode < keyID.length) {
			// and changes the boolean for that key to false
			keyID[keyCode] = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}