package server;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class View  extends JPanel {
	Game targetGame;
	int sqSize;

	public View(Game inGame) {
		targetGame = inGame;
		sqSize = 16;
		
		setPreferredSize( new Dimension(targetGame.width*sqSize*2, targetGame.height*sqSize));

	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintMap(g);
        paintShips(g);
        paintShots(g);
    }

	

	private void paintMap(Graphics g) {
		g.setColor(Color.decode("#3344ee"));
		int totalWidth = targetGame.width*sqSize;
		int totalHeight = targetGame.height*sqSize;
		g.fillRect(0,0, totalWidth, totalHeight);
		
		g.fillRect(totalWidth, 0, totalWidth, totalHeight);
		
	}

	private void paintShips(Graphics g) {
		int num =0;
		ArrayList<Map> playAreas = targetGame.playAreas;
		for (Map currentMap : playAreas) {
			int offsetX = targetGame.width*sqSize*num;
			for (Ship ship : currentMap.ships) {
				if (ship.isAlive) {
					g.setColor(currentMap.color);
				} else {
					g.setColor(Color.DARK_GRAY);
				}
				for (int i=0; i<ship.length; i++) {
					if (ship.orientation == ORIENT.HORIZ) {
						
						g.fillRect(sqSize*(ship.x+i)+offsetX, sqSize*ship.y, sqSize-1, sqSize-1);
					} else {
						g.fillRect(sqSize*ship.x+offsetX, sqSize*(ship.y+i), sqSize-1, sqSize-1);
					}
				}
			}
			num++;
		}
		
	}
	
	private void paintShots(Graphics g) {
		int num = 0;
		g.setColor(Color.BLACK);
		ArrayList<Map> playAreas = targetGame.playAreas;
		for (Map currentMap : playAreas) {
			int offsetX = targetGame.width*sqSize*num;
			for (int i=0; i<currentMap.width; i++) {
				for (int j=0; j<currentMap.width; j++) {
					
				
					if (currentMap.isKnown[i][j]) {
						if (currentMap.occupier[i][j] >=0) {
							g.setColor(Color.YELLOW);
						} else {
							g.setColor(Color.BLACK);
						}
						g.fillRect(sqSize*i+offsetX+2, sqSize*j+2, sqSize-5, sqSize-5);
					}
				}
			}
			num++;
		}
		
	}

	
}
