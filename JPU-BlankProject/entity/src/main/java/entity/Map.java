package entity;

import MobileElement.Diamond;
import MobileElement.Ennemy;
import MobileElement.Player;
import MobileElement.Stone;
import MotionlessElement.Air;
import MotionlessElement.Dirt;
import MotionlessElement.Exit;
import MotionlessElement.Wall;

/**
 * The Class HelloWorld.
 *
 * @author Jean-Aymeric Diet
 */
public class Map extends Entity{

	/** The id. */
	private int	id;

	/** The message. */
	private String	content;
	
	private Entity[][] mapEntity;
	
	private int count = 0;

	public Map(final int id,  final String message) {
		super();
		this.setId(id);
		this.setContent(message);
		createMap();
	}


	public Map() {
		this(1, "");
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getWidthMap() {
		String[] map = this.getContent().split("\n");
		return map[0].length() - 1;
	}
	
	public int getHeightMap() {
		String[] map = this.getContent().split("\n");
		return map.length;
	}
	
	public String getContent() {
		return this.content;
	}

	public void setContent(final String message) {
		this.content = message;
	}
	
	public Entity[][] getEntityMap(){
		return this.mapEntity;
	}
	
	public Player getPlayer() {
		Entity[][] entity = this.getEntityMap();
		for (int y = 0; y < getHeightMap(); y++) {
            for (int x = 0; x < getWidthMap(); x++) {
                if (entity[x][y] instanceof Player) {
                	return (Player) entity[x][y];
                }
            }
        }
		return null;
	}
	
	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}
	
	public boolean isCollision(Entity[][] playerPos, int x, int y) {
		if(playerPos[x][y] instanceof Stone) {
            return true;
        } else if(playerPos[x][y] instanceof Wall) {
        	return true;
        } else {
            return false;
        }
	}
	
	public boolean isDiamond(Entity[][] playerPos, int x, int y) {
		if(playerPos[x][y] instanceof Diamond) {
			return true;
		} else {
			return false;
		}
	}
	
	public void moveUp() {
		boolean collision = isCollision(getEntityMap(), getPlayer().getX(), getPlayer().getY()-1);
		boolean isDiamond = isDiamond(getEntityMap(), getPlayer().getX(), getPlayer().getY()-1);
		getPlayer().updateSpritePlayer('Z');
		if (!collision) {
				getEntityMap()[getPlayer().getX()][getPlayer().getY()-1] = getEntityMap()[getPlayer().getX()][getPlayer().getY()];
				getEntityMap()[getPlayer().getX()][getPlayer().getY()] = new Air(getPlayer().getX(),getPlayer().getY());
				getPlayer().setY(getPlayer().getY()-1);
				if (isDiamond) {
					setCount(getCount() + 1);
					System.out.println(count);
				}
		} else {}
	}
	



	public void moveDown() {
		boolean collision = isCollision(getEntityMap(), getPlayer().getX(), getPlayer().getY()+1);
		boolean isDiamond = isDiamond(getEntityMap(), getPlayer().getX(), getPlayer().getY()+1);
		getPlayer().updateSpritePlayer('S');
		if (!collision) {
				getEntityMap()[getPlayer().getX()][getPlayer().getY()+1] = getEntityMap()[getPlayer().getX()][getPlayer().getY()];
				getEntityMap()[getPlayer().getX()][getPlayer().getY()] = new Air(getPlayer().getX(),getPlayer().getY());
				getPlayer().setY(getPlayer().getY()+1);
				if (isDiamond) {
					setCount(getCount() + 1);
					System.out.println(count);
				}
		} else {}
	}
	
	public void moveLeft() {
		boolean collision = isCollision(getEntityMap(), getPlayer().getX()-1, getPlayer().getY());
		boolean isDiamond = isDiamond(getEntityMap(), getPlayer().getX()-1, getPlayer().getY());
		getPlayer().updateSpritePlayer('Q');
		if (!collision) {
				getEntityMap()[getPlayer().getX()-1][getPlayer().getY()] = getEntityMap()[getPlayer().getX()][getPlayer().getY()];
				getEntityMap()[getPlayer().getX()][getPlayer().getY()] = new Air(getPlayer().getX(),getPlayer().getY());
				getPlayer().setX(getPlayer().getX()-1);
				if (isDiamond) {
					setCount(getCount() + 1);
					System.out.println(count);
				}
		} else {}
	}
	
	public void moveRight() {
		boolean collision = isCollision(getEntityMap(), getPlayer().getX()+1, getPlayer().getY());
		boolean isDiamond = isDiamond(getEntityMap(), getPlayer().getX()+1, getPlayer().getY());
		getPlayer().updateSpritePlayer('D');
		if (!collision) {
				getEntityMap()[getPlayer().getX()+1][getPlayer().getY()] = getEntityMap()[getPlayer().getX()][getPlayer().getY()];
		        getEntityMap()[getPlayer().getX()][getPlayer().getY()] = new Air(getPlayer().getX(),getPlayer().getY());
		        getPlayer().setX(getPlayer().getX()+1);
		        if (isDiamond) {
					setCount(getCount() + 1);
					System.out.println(count);
				}
		} else {}
	}
	
	public void createMap() {
		String map = this.getContent(); //Loading map
		if (getHeightMap() >= 1 && getWidthMap() >= 1) {
			this.mapEntity = new Entity[this.getWidthMap()][this.getHeightMap()];
			for (int y = 0; y < getHeightMap(); y++) {
				String[] finalMap = map.split("\n");
                for (int x = 0; x < getWidthMap(); x++) {
                	switch (finalMap[y].toCharArray()[x]) {
					case 'W':
						mapEntity[x][y] = new Wall(x, y);
						break;
					case 'T':
						mapEntity[x][y] = new Dirt(x, y);
						break;
					case 'P':
						mapEntity[x][y] = new Player(x, y);
						break;
					case 'O':
						mapEntity[x][y] = new Stone(x, y);
						break;
					case 'E':
						mapEntity[x][y] = new Ennemy(x, y);
						break;
					case 'A':
						mapEntity[x][y] = new Air(x, y);
						break;
					case 'D':
						mapEntity[x][y] = new Diamond(x, y);
						break;
					case 'X':
						mapEntity[x][y] = new Exit(x, y);
						break;
					default:
						break;
					}
                }
            }
		}
	}
}
