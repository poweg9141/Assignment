package graphics;

import Entities.Spawner;
import Entities.Sprite;
import coreComponents.Display;
import coreComponents.GameKeys;
import coreComponents.GameVariables;

//create a new class that extends the render class
public class Render3D extends Render {

    Spawner spawn = new Spawner();
    // creates a double array to store the z coordinate of every pixel
    // is passed to the renderDistance method to allow for pixel fading
    private double[] zPos = new double[width * height];
    public static boolean[] wall = new boolean[600 * 800];
    private double[] zWall = new double[width];
    // creates class accessible variables for the movement from the floor method
    private double forward, right, cosine, sine, up;

    // sets the width and height variables in this class equal to that of the
    // width and height in the render class
    public Render3D(int width, int height) {
        super(width, height);
    }

    //NOTE: ALL CODE IN RENDER3D IS MODIFIED FROM NOTCH'S LUDAM DARE GAME AND IS NOT MINE
    // the method that will be used to render the floor and ceiling for the game
    public void floor(GameKeys game) {

        // sets all the z positions for the walls to 0 so they wont render
        // unless we tell them to
        for (int i = 0; i < width; i++) {
            zWall[i] = 0;
        }

        for (int i = 0; i < wall.length; i++) {
            wall[i] = false;
        }

        // variable used to control the depth of the floor
        double floorDepth = 8;
        double ceilingHeight = 8;
        // sets the variable for forward and right which is based on the games
        // time in the GameTick class
        // these variables are also controlled by the InputControl class
        forward = game.control.FB / 2;
        right = game.control.LR;
        up = game.control.up;
        // makes a rotation variable that is dictated by the games time
        double rotation = /* 0;//Math.sin(game.time % 10000.0 / 220);// */ game.control.rotation;
        // makes a cosine value for the rotation
        cosine = Math.cos(rotation);
        // makes a sine value for the rotation
        sine = Math.sin(-rotation);

        // for loop to run until y equals the height declared
        for (int y = 0; y < height; y++) {
            // controls the rendering of the ceiling
            double ceiling = (y - height / 2.0) / height;
            // used to control the depth of the floor
            double z = (floorDepth + up) / ceiling;
            // inverts the double for ceiling so that the floor animation will
            // move in the same direction
            if (ceiling < 0) {
                // used to control the height of the ceiling
                z = (ceilingHeight - up) / -ceiling;
            }
            // creates the position for the floor (will be relative to the
            // position of the ceiling)

            // for loop to run inside y loop until x equals the width declared
            for (int x = 0; x < width; x++) {
                // controls the rendering of the floor
                double depth = (x - width / 2.0) / height;
                // makes the floor rendering in relation to the ceiling
                // rendering
                depth *= z;
                // xx controls the vertical animation
                double xx = depth * cosine + z * sine;
                // yy controls the horizontal animation
                double yy = z * cosine - depth * sine;
                // changes xx into an int to be used in rendering to the
                // pixels[] array
                int xPix = (int) (xx + right);
                // changes yy into an int to be used in rendering to the
                // pixels[] array
                int yPix = (int) (yy + forward);
                // renders the animation to the screen using the pixels[] array
                // from the Render class
                // pixels[x + y * width] = ((xPix & 15) * 16) | ((yPix & 15) *
                // 16) << 16;
                // pixels[x + y * width] = ((xPix & 7) * 8) | ((yPix & 7) * 8)
                // << 8;
                    pixels[x + y * width] = Textures.floor.pixels[(xPix & 7) + (yPix & 7) * 8];
                zPos[x + y * width] = z;
            }
        }
    }

