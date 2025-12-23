package main_container;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player {
	ArrayList<Boat> boats = new ArrayList<>();
	ArrayList<String> boatsNames = new ArrayList<>();
	int shots[][] = new int[10][10];
	int currentBoats = 0;
	int sunkBoats = 0;
	//I realized i could just initialize them like this -> In fact i could not
	Set<String> boatsCoordinates = new HashSet<>();
	Set<String> enemyFoundCoordinates = new HashSet<>();
	Set<String> shotsMade = new HashSet<>();
	Set<String> shotsReceived = new HashSet<>();
	Set<String> ownSunkCoordinates = new HashSet<>();
	Set<String> enemySunkCoordinates = new HashSet<>();

	
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
	//I need to look trhough this -> Should be done
	public void removeLastBoat() {
		boatsCoordinates.removeAll(boats.get(--currentBoats).getCoordinates());
		boats.get(currentBoats).removeCoordinates();
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
	
	public void oneMoreSunkBoat() {
		sunkBoats++;
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

	public Set<String> getShotsReceived() {
		return shotsReceived;
	}

	public Set<String> getBoatsCoordinates() {
		return boatsCoordinates;
	}

	public ArrayList<Boat> getBoats() {
		return boats;
	}

	public Set<String> getOwnSunkCoordinates() {
		return ownSunkCoordinates;
	}

	public Set<String> getEnemySunkCoordinates() {
		return enemySunkCoordinates;
	}

	public Set<String> getEnemyFoundCoordinates() {
		return enemyFoundCoordinates;
	}

	public ArrayList<String> getBoatsNames() {
		return boatsNames;
	}

	
	
}
