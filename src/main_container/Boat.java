package main_container;

import java.util.ArrayList;

public class Boat {
	private ArrayList<Coordinates> coordinates;
	//(state) ? hit : not hit 
	private ArrayList<Boolean> state;
	private int currentCoordinatesSet = 0;
	private int maxCoordinates;
	private boolean isHorizontal;
	
	public ArrayList<Boolean> getState(){
	  return state:
	}
	
	public Boat(int maxCoordinates){
	  this.maxCoordinates = maxCoordinates;
	}
	
	public void addCoordiante(int row, int column) {
		coordinates.add(new Coordinates(row, column));
		estado.add(false);
	}
	
	public void removeLastCoordinate() {
		coordinates.removeLast();
		estado.removeLast();
	}
	
	public void setCoordinates(int initialRow, int finalRow, int initialColumn, int finalColumn){
	  isHorizontal = (initialRow==finalRow)?true:false;
	  if(isHorizontal){
	    int goingRightOrLeftIncrement = (finalColumn>initialColumn) ? 1 : -1;
	    while(currentCoordinatesSet<maxCoordinates)
	      coordinates.add(new Coordinates(initialRow,initialColumn + goingUpOrDownIncrement*currentCoordinatesSet++))
	  } else{
	    
	  }
	  
	}
	
}
