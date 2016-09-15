package server;

import java.awt.Color;
import java.util.*;




public class Game {
	STATE gameState;
	public ArrayList<Map> playAreas;
	int width, height;
	int frame;
	boolean isQuit = false;
	int currentTurn = 0;

	public Game(int width, int height) {
		createNewGame(width, height);
		frame = 0;
	}
	
	public void createNewGame(int inWidth, int inHeight) {
		width = inWidth;
		height = inHeight;
		playAreas = new ArrayList<Map>();
		playAreas.add(new Map(PLAYER.COMPUTER, Color.GREEN, width, height));
		playAreas.add(new Map(PLAYER.COMPUTER, Color.RED, width, height));
	}
	
	public void update() {
		int x,y;
		frame++;
		currentTurn++;
		if (currentTurn >= playAreas.size()) {
			currentTurn = 0;
		}
		Map currentMap = playAreas.get(currentTurn);
		//for (Map currentMap : playAreas) {
			boolean hasHit = true;
			x = Map.randomInt(width);
			y = Map.randomInt(height);
			while (hasHit == true) {
				
				do {
					int[] newPos = randomDirec(x, y);
					x = newPos[0];
					y = newPos[1];
					//x = Map.randomInt(width);
					//y = Map.randomInt(height);
				} while (currentMap.isKnown[x][y]);
				
				hasHit = currentMap.checkHit(x, y);
			}
		//}
		
	}

	public boolean checkGameOver() {
		boolean hasShip;
		for (Map currentMap : playAreas) {
			hasShip = false;
			for (Ship ship : currentMap.ships) {
				if (ship.isAlive) hasShip = true;
			}
			if (hasShip == false) {
				return true;
			}
		}
		return false;
	}
	
	public int[] randomDirec(int x, int y) {
		int[] newPos = {x, y};
		switch (Map.randomInt(4)) {
			case 0:
				newPos[0] += 1;
				if (newPos[0]>=width) newPos[0] = width-1;
				break;
			case 1:
				newPos[0] -= 1;
				if (newPos[0]<0) newPos[0] = 0;
				break;
			case 2:
				newPos[1] += 1;
				if (newPos[1]>=height) newPos[1] = height-1;
				break;
			case 3:
				newPos[1] -= 1;
				if (newPos[1]<0) newPos[1] = 0;
				break;
			
		}
		return newPos;
	}

}
