package main_container;

import java.util.ArrayList;

public class Player {
	ArrayList<Boat> boats;
	ArrayList<String> boatsNames;
	//Adding the boats names
	{
	boatsNames.add("Carrier");
	boatsNames.add("Battleship");
	boatsNames.add("Cruiser");
	boatsNames.add("Submarine");
	boatsNames.add("Destroyer");
	}
	
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
		boats.add(new Boat(4));
		boats.add(new Boat(3));
		boats.add(new Boat(3));
		boats.add(new Boat(2));
	}
	
	public void setBoatPosition(int initialRow, int finalRow, int initialCloumn, int finalColumn) {
		boats.get(currentBoats++).setCoordinates(initialRow, finalRow, initialCloumn, finalColumn);
	}

}
