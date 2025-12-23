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

	public void addCoordiante(int row, int column) {
		coordinates.add(String.format("%d%d", row, column));
		state.add(false);
	}

	public void removeLastCoordinate() {
		coordinates.removeLast();
		state.removeLast();
	}

	// Should be working now -> Corrected
	public void setCoordinates(int initialRow, int finalRow, int initialColumn, int finalColumn) {
		isHorizontal = (initialRow == finalRow) ? true : false;
		if (isHorizontal) {
			int row = initialRow;
			int column = initialColumn;
			int goingRightOrLeftMultiplier = (finalColumn > initialColumn) ? 1 : -1;
			while (currentCoordinatesSet < totalCoordinates)
				coordinates.add(
						String.format("%d%d", row, (column + goingRightOrLeftMultiplier * currentCoordinatesSet++)));
		} else {
			int column = initialColumn;
			int row = initialRow;
			int goingUpOrDownMultiplier = (finalRow > initialRow) ? 1 : -1;
			while (currentCoordinatesSet < totalCoordinates)
				coordinates
						.add(String.format("%d%d", row + (goingUpOrDownMultiplier * currentCoordinatesSet++), column));
		}
	}

	public boolean isSunk() {
		for (int i = 0; i < totalCoordinates; i++)
			if (!state.get(i))// It exits the function if there even one coordinate not hit
				return false;
		sunk = true;
		return sunk;
	}

}
