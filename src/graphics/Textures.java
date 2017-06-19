/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Textures {

	// creates an object of render called floor, sets it equal to loadBitMap
	// with a file
	// the render class constructor will make an array that will store the
	// pixels of the image into
        
	public static Render floor = loadBitMap("textures/floors/brownfloor.png");
        public static Render floor1 = loadBitMap("textures/floors/blue.png");
        public static Render floor2 = loadBitMap("textures/floors/green.png");
        public static Render floor3 = loadBitMap("textures/floors/goldfloor.png");

	// public static Render floors2 =
	// loadBitMap("F:/SummativeFix285478/textures/floors/brownfloor.png");

	// creates a method that will return an array of filled pixels, the same
	// array that was just created above
	public static Render loadBitMap(String file) {

		// try's to load an image because the image could not be there
		try {
			// creates a buffered image of the file passed to the method
			BufferedImage image = ImageIO.read(ClassLoader.getSystemResource(file));

			// gets the width and height of the image
			int width = image.getWidth();
			int height = image.getHeight();

			// creates an array of pixels that are the length of the image
			Render result = new Render(width, height);

			// gets the RGB value of every pixel in the image, and sets it to
			// the corresponding spot in the created array
			image.getRGB(0, 0, width, height, result.pixels, 0, width);

			// returns the array now filled with RGB values of the image
			return result;

			// catches any possible errors, and if the image can't be loaded,
			// crashes the game
		} catch (Exception e) {
			System.out.println("error loading image");
			throw new RuntimeException(e);
		}
	}
}