    // NOT USED
    public void renderEntity(double x, double y, double z) {
        double corVal = 0.0625;

        double XC = ((x) - (right * corVal)) * 2;
        double ZC = ((z) - (forward * corVal)) * 2;
        double YC = ((y) - (up * corVal)) * 2;

        double rotX = XC * cosine - ZC * sine;
        double rotZ = ZC * cosine - XC * sine;
        double rotY = YC;

        // the center of the screen
        double centerX = 400.0;
        double centerY = 300.0;

        // calculate rotation for x and y pixels (center of sprite)
        double yPix = rotX / rotZ * height + centerX;
        double xPix = rotY / rotZ * height + centerY;

        // finds the outside corners of the sprite
        double xPixL = xPix - 16 / rotZ;
        double xPixR = xPix + 16 / rotZ;
        double yPixL = yPix - 16 / rotZ;
        double yPixR = yPix + 16 / rotZ;

        // converts the corners into an int
        int xPixLI = (int) xPixL;
        int xPixRI = (int) xPixR;
        int yPixLI = (int) yPixL;
        int yPixRI = (int) yPixR;

        // cuts off rendering at edge of screen
        if (xPixLI < 0) {
            xPixLI = 0;
        }
        if (xPixRI > width) {
            xPixRI = width;
        }
        if (yPixLI < 0) {
            yPixLI = 0;
        }
        if (yPixRI > width) {
            xPixRI = width;
        }

        // for loop to render y pixels of sprites
        for (int y1 = yPixLI; y1 < yPixRI; y1++) {
            // for loop to render x pixels of sprites
            for (int x1 = xPixLI; x1 < xPixRI; x1++) {
                if (zPos[x1 + y1 * width] > ZC) {
                    pixels[x1 + y1 * width] = 0x236DCF;
                    zPos[x1 + y1 * width] = rotZ;
                }
            }
        }
    }

    // method used to render a single wall into the game given the constructors
    // below
    public void renderWall(double leftx, double rightx, double rightdistancez, double leftdistancez, double heighty) {

        // if(leftx > rightx){
        // double temp = leftx;
        // leftx = rightx;
        // rightx = temp;
        // }

        // correction value used to account for the walls moving to fast
        double corVal = -0.0625;

        // Math to calculate the left and right X and Z coordinates of the wall,
        // accounting for player movement
        // stands for left/right X/Z Coordinates
        double leftXC = ((leftx) - (right * -corVal)) * 2;
        double leftZC = ((leftdistancez) - (forward * -corVal)) * 2;
        double rightXC = ((rightx) - (right * -corVal)) * 2;
        double rightZC = ((rightdistancez) - (forward * -corVal)) * 2;

        // Math to calculate the right/left X/Z coordinates, accounting for
        // player rotation of the z and x coordinates
        // stands for rotation left/right side X/Z
        double rotLSX = leftXC * cosine - leftZC * sine;
        double rotLSZ = leftZC * cosine + leftXC * sine;
        double rotRSX = rightXC * cosine - rightZC * sine;
        double rotRSZ = rightZC * cosine + rightXC * sine;

        // Math to calculate the right and left top and bottom wall Y position,
        // accounting for player movement in the y direction
        // stands for right Y top/bottom Position
        double rightYTopP = ((-heighty) - (up * corVal)) * 2;
        double rightYBotP = ((+0.5 - heighty) - (up * corVal)) * 2;
        double leftYTopP = ((-heighty) - (up * corVal)) * 2;
        double leftYBotP = ((+0.5 - heighty) - (up * corVal)) * 2;

        // creates the max distance the box can be rendered
        double cop = 0.5;

        // if both the boxes have already been cut off, leave method
        if (rotLSZ < cop && rotRSZ < cop) {
            return;
        }

        if (rotLSZ < cop) {
            rotLSZ = rotLSZ + (rotRSZ - rotLSZ) * ((cop - rotLSZ) / (rotRSZ - rotLSZ));
            rotLSX = rotLSX + (rotRSX - rotLSX) * ((cop - rotLSZ) / (rotRSZ - rotLSZ));
        }
        if (rotRSZ < cop) {
            rotRSZ = rotLSZ + (rotRSZ - rotLSZ) * ((cop - rotLSZ) / (rotRSZ - rotLSZ));
            rotRSX = rotLSX + (rotRSX - rotLSX) * ((cop - rotLSZ) / (rotRSZ - rotLSZ));
        }

        // Math to calculate the left and right X pixel accounting for all
        // variables, (rotation and movement)
        double xPixL = (rotLSX / rotLSZ * height + width / 2);
        double xPixR = (rotRSX / rotRSZ * height + width / 2);

        // if the wall is inverted, leaves the method because there was an error
        // drawing the wall
        if (xPixL >= xPixR) {
            return;
        }

        // converts the right and left X pixel into an integer so it can be used
        // in the pixel array and displayed to the screen
        int xPixLI = (int) (xPixL);
        int xPixRI = (int) (xPixR);

        // if the left pixel is off the left side of the screen, set the new x
        // pixel to the end of the screen
        if (xPixLI < 0) {
            xPixLI = 0;
        }
        // if the right pixel is off the right side of the screen, set the new x
        // pixel to the end of the screen
        if (xPixRI > width) {
            xPixRI = width;
        }

        // finds the four y corners of the wall accounting for player movement
        // and Z rotation
        int yPixTL = (int) (leftYTopP / rotLSZ * height + height / 2.0);
        int yPixBL = (int) (leftYBotP / rotLSZ * height + height / 2.0);
        int yPixTR = (int) (rightYTopP / rotRSZ * height + height / 2.0);
        int yPixBR = (int) (rightYBotP / rotRSZ * height + height / 2.0);

        // for loop to draw the X pixels of the wall from left to right
        for (int x = xPixLI; x < xPixRI; x++) {
            // Math to account for player rotation in the X coordinate
            double pixelRotation = (x - xPixL) / (xPixR - xPixL);

            // gets the inverse of the pixels
            double inverse = ((1 / rotLSZ) + ((1 / rotRSZ) - (1 / rotLSZ)) * pixelRotation);

            // if the inverse is less than the position of the wall do nothing
            if (zWall[x] > inverse) {
                continue;
            }
            // otherwise set the walls z positions to its pixel inverse
            zWall[x] = inverse;

            // Find the top and bottom yPixl position accounting for movement
            // and rotation
            double yPixTP = yPixTL + (yPixTR - yPixTL) * pixelRotation;
            double yPixBP = yPixBL + (yPixBR - yPixBL) * pixelRotation;

            // converts the yPixle positions into integers to be used in the
            // pixel array
            int yPixTPI = (int) (yPixTP);
            int yPixBPI = (int) (yPixBP);

            // if the top of the wall i past the top of the screen, the new top
            // position is the top of the screen
            if (yPixTPI < 0) {
                yPixTPI = 0;
            }
            // if the bottom of the wall is past the bottom of the screen, the
            // new bottom position is the bottom of the screen
            if (yPixBPI > height) {
                yPixBPI = height;
            }

            // for loop to render the Y pixels, inside the X loop so that all
            // y's are renders for every x
            for (int y = yPixTPI; y < yPixBPI; y++) {
                // sets the corresponding spot in the pixel array to a random
                // color, for now...
                pixels[x + y * width] = 0x3DB2D9;
                wall[x + y * width] = true;
                // apply's one z coordinate to the entire wall so it gets
                // properly affected by render distance
                //render distance on walls disabled so you can always find your way back to the maze
                //since there's no world limit
                zPos[x + y * width] = Math.abs(rightdistancez - leftdistancez) * 200;
            }
        }

    }

