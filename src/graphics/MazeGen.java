package graphics;

import java.util.Random;

public class MazeGen {
	
	public static double[] Pos(int rad){
		
		boolean walls[] = new boolean[rad * rad];
		
		//import the random
		Random rand = new Random();
		//loops through the square created by the radius
		for(int x = 0; x < rad; x++){
			for(int z = 0; z < rad; z++){
				//chance from 0 to 3
				int chance = rand.nextInt(4);
				//if it equals 0, spawn a wall, else don't
				if(chance == 0){
					walls[0] = true;
				}else{
					walls[0] = false;
				}
			}
		}
		
		return null;
	}
	
}
