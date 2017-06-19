package userInput;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;

import coreComponents.Display;
import coreComponents.GameVariables;
import graphics.Render3D;

public class InputControl {

	// list of variables to control walking and rotation
	public double LR, FB, LRa, FBa, rotation, rotationA, up;
	// creates jump and sneak height, and a maxed boolean to be used when
	// jumping
	double jumpHeight = 0.5;
	double sneakHeight = 0.2;
	boolean maxed = false;

	// method to calculate movement variables, inherits keyCodes from the Game
	// class
	public void tick(boolean forward, boolean back, boolean left, boolean right, boolean tLeft, boolean tRight,
			boolean jump, boolean sneak, boolean sprint) {
		// variables to control the speed of the walking and rotation
		double rotationSpeed = 0.001 * (Math.abs(UserInput.mousePos - (Display.WIDTH / 2)));
		double speed = GameVariables.walkingspeed;
		// variables to change motion back to zero every tick so the user
		// doesn't infinitely get faster;
		double LRMove = 0;
		double FBMove = 0;

		// checks which key has been pressed and increases or decreases the
		// appropriate value
		if (forward) {
			FBMove++;
		}
		if (back) {
			FBMove--;
		}
		if (left) {
			LRMove--;
		}
		if (right) {
			LRMove++;
		}
		if (tRight) {
			rotationA -= rotationSpeed;
		}
		if (tLeft) {
			rotationA += rotationSpeed;
		}
		if (jump && maxed == false) {
			up += jumpHeight;
		}
		if (sprint) {
			speed = GameVariables.runSpeed;
		}
		if (sneak) {
			up -= sneakHeight;
			speed = GameVariables.walkingspeed;
		}
		// maxed is changed t ensure that players can't float or spam jump
		// without first falling back to the ground
		if (maxed == false && up > 7) {
			maxed = true;
		}
		if (maxed == true && up <= 0.1) {
			maxed = false;
		}
		// variables used to calculate the speed of the rotation
		LRa += (LRMove * Math.cos(rotation)) - (FBMove * Math.sin(rotation)) * speed;
		FBa += (FBMove * Math.cos(rotation)) + LRMove * Math.sin(rotation) * speed;
		// add the direction and speed of the movement to the variables every
		// time the game ticks
		LR += LRa;
		FB += FBa;
		// changes the motion variables back to zero so the motion doesn't
		// infinitely get faster
		LRa = 0;
		FBa = 0;
		// slowly lowers the value of up to make the player fall down
		up *= 0.9;
		// add the direction and speed of the rotation to the variable every
		// time the game ticks
		rotation += rotationA;
		// sets rotation variables back to zero so the rotation doesn't
		// infinitely get faster
		rotationA = 0;
	}

	// method used to reset the mouse to the center of the screen
	public static void resetMouse() {
		// new robot object
		Robot mouse;
		// try's because it can fail
		try {
			mouse = new Robot();
			// moves the mouse to the center of the JFrame
			mouse.mouseMove(Display.frameX + (Display.WIDTH / 2) + 3, Display.frameY + (Display.HEIGHT / 2));

			// catches any exceptions
		} catch (AWTException ex) {
			Logger.getLogger(InputControl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}