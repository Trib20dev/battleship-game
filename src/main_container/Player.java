package main_container;

import java.util.ArrayList;

public class Player {
	ArrayList<Boat> boats;
	ArrayList<String> boatsNames;
	int shots[][] = new int[10][10];
	int currentBoats = 0;
	
	/* 
	 * Carrier 5 coordinates
	 * Battleship 4 coordinates
	 * Cruiser 3 coordinates
	 * Submarine 3 coordinates
	 * Destroyer 2 coordinates
	 */
	public Player() {
		boats.add(new Boat(5));
		boatsNames.add("Carrier");
		boats.add(new Boat(4));
		boatsNames.add("Battleship");
		boats.add(new Boat(3));
		boatsNames.add("Cruiser");
		boats.add(new Boat(3));
		boatsNames.add("Submarine");
		boats.add(new Boat(2));
		boatsNames.add("Destroyer");
	}
	
	public void setBoatPosition(int initialRow, int finalRow, int initialCloumn, int finalColumn) {
		boats.get(currentBoats++).setCoordinates(initialRow, finalRow, initialCloumn, finalColumn);
	}
		
}
