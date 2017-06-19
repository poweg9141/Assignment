package graphics;

import java.util.Random;

import Entities.Spawner;
import Entities.Sprite;
import coreComponents.GameKeys;

//extends the render class so we can use all of its methods in here without importing them
public class Screen extends Render {

	// create a variable of the Render3D class
	private Render3D render = new Render3D(width, height);
	Spawner spawn = new Spawner();
	private double[] xPos, zPos, sPosX;
	private int radius;

	// creates a method screen inside the screen class with the variables height
	// and width
	public Screen(int width, int height) {
		// overrides the two variables height and width from the superclass so
		// they can be used differently in this class
		super(width, height);
	}

	public void generateWall(int rad) {
		xPos = new double[rad];
		zPos = new double[rad];
		sPosX = new double[rad];
		radius = rad;
		Random rand = new Random();
		for (int i = 0; i < xPos.length; i++) {
			int chance = rand.nextInt(2);
			if (chance == 0) {
				xPos[i] = i;
				sPosX[i] = 0;
				spawn.addEntity(new Sprite(0, 0, 0));
			} else {
				xPos[i] = 0;
				sPosX[i] = 0;
			}
		}
		for (int i = 0; i < zPos.length; i++) {
			int chance2 = rand.nextInt(2);
			if (chance2 == 0) {
				zPos[i] = i;
				sPosX[i] = 0;
			} else {
				zPos[i] = 0;
				sPosX[i] = 0;
				spawn.addEntity(new Sprite(0, 0, 0));
			}
		}

	}

	// method with a object of the GameKeys passed to it so Render3D can use
	// methods from it without importing it
	public void render(GameKeys game) {
		// sets every pixel in the array to 0 (the color black)
		// acts as the eraser for the screen
		for (int i = 0; i < width * height; i++) {
			pixels[i] = 0;
		}

		// calls the floor method in the render class, will render the floor and
		// ceiling
		render.floor(game);

		for (int x = 0; x < xPos.length; x++) {
			for (int z = 0; z < radius; z++) {
				render.renderWall(xPos[x] / 2, xPos[x] / 2, zPos[z] / 2, zPos[z] / 2 + 0.5, 0);
				render.renderWall(xPos[x] / 2, xPos[x] / 2, zPos[z] / 2 + 0.5, zPos[z] / 2, 0);
				render.renderWall(xPos[x] / 2, xPos[x] / 2 + 0.5, zPos[z] / 2, zPos[z] / 2, 0);
				render.renderWall(xPos[x] / 2 + 0.5, xPos[x] / 2, zPos[z] / 2, zPos[z] / 2, 0);
				render.renderWall(xPos[x] / 2, xPos[x] / 2, zPos[z] / 2, zPos[z] / 2 + 0.5, 0.5);
				render.renderWall(xPos[x] / 2, xPos[x] / 2, zPos[z] / 2 + 0.5, zPos[z] / 2, 0.5);
				render.renderWall(xPos[x] / 2, xPos[x] / 2 + 0.5, zPos[z] / 2, zPos[z] / 2, 0.5);
				render.renderWall(xPos[x] / 2 + 0.5, xPos[x] / 2, zPos[z] / 2, zPos[z] / 2, 0.5);
			}
		}
		
		// calls the render distance method, will make everything fade to black
		render.renderDistance();
		// calls the draw method in render class, this will set all the pixels
		// into the array the main class uses, a way of finalizing what has been
		// rendered
		draw(render, 0, 0);
	}
}
