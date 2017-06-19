package coreComponents;

import java.awt.event.KeyEvent;

import graphics.MazeGen;
import userInput.InputControl;
import userInput.UserInput;

public class GameKeys {

	// creates a time variable, the number represents the amount of times the
	// screen has been updated since the game started
	public static double time = 0;
	// creates a new object of the InputControl class
	public InputControl control = new InputControl();

	public void tick(boolean[] key) {
		// adds one to the public variable time
		time += 1;
		// declaring booleans for each key we want the user to be able to press
		boolean forward = key[KeyEvent.VK_W];
		boolean back = key[KeyEvent.VK_S];
		boolean left = key[KeyEvent.VK_A];
		boolean right = key[KeyEvent.VK_D];
		boolean jump = key[KeyEvent.VK_SPACE];
		boolean sneak = key[KeyEvent.VK_Q];
		boolean sprint = key[KeyEvent.VK_SHIFT];
		// sets right and left turning to false in case the mousePos variable
		// returns an error
		boolean tLeft = false;
		boolean tRight = false;

		// gets the position of the mouse on the screen
		int mouseX = UserInput.mousePos;
		// if the mouse is on the right side of the screen, turn right
		if (mouseX < Display.WIDTH / 2) {
			tLeft = true;
			tRight = false;
			// if the mouse is on the left side of the screen, turn left
		} else if (mouseX > Display.WIDTH / 2) {
			tRight = true;
			tLeft = false;
			// if the mouse is in the middle of the screen, don't turn
		} else if (mouseX == Display.WIDTH / 2) {
			tRight = false;
			tLeft = false;
		}
		// calls the tick method in inputControl, and passes the variables of
		// every key we want to perform an action for
		control.tick(forward, back, left, right, tLeft, tRight, jump, sneak, sprint);
	}
}
