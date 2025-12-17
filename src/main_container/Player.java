package main_container;

import java.net.SecureCacheResponse;
import java.util.ArrayList;

public class Player {
	ArrayList<Boat> boats = new ArrayList<>();
	ArrayList<String> boatsNames = new ArrayList<>();
	int shots[][] = new int[10][10];
	int currentBoats = 0;
	int notSunkBoats = 5;
	ArrayList<String> boatsCoordinates = new ArrayList<>();//So that i can use .contains() to check them -> May change from string to something else
	ArrayList<String> shotsMade = new ArrayList<>(); //Have to decide if i'll use either this or the other
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
	
	//Should be complete?
	public void setBoatPosition(int initialRow, int finalRow, int initialCloumn, int finalColumn) {
		boats.get(currentBoats).setCoordinates(initialRow, finalRow, initialCloumn, finalColumn);
		for(int i=0;i<boats.get(currentBoats).getTotalCoordinates();i++) {
			boatsCoordinates.add(
					String.valueOf(boats.get(currentBoats).getCoordinates().get(i).getRow())
					+ String.valueOf(boats.get(currentBoats).getCoordinates().get(i).getColumn())
					);
		}
		currentBoats++;
	}

	public int getNotSunkBoats() {
		return notSunkBoats;
	}

	public int getCurrentBoats() {
		return currentBoats;
	}
	
	public ArrayList<String> getShotsMade() {
		return shotsMade;
	}

	public void removeLastBoat() {
		for(int i=0;i<boats.get(currentBoats).getTotalCoordinates();i++)
			boatsCoordinates.removeLast();
		boats.removeLast();
		currentBoats--;
	}
	
	
		
	
	
}
