package main_container;

import java.util.ArrayList;

/**
 * Represents an individual vessel in the Battleship fleet.
 * Handles internal coordinate mapping, hit tracking for each segment,
 * and calculates the destruction status of the boat.
 */
public class Boat {
	/** List of specific coordinates (e.g., "A1", "A2") assigned to this boat. */
	private ArrayList<String> coordinates = new ArrayList<>();
	
	/** * Parallel list to 'coordinates'. Stores the hit status (true/false) 
	 * for each corresponding segment of the boat. 
	 */
	private ArrayList<Boolean> state = new ArrayList<>();
	
	/** Counter used during placement to track how many segments have been calculated. */
	private int currentCoordinatesSet = 0;
	
	/** Total number of cells this boat occupies. */
	private int totalCoordinates;
	
	/** Flag to indicate if the boat is horizontal (true) or vertical (false). */
	private boolean isHorizontal;
	
	/** Flag to store whether the boat has been completely sunk. */
	private boolean sunk = false;

	/** @return The list of boolean hit states for all segments. */
	public ArrayList<Boolean> getState() {
		return state;
	}
	
	/**
	 * Marks a specific segment of the boat as hit.
	 * @param index The position index within the boat's coordinates list.
	 */
	public void setStateHitAt(int index) {	
		state.set(index, true);
	}

	/** @return The predefined length of this boat. */
	public int getTotalCoordinates() {
		return totalCoordinates;
	}

	/** @return The list of string coordinates occupied by this boat. */
	public ArrayList<String> getCoordinates() {
		return coordinates;
	}

	/**
	 * Constructor for a Boat.
	 * @param totalCoordinates The fixed length of the boat based on its type.
	 */
	public Boat(int totalCoordinates) {
		this.totalCoordinates = totalCoordinates;
	}

	/**
	 * Clears all positioning data from the boat.
	 * Used when a player decides to remove a placed boat or reset placement.
	 */
	public void removeCoordinates() {
		coordinates.clear();
		state.clear();			
		currentCoordinatesSet = 0;
	}

	/**
	 * Populates the boat's coordinates and initializes its hit state.
	 * Uses multipliers to handle both forward (A0 to A3) and backward (A3 to A0) placement.
	 * * @param initialRow Starting row index (0-9).
	 * @param finalRow Ending row index (0-9).
	 * @param initialColumn Starting column index (0-9).
	 * @param finalColumn Ending column index (0-9).
	 */
	public void setCoordinates(int initialRow, int finalRow, int initialColumn, int finalColumn) {
		// Security reset before adding new coordinates
	    coordinates.clear();
	    state.clear();
	    currentCoordinatesSet = 0;
		
		isHorizontal = (initialRow == finalRow) ? true : false;
		
		if (isHorizontal) {
			int row = initialRow;
			int column = initialColumn;
			// Determines direction: 1 for right, -1 for left
			int goingRightOrLeftMultiplier = (finalColumn > initialColumn) ? 1 : -1;
			
			while (currentCoordinatesSet < totalCoordinates) {
				coordinates.add(
						String.format("%c%d", 'A' + row, (column + goingRightOrLeftMultiplier * currentCoordinatesSet++)));
				state.add(false); // All segments start as NOT hit (false)
			}
		} else {
			int column = initialColumn;
			int row = initialRow;
			// Determines direction: 1 for down, -1 for up
			int goingUpOrDownMultiplier = (finalRow > initialRow) ? 1 : -1;
			
			while (currentCoordinatesSet < totalCoordinates) {
				coordinates.add(
						String.format("%c%d", 'A' + row + (goingUpOrDownMultiplier * currentCoordinatesSet++), column));
				state.add(false); // All segments start as NOT hit (false)
			}
		}
	}

	/**
	 * Evaluates the 'state' list to determine if the boat is destroyed.
	 * @return true if every coordinate has been hit, false otherwise.
	 */
	public boolean isSunk() {
		for (int i = 0; i < totalCoordinates; i++)
			// If even one coordinate is not hit, the boat is still afloat
			if (!state.get(i)) return false;
		
		sunk = true;
		return sunk;
	}

}