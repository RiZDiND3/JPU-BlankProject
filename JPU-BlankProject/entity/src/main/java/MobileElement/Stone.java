package MobileElement;

import java.io.IOException;

import entity.Entity;
import entity.Sprite;

public class Stone extends MobileElement {

	static Sprite sprite = new Sprite('O', "Stone.png");
	boolean isFallen;
	int falling = 0;
	
	public int getFalling() {
		return falling;
	}

	public void setFalling(int falling) {
		this.falling = falling;
	}

	public boolean isFallen() {
		return isFallen;
	}

	public void setFallen(boolean isFallen) {
		this.isFallen = isFallen;
	}

	static {
		try {
			sprite.loadImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stone(int x, int y) {
		super(sprite, x, y);
	}

}
