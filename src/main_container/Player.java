package main_container;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player {
	ArrayList<Boat> boats = new ArrayList<>();
	ArrayList<String> boatsNames = new ArrayList<>();
	int shots[][] = new int[10][10];
	int currentBoats = 0;
	int sunkBoats = 5;
	Set<String> boatsCoordinates = new HashSet<>();//So that i can use .contains() to check them -> May change from string to something else It changed to Set :)
	Set<String> shotsMade = new HashSet<>(); //Found that this is better i believe -> Ended up using it quit a few times
	Set<String> shotsReceived = new HashSet<>();
	Set<String> ownSunkCoordinates = new HashSet<>();
	Set<String> enemySunkCoordinates = new HashSet<>();
	Set<String> enemyFoundCoordinates = new HashSet<>();
	
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
		boatsCoordinates.addAll(boats.get(currentBoats++).getCoordinates());
	}
	public void removeLastBoat() {
		boatsCoordinates.removeAll(boats.get(--currentBoats).getCoordinates());
		boats.removeLast();
	}

	/*
	 * Printable possibilities characters:
	 * Untouched water: ·
	 * Missed shot: □
	 * Hidden ship: ■
	 * Hit ship: ▣
	 * Sunk ship: ☒
	 */
	
	//Print boards -> I believe they are finished
	public void printMyBoats() {
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		for(int f=0;f<10;f++) {
			System.out.printf("%c ",'A'+f);			
			for(int c=0; c<10;c++) {
				if(ownSunkCoordinates.contains(String.format("%c%d", 'A' + f,c)))
					System.out.printf("☒ ");
				else if(boatsCoordinates.contains(String.format("%c%d", 'A' + f,c))&&shotsReceived.contains(String.format("%c%d", 'A' + f,c)))//hit
					System.out.printf("▣ ");
				else if(shotsReceived.contains(String.format("%c%d", 'A' + f,c)))
					System.out.printf("□ ");
				else if(boatsCoordinates.contains(String.format("%c%d", 'A' + f,c)))
					System.out.printf("■ ");
				else
					System.out.printf("· ");
			}
			System.out.println("");
		}
	}
	
	public void printMyShots() {
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		for(int f=0;f<10;f++) {
			System.out.printf("%c ",'A'+f);			
			for(int c=0; c<10;c++) {
				if(enemySunkCoordinates.contains(String.format("%c%d", 'A' + f,c)))
					System.out.printf("☒ ");
				else if(shotsMade.contains(String.format("%c%d", 'A' + f,c))&&enemyFoundCoordinates.contains(String.format("%c%d", 'A' + f,c)))//Hit
					System.out.printf("▣ ");
				else if(shotsMade.contains(String.format("%c%d", 'A' + f,c)))
					System.out.printf("□ ");
				else
					System.out.printf("· ");
			}
			System.out.println("");
		}
	}
	
	
	public int getSunkBoats() {
		return sunkBoats;
	}

	public int getCurrentBoats() {
		return currentBoats;
	}
	
	public Set<String> getShotsMade() {
		return shotsMade;
	}
	
	
		
	
	
}
