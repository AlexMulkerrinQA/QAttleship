package server;

public class Ship {
	String name;
	int x,y; 
	ORIENT orientation;
	boolean isAlive;
	int health;
	int length;

	public Ship(String inName, int inX, int inY, ORIENT inOrient, int inLength) {
		name = inName;
		x = inX;
		y = inY;
		orientation = inOrient;
		isAlive = true;
		health = inLength;
		length = inLength;
	}
	
	public boolean hit() {
		health--;
		if (health == 0) {
			isAlive = false;
			System.out.println(name+" has been sunk!");
			return true;
		}
		System.out.println(name+" has been hit! ("+health+"/"+length+")");
		return false;
	}

}
