package MotionlessElement;

import java.io.IOException;

import entity.Entity;
import entity.Sprite;

public class Dirt extends MotionlessElement {

	static Sprite sprite = new Sprite('T', "Dirt.png");
	
	static {
		try {
			sprite.loadImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Dirt(int x, int y) {
		super(sprite, x, y);
	}

}