    public void renderDistance() {

        // cycles through the pixels one by one
        for (int i = 0; i < width * height; i++) {
            // sets a brightness variable which is what percent of the original
            // color the new color should appear as
            // (255 - zPos) gets the new color intensity compares to white (255)
            // is multiplies by an intensity variable which controls how
            // quickly the screen fades to black
            // divided by 255 to get the percent reduction of the color so it
            // can be applied to any color
            double darkness = ((255 - zPos[i]) * 0.9) / 255;
            // double brightness;

            // makes sure the brightness variable doesn't go below 0
            if (darkness < 0) {
                darkness = 0;
            }

            if (darkness > 255) {
                darkness = 255;
            }

            // get the r g and b values from the number stored in the pixels
            // array
            int r = (pixels[i] >> 16) & 0xff;
            int g = (pixels[i] >> 8) & 0xff;
            int b = (pixels[i]) & 0xff;

            // multiplies that by the brightness variable
            r *= darkness;
            g *= darkness;
            b *= darkness;

            // sets the new colour values equal to their corresponding spot in
            // the array
            pixels[i] = r << 16 | g << 8 | b;
        }

    }

    // an attempt to make the render distance fade to white instead of black,
    // doesn't work
    public void renderDarkness() {

        for (int i = 0; i < width * height; i++) {

            double brightness = ((255 - zPos[i]) * 0.9) / 255;

            int r = (pixels[i] >> 16) & 0xff;
            int g = (pixels[i] >> 8) & 0xff;
            int b = (pixels[i]) & 0xff;

            r /= brightness;
            g /= brightness;
            b /= brightness;

            pixels[i] = r << 16 | g << 8 | b;
        }
    }

    public void clicked(int x, int y) {
        if (wall[x + y * width] == true) {
            Display.points++;
        }
    }
}