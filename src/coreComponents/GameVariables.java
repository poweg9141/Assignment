package coreComponents;

/**
 *
 * @author poweg9141
 */
// Class used to change all the games variables to suit the players choice
public class GameVariables {

	// the ID of the player, changes based on the character selected
	public static int playerID;
	// jump height
	public static int jumpheight;
	// walking speed
	public static double walkingspeed;
	// sneak multiplier
	public static double runSpeed;
	// player height
	public static int playerheight;
	// power-ups
	public static int powerUpType;
	// item in hand (maybe)

	// amount of initial power-ups
	public static int initPowers;

	// PLAYER VARIABLES
	public static void playerVariables() {
		// switch statement that sets all basic movement variables based on
		// character selection
		switch (playerID) {
		case 1:
			jumpheight = 10;
			walkingspeed = 1;
			runSpeed = 1.5;
			playerheight = 0;
			break;
		case 2:
			jumpheight = 10;
			walkingspeed = 2;
			runSpeed = 2.5;
			playerheight = -1;
			break;
		case 3:
			jumpheight = 10;
			walkingspeed = 0.005;
			runSpeed = 1.5;
			playerheight = 0;
			break;
		case 4:
			jumpheight = 15;
			walkingspeed = 2.5;
			runSpeed = 3;
			playerheight = -4;
			break;
		case 5:
			jumpheight = 5;
			walkingspeed = 0.5;
			runSpeed = 1;
			playerheight = 4;
			break;
		default:
			jumpheight = 10;
			walkingspeed = 1;
			runSpeed = 1.5;
			playerheight = 0;
			break;
		}
	}

	// changes all the game variables back to normal before rendering every
	// frame, in case they were changed and not changed back
	public static void liveUpdate() {
		switch (playerID) {
		case 1:
			walkingspeed = 1;
			break;
		case 2:
			walkingspeed = 2;
			break;
		case 3:
			walkingspeed = 0.5;
			break;
		case 4:
			walkingspeed = 2.5;
			break;
		case 5:
			walkingspeed = 0.5;
			break;
		default:
			walkingspeed = 1;
			break;
		}
	}

	// night controlled in the Screen class
	public static boolean night = true;
	// ceiling or not controlled in Render3D class
	public static boolean sky = false;
	// time limit conrtolled in Display class
        public static int level = 1;

	// GAME VARIABLES
	public static void gameChanges() {

	}

	// amount of minions spawned
	// size of map
	// amount of walls spawned

	// texture of floor, ceiling, walls

	// amount of power-ups spawned
}
