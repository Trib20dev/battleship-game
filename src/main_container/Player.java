package main_container;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a player in the Battleship game.
 * Manages the player's fleet, shot history, and board rendering logic.
 * It uses several HashSets to optimize coordinate lookup during game turns.
 */
public class Player {
	/** List of Boat objects belonging to the player's fleet. */
	private ArrayList<Boat> boats = new ArrayList<>();
	
	/** Display names for each type of boat in the fleet. */
	private ArrayList<String> boatsNames = new ArrayList<>();
	
	/** Tracker for the number of boats currently positioned on the board. */
	private int currentBoats = 0;
	
	/** Tracker for the number of the player's boats that have been sunk. */
	private int sunkBoats = 0;
	
	/** Global set of all coordinates occupied by the player's fleet. */
	private Set<String> boatsCoordinates = new HashSet<>();
	
	/** Set of coordinates where the player successfully hit an enemy boat. */
	private Set<String> enemyFoundCoordinates = new HashSet<>();
	
	/** Set of all coordinates where the player has fired a shot. */
	private Set<String> shotsMade = new HashSet<>();
	
	/** Set of all coordinates where the enemy has fired at this player. */
	private Set<String> shotsReceived = new HashSet<>();
	
	/** Set of the player's own coordinates that belong to a fully sunk boat. */
	private Set<String> ownSunkCoordinates = new HashSet<>();
	
	/** Set of the enemy's coordinates that belong to a fully sunk boat. */
	private Set<String> enemySunkCoordinates = new HashSet<>();

	/**
	 * Constructor: Initializes the standard fleet for a Battleship game.
	 * Default fleet: Carrier (5), Battleship (4), Cruiser (3), Submarine (3), Destroyer (2).
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
	
	/**
	 * Positions a boat on the board and updates the global coordinate registry.
	 * @param initialRow Starting row index.
	 * @param finalRow Ending row index.
	 * @param initialColumn Starting column index.
	 * @param finalColumn Ending column index.
	 */
	public void setBoatPosition(int initialRow, int finalRow, int initialColumn, int finalColumn) {
		boats.get(currentBoats).setCoordinates(initialRow, finalRow, initialColumn, finalColumn);
		boatsCoordinates.addAll(boats.get(currentBoats++).getCoordinates());
	}
	
	/**
	 * Reverts the last boat placement.
	 * Decrements the counter and removes the boat's coordinates from the global registry.
	 */
	public void removeLastBoat() {
		boatsCoordinates.removeAll(boats.get(--currentBoats).getCoordinates());
		boats.get(currentBoats).removeCoordinates();
	}

	/**
	 * Renders the player's own board to the console.
	 * Symbols used:
	 * · Untouched water
	 * □ Missed enemy shot
	 * ■ Intact ship segment
	 * ▣ Hit ship segment
	 * ☒ Fully sunk ship
	 */
	public void printMyBoats() {
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		for(int f=0; f<10; f++) {
			System.out.printf("%c ", 'A'+f);			
			for(int c=0; c<10; c++) {
				String coord = String.format("%c%d", 'A' + f, c);
				
				if(ownSunkCoordinates.contains(coord))
					System.out.printf("☒ ");
				else if(boatsCoordinates.contains(coord) && shotsReceived.contains(coord))
					System.out.printf("▣ ");
				else if(shotsReceived.contains(coord))
					System.out.printf("□ ");
				else if(boatsCoordinates.contains(coord))
					System.out.printf("■ ");
				else
					System.out.printf("· ");
			}
			System.out.println("");
		}
	}
	
	/**
	 * Renders the board showing the player's offensive progress (shots fired at enemy).
	 * Symbols used:
	 * · Unknown/Unfired water
	 * □ Miss (Shot into empty water)
	 * ▣ Hit (Confirmed hit on enemy vessel)
	 * ☒ Sunk (Confirmed sunk enemy vessel)
	 */
	public void printMyShots() {
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		for(int f=0; f<10; f++) {
			System.out.printf("%c ", 'A'+f);			
			for(int c=0; c<10; c++) {
				String coord = String.format("%c%d", 'A' + f, c);
				
				if(enemySunkCoordinates.contains(coord))
					System.out.printf("☒ ");
				else if(shotsMade.contains(coord) && enemyFoundCoordinates.contains(coord))
					System.out.printf("▣ ");
				else if(shotsMade.contains(coord))
					System.out.printf("□ ");
				else
					System.out.printf("· ");
			}
			System.out.println("");
		}
	}
	
	/** Increments the count of player's boats that have been sunk. */
	public void oneMoreSunkBoat() {
		sunkBoats++;
	}
	
	/** Increments the count of boats positioned during setup. */
	public void oneMoreSetBoat() {
		currentBoats++;
	}
	
	/** @return Total number of player's boats already sunk. */
	public int getSunkBoats() {
		return sunkBoats;
	}

	/** @return Number of boats currently placed on the board. */
	public int getCurrentBoats() {
		return currentBoats;
	}
	
	/** @return Set of all coordinates targeted by this player. */
	public Set<String> getShotsMade() {
		return shotsMade;
	}

	/** @return Set of all coordinates where this player received fire. */
	public Set<String> getShotsReceived() {
		return shotsReceived;
	}

	/** @return Set of coordinates belonging to this player's fleet. */
	public Set<String> getBoatsCoordinates() {
		return boatsCoordinates;
	}

	/** @return List of Boat objects in the fleet. */
	public ArrayList<Boat> getBoats() {
		return boats;
	}

	/** @return Set of coordinates of this player's boats that are sunk. */
	public Set<String> getOwnSunkCoordinates() {
		return ownSunkCoordinates;
	}

	/** @return Set of enemy coordinates that have been confirmed as sunk. */
	public Set<String> getEnemySunkCoordinates() {
		return enemySunkCoordinates;
	}

	/** @return Set of enemy coordinates where a hit was confirmed. */
	public Set<String> getEnemyFoundCoordinates() {
		return enemyFoundCoordinates;
	}

	/** @return List of boat names (Carrier, Battleship, etc.). */
	public ArrayList<String> getBoatsNames() {
		return boatsNames;
	}
}