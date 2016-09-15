package server;

import java.awt.Color;
import java.util.*;

public class Map {
	public ArrayList<Ship> ships;
	int width, height;
	int[][] occupier;
	boolean[][] isKnown;
	Color color;
	
	public static String[][] startShips = {
			{"Patrol Boat", "2", "2"},
			{"Battleship", "3", "2"},
			{"Submarine", "3", "1"},
			{"Destroyer", "4", "1"},
			{"Carrier", "5", "1"}
	};

	public Map(PLAYER playerType, Color playerColor, int inWidth, int inHeight) {
		width = inWidth;
		height = inHeight;
		color = playerColor;
		ships = new ArrayList<Ship>();
		
		clearMap();
		
		if (playerType == PLAYER.COMPUTER) {
			generateRandomShips();
		}
	}
	
	public void clearMap() {
		occupier = new int[width][height];
		isKnown = new boolean[width][height];
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				occupier[i][j] = -1;
				isKnown[i][j] =  false;
			}
		}
	}

	private void generateRandomShips() {
		ORIENT orient;
		String name;
		int x, y, length, id;
		boolean isOverlapping;
		int xRange, yRange;
		
		for (String[] element : startShips) {
			name = element[0];
			length = Integer.parseInt(element[1]);
			int number = Integer.parseInt(element[2]);
			
			for (int i = 0; i<number; i++) {
				id = ships.size();
				if (Math.random() > 0.5f) {
					orient = ORIENT.HORIZ;
					xRange = width-length;
					yRange = height;
				} else {
					orient = ORIENT.VERT;
					xRange = width;
					yRange = height - length;
				}
				
				isOverlapping = false;
				do {
					x = randomInt(xRange);
					y = randomInt(yRange);
					isOverlapping = checkOccupation(x,y, orient, length);
				} while (isOverlapping);
				setOccupation(x,y, orient, length, id);
				
				System.out.println("Adding: "+name+","+x+","+y+","+orient+","+length);
				ships.add(new Ship(name, x, y, orient, length));
			}
		}
		
	}
	
	private void setOccupation(int x, int y, ORIENT orient, int length, int id) {
		if (orient == ORIENT.HORIZ) {
			for (int i=0; i<length; i++) {
				occupier[x+i][y] = id;
			}
		} else {
			for (int j=0; j<length; j++) {
				occupier[x][y+j] = id;
			}

		}
	}
	
	private boolean checkOccupation(int x, int y, ORIENT orient, int length) {
		if (orient == ORIENT.HORIZ) {
			for (int i=0; i<length; i++) {
				if (occupier[x+i][y] >= 0) {
					return true;
				}
			}
		} else {
			for (int j=0; j<length; j++) {
				if (occupier[x][y+j] >= 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkHit(int x, int y) {
		boolean result = false;
		int shipID = occupier[x][y];
		if (shipID >= 0) {
			Ship targetShip = ships.get(shipID);
			targetShip.hit();
			result = true;
		}
		isKnown[x][y] = true;
		return result;
	}

	public static int randomInt(int range) {
		return (int) Math.floor( Math.random()*range );
	}
}
