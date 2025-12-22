package main_container;

import java.net.SecureCacheResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player {
	ArrayList<Boat> boats = new ArrayList<>();
	ArrayList<String> boatsNames = new ArrayList<>();
	int shots[][] = new int[10][10];
	int currentBoats = 0;
	int notSunkBoats = 5;
	ArrayList<String> boatsCoordinates = new ArrayList<>();//So that i can use .contains() to check them -> May change from string to something else
	Set<String> shotsMade = new HashSet<>(); //Found that this is better i believe
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

	/*
	 * Printable possibilities characters:
	 * Untouched water: ·
	 * Missed shot: □
	 * Hidden ship: ■
	 * Hit ship: ▣
	 * Sunk ship: ☒
	 */
	
	//Print boards
	public void printMyBoats() {
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		for(int f=0;f<10;f++) {
			System.out.printf("%c ",'A'+f);
			for(int c=0; c<10;c++) {
				if(ownSunkCoordinates.contains(String.format("%d%d", f,c)))
					System.out.printf("☒ ");
				else if(boatsCoordinates.contains(String.format("%d%d", f,c))&&)
					System.out.printf("■ ");
				else
					System.out.printf("· ");
			}
			System.out.println("");
		}
	}
	
	public void printMyShots() {
		for(int f=0;f<10;f++) {
			for(int c=0; c<10;c++) {
				if(boatsCoordinates.contains(String.format("%d%d", f,c)))
					System.out.printf("■ ");
				else
					System.out.printf("· ");
			}
			System.out.println("");
		}
	}
	
	
	public int getNotSunkBoats() {
		return notSunkBoats;
	}

	public int getCurrentBoats() {
		return currentBoats;
	}
	
	public Set<String> getShotsMade() {
		return shotsMade;
	}

	public void removeLastBoat() {
		currentBoats--;
		for(int i=0;i<boats.get(--currentBoats).getTotalCoordinates();i++)
			boatsCoordinates.removeLast();
		boats.removeLast();
	}
	
	
		
	
	
}
