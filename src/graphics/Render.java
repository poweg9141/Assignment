package graphics;

import coreComponents.Display;

public class Render {

	//creates width and height variables to be used in this class
	public final int width, height;
	//creates a pixels[] array to be used in this class
	public int pixels[];
	
	//sets the width height and pixels[] variables to the same as the ones passed from the main class (Display)
	//constructor called when object is initialized
	public Render(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	//the method to set the yPix and Xpix variables to be used in this class to their corresponding X and Y positions on the screen
	public void draw(Render render, int xOff, int yOff){
		//sets the yPix to the initial drawing position(yOff) plus the amount of pixels wanted to be drawn (y, i.e. the counter)
		for(int y = 0; y < render.height; y++){
			int yPix = y + yOff;
			//if the pixel trying to be drawn to is off the screen, nothing will happen
			if(yPix < 0 || yPix > height){
				continue;
			}
			//sets the xPix to the initial drawing position(xOff) plus the amount of pixels wanted to be drawn (x, i.e. the counter)
			for(int x = 0; x < render.width; x++){
				int xPix = x + xOff;
				//if the pixel trying to be drawn to is off the screen, nothing will happen
				if(xPix < 0 || xPix > width){
					continue;
				}
				
				//the empty code lines allow for pixels with no data (empty pixels) to be printed to the screen
				//without this all pixels would have to contain a value (i.e. no graphics transparency)
				int empty = render.pixels[x + y * render.width];
				//only sets pixels to the generated color if the pixel is not black
				//this is because when the screen is cleared it is set to black and doing so to a pixel again would waste time 
				if(empty > 0){
					pixels[xPix + yPix * width] = empty;
				}
			}
		}
	}

}
