package server;

import javax.swing.*;

import qagui.QAFrame;

public class Application extends QAFrame implements Runnable{
	Game game;
	boolean isGameOver = false;
	View mapPanel;
	JList<String> inList;
	JTextArea outLog;
	JLabel gameInfo;
	JLabel systemInfo;
	
	
	public static void main(String[] args) {
		new Application();
	}
	
	public Application() {
		super("QA Battleships Game");
		
		game = new Game(13, 13);
		setupInfoPanels();
		Thread t = new Thread(this);
		t.start();
		
		
		

	}
	
	private void setupInfoPanels() {
		appInfoPanel.contents.setText("Current game state: ");
		inputPanel.contents.setText("Ships to place");
		processPanel.contents.setText("Your battlefield");
		outputPanel.contents.setText("Game Events");
		sysInfoPanel.contents.setText("Place your fleet commander!");
	
		String[] shipNames = new String[Map.startShips.length];
		for (int i=0; i<Map.startShips.length; i++) {
			shipNames[i] = Map.startShips[i][0];
		}
		inList = new JList<String>(shipNames);
		inList.setSelectedIndex(0);
		inputPanel.add(inList);
		
		outLog = new JTextArea();
		outputPanel.add(outLog);
		outLog.setText("-");
		
		mapPanel = new View(game);
		mapPanel.repaint();
		processPanel.add(mapPanel);
		pack();
		
	}

	@Override
	public void handleKeys() {
		// TODO Auto-generated method stub
		
	}
	
	public void run() {
		while(!isGameOver) {
			game.update();
			isGameOver = game.checkGameOver();
			mapPanel.repaint();
			sysInfoPanel.contents.setText("Place your fleet commander! "+game.frame);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mapPanel.repaint();
		sysInfoPanel.contents.setText("Game Over!");
	}
}
