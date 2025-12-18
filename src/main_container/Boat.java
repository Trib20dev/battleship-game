package main_container;

import java.util.ArrayList;

public class Boat {
	private ArrayList<String> coordinates = new ArrayList<>();
	//(state) ? hit : not hit 
	private ArrayList<Boolean> state = new ArrayList<>();
	private int currentCoordinatesSet = 0;
	private int totalCoordinates;
	private boolean isHorizontal;
	private boolean hasSunk = false;
	
	public ArrayList<Boolean> getState(){
	  return state;
	}
	
	public int getTotalCoordinates() {
		return totalCoordinates;
	}

	public ArrayList<String> getCoordinates() {
		return coordinates;
	}

	public boolean getHasSunk() {
		return hasSunk;
	}

	public Boat(int totalCoordinates){
	  this.totalCoordinates = totalCoordinates;
	}
	
	public void addCoordiante(int row, int column) {
		coordinates.add(String.format("%d%d", row,column));
		state.add(false);
	}
	
	public void removeLastCoordinate() {
		coordinates.removeLast();
		state.removeLast();
	}
	
	//Should be working now -> Corrected 
	public void setCoordinates(int initialRow, int finalRow, int initialColumn, int finalColumn){
	  isHorizontal = (initialRow==finalRow)?true:false;
	  if(isHorizontal){
		  int row = initialRow;
		  int column = initialColumn;
	    int goingRightOrLeftIncrement = (finalColumn>initialColumn) ? 1 : -1;
	    while(currentCoordinatesSet<totalCoordinates)
	      coordinates.add(String.format("%d%d",row,(column + goingRightOrLeftIncrement*currentCoordinatesSet++)));
	  } else{
		  int column = initialColumn;
		  int row = initialRow;
		  int goingUpOrDownIncrement = (finalRow>initialRow) ? 1 : -1;
		  while(currentCoordinatesSet<totalCoordinates)
		      coordinates.add(String.format("%d%d",row + (goingUpOrDownIncrement*currentCoordinatesSet++),column));
	  }
	}
	
	private void hasItSunk() {
		for(int i=0;i<totalCoordinates;i++)
			//It exits the function if there even one coordinate not hit
			if(state.get(i)) return;
		
		hasSunk = true;
	}
	
	
	
}
