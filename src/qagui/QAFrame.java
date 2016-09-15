package qagui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class QAFrame  extends JFrame implements KeyListener {
	public JPanel pane;
	
	public QAPanel appInfoPanel;
	public QAPanel inputPanel;
	public QAPanel processPanel;
	public QAPanel outputPanel;
	public QAPanel sysInfoPanel;
	
	private final  String[][] defaults = {
			{"Application info", "<Description of application goes here>"},
			{"Input", "<input interface elements>"},
			{"Process", "<process details>"},
			{"Output", "<output interface elements>"},
			{"System info", "<system state infobar>"}
	};
	
	public QAFrame(String windowTitle) {
		super(windowTitle);
		setIconImage(new ImageIcon("QA Icon.png").getImage());
				
		setSize(480, 360);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());

		// default interface elements
		appInfoPanel = new QAPanel(defaults[0][0], defaults[0][1]);
		inputPanel = new QAPanel(defaults[1][0], defaults[1][1]);
		processPanel = new QAPanel(defaults[2][0], defaults[2][1]);
		outputPanel = new QAPanel(defaults[3][0], defaults[3][1]);
		sysInfoPanel = new QAPanel(defaults[4][0], defaults[4][1]);
		
		pane.add(appInfoPanel, BorderLayout.NORTH);
		pane.add(inputPanel, BorderLayout.WEST);
		pane.add(processPanel, BorderLayout.CENTER);
		pane.add(outputPanel, BorderLayout.EAST);
		pane.add(sysInfoPanel, BorderLayout.SOUTH);
		
		add(pane);
		pack();
		setVisible(true);
	}
	
	// generic panel with vertical box layout
	public class QAPanel extends JPanel {
		public JLabel contents;
		
		public QAPanel(String title, String text) {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			contents = new Placeholder(text);
			add(contents);
			setBorder(BorderFactory.createTitledBorder(title));
		}
	}
	
	// dummy text for interface area which hasn't been populated
	public class Placeholder extends JLabel {
		public Placeholder(String text) {
			super(text, SwingConstants.CENTER);
			setForeground(Color.GRAY);
		}
	}
	
	// keyboard event listeners
	public void keyPressed(KeyEvent e) { handleKeys(); }
	public void keyReleased(KeyEvent e) { handleKeys(); }
	public void keyTyped(KeyEvent e) { handleKeys(); }
		
	public abstract void handleKeys();
}

