package Entities;

import java.util.ArrayList;
import java.util.List;

public class Spawner {

	public List<Sprite> sprites = new ArrayList<Sprite>();
	
	public void addEntity(Sprite sprite){
		sprites.add(sprite);
	}
}
