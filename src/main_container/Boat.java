package main_container;

import java.util.ArrayList;

public class Boat {
	private ArrayList<String> coordinates = new ArrayList<>();
	// (state) ? hit : not hit
	private ArrayList<Boolean> state = new ArrayList<>();
	private int currentCoordinatesSet = 0;
	private int totalCoordinates;
	private boolean isHorizontal;
	private boolean sunk = false;

	public ArrayList<Boolean> getState() {
		return state;
	}
	
	public void setStateHitAt(int index) {	
		state.set(index,true);
	}

	public int getTotalCoordinates() {
		return totalCoordinates;
	}

	public ArrayList<String> getCoordinates() {
		return coordinates;
	}

	public Boat(int totalCoordinates) {
		this.totalCoordinates = totalCoordinates;
	}

	//Is this ever used?
	public void addCoordiante(int row, int column) {
		coordinates.add(String.format("%d%d", row, column));
		state.add(false);
	}

	public void removeCoordinates() {
		coordinates.clear();
		state.clear();			
		currentCoordinatesSet=0;
	}

	// Should be working now -> Corrected -> Changed the coordinates format to A0 from 00
	public void setCoordinates(int initialRow, int finalRow, int initialColumn, int finalColumn) {
		// We clear for security before adding the new ones
	    coordinates.clear();
	    state.clear();
	    currentCoordinatesSet = 0;
		
		isHorizontal = (initialRow == finalRow) ? true : false;
		if (isHorizontal) {
			int row = initialRow;
			int column = initialColumn;
			int goingRightOrLeftMultiplier = (finalColumn > initialColumn) ? 1 : -1;
			while (currentCoordinatesSet < totalCoordinates) {
				coordinates.add(
						String.format("%c%d", 'A' + row, (column + goingRightOrLeftMultiplier * currentCoordinatesSet++)));
				state.add(false);
			}
		} else {
			int column = initialColumn;
			int row = initialRow;
			int goingUpOrDownMultiplier = (finalRow > initialRow) ? 1 : -1;
			while (currentCoordinatesSet < totalCoordinates) {
				coordinates.add(
						String.format("%c%d", 'A' + row + (goingUpOrDownMultiplier * currentCoordinatesSet++), column));
				state.add(false);
			}
		}
	}

	public boolean isSunk() {
		for (int i = 0; i < totalCoordinates; i++)
			if (!state.get(i))return false;// It exits the function if there even one coordinate not hit
		sunk = true;
		return sunk;
	}

}
