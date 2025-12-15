package main_container;

import java.util.ArrayList;

public class Boat {
	private ArrayList<Coordinates> coordinates;
	//(state) ? hit : not hit 
	private ArrayList<Boolean> estado;
	
	public void addCoordiante(int row, int column) {
		coordinates.add(new Coordinates(row, column));
		estado.add(false);
	}
	
	public void removeLastCoordinate() {
		coordinates.removeLast();
		estado.removeLast();
	}
	
}