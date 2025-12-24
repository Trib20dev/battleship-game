package main_container;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 * Main controller class for the Battleship game.
 * Handles the game setup, player turns, coordinate validation, 
 * and the main game loop until a winner is determined.
 */
public class Start {
	
	/** Static scanner for user input throughout the execution. */
	static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Main execution thread. Manages the two-phase game structure:
	 * 1. Placement phase: Players position their fleet.
	 * 2. Combat phase: Players take turns shooting until a fleet is destroyed.
	 */
	public static void main(String[] args) {
		Player player1 = new Player();
		Player player2 = new Player();
		Player[] players = {player1, player2};
		int currentPlayer = 0; // 0 for player 1, 1 for player 2
		int currentEnemy = 1;
		
		System.out.println("The boats shall be created with the format:\nA0 A4\nWhere you indicate from which space to which space your boat exists");
		
		// --- BOAT PLACEMENT PHASE ---
		for(int i = 0; i < 2; i++) {
			System.out.printf("It's player %d's turn\n", (i == 0) ? 1 : 2);
			
			int boatsAccesIndex;
			String boatName;
			ArrayList<Boat> boats;
			int boatLength;
			
			while(players[i].getCurrentBoats() != 5) {
				boats = players[i].getBoats();
				boatsAccesIndex = players[i].getCurrentBoats();
				boatName = players[i].getBoatsNames().get(boatsAccesIndex);
				boatLength = boats.get(boatsAccesIndex).getTotalCoordinates();
				boolean boatRemoved = false;
				
				String input;
				do {
					System.out.printf("Where will the %s which ocupies %d spaces be?\n", boatName, boatLength);
					input = scanner.nextLine().toUpperCase();
					
					// Allows user to undo the previous placement
					if(input.matches("(?i)remove boat") && boatsAccesIndex > 0) {
						System.out.println("You removed your last boat");
						players[i].removeLastBoat();
						boatRemoved = true;
						break;
					}
					
				} while(!input.matches("^[A-J][0-9] [A-J][0-9]$"));

				if(boatRemoved) continue;
				
				// Validate positioning logic (overlap, adjacency, and length)
				if(!boatMayExist(input, players[i].getBoatsCoordinates(), boatLength)) {
					System.out.println("Not valid, try again");
					continue;
				}
				
				// Convert char coordinates to integer indices for storage
				players[i].setBoatPosition(
						(int)(input.charAt(0) - 'A'), // Initial Row
						(int)(input.charAt(3) - 'A'), // Final Row
						(int)(input.charAt(1) - '0'), // Initial Column
						(int)(input.charAt(4) - '0')); // Final Column
				
				players[i].printMyBoats();
			}
			// Clear terminal visual space between players
			System.out.println("\n".repeat(50));
		}
		
		// --- COMBAT PHASE ---
		while(player1.getSunkBoats() != 5 && player2.getSunkBoats() != 5) {
			System.out.printf("It's player %d's turn\n", (currentPlayer == 0) ? 1 : 2);
	
			String input;
			do {
				System.out.println("what will you do?");
				input = scanner.nextLine().toUpperCase();
				
				// Non-ending turn commands for board checking
				if(input.matches("(?i).*see my boats.*")) 
					players[currentPlayer].printMyBoats();
				
				if(input.matches("(?i).*see my shots.*")) 
					players[currentPlayer].printMyShots();
				
			} while(!input.matches("^[A-J][0-9]$"));
			
			// Duplicate shot validation
			if(players[currentPlayer].getShotsMade().contains(input)) {
				System.out.printf("You already shot at %s, not the wisest move", input);
				// Penalty: lose turn
				currentPlayer = 1 - currentPlayer;
				currentEnemy = 1 - currentEnemy;
				continue;
			}
			
			System.out.printf("You shot at: %s\n", input);
			players[currentPlayer].getShotsMade().add(input);
			players[currentEnemy].getShotsReceived().add(input);
			
			// Hit detection logic
			if(players[currentEnemy].getBoatsCoordinates().contains(input)) {
				System.out.println("Hit!");
				players[currentPlayer].getEnemyFoundCoordinates().add(input);
				
				for(int i = 0; i < players[currentEnemy].getBoats().size(); i++) {
					if(players[currentEnemy].getBoats().get(i).getCoordinates().contains(input)) {
						
						// Identify exact segment index and mark as hit
						for(int j = 0; j < players[currentEnemy].getBoats().get(i).getCoordinates().size(); j++)
							if(players[currentEnemy].getBoats().get(i).getCoordinates().get(j).contains(input))
								players[currentEnemy].getBoats().get(i).setStateHitAt(j);
							
						// Check if the specific boat is now destroyed
						if(players[currentEnemy].getBoats().get(i).isSunk()) {
							System.out.printf("You sunk your enemy's %s\n", players[currentEnemy].getBoatsNames().get(i));
							players[currentPlayer].getEnemySunkCoordinates().addAll(players[currentEnemy].getBoats().get(i).getCoordinates());
							players[currentEnemy].getOwnSunkCoordinates().addAll(players[currentEnemy].getBoats().get(i).getCoordinates());
							players[currentEnemy].oneMoreSunkBoat();
						}
					}
				}
			} else {
				System.out.println("Miss!");
			}
			
			// Switch turns
			currentPlayer = 1 - currentPlayer;
			currentEnemy = 1 - currentEnemy;
		}
		
		// End game notification
		System.out.println("GAME OVER!");
		System.out.printf("Player %d is the winner!", (player1.getSunkBoats() == 5) ? 2 : 1);
	}

	/**
	 * Comprehensive validation for boat placement.
	 * Checks: diagonal restriction, correct length, and proximity to other boats.
	 * * @param input String with placement range (e.g., "A0 A4")
	 * @param boatsCoordinates Set of coordinates already occupied by other boats
	 * @param boatLength Required length for the current boat being placed
	 * @return true if the boat can be legally placed, false otherwise
	 */
	private static boolean boatMayExist(String input, Set<String> boatsCoordinates, int boatLength) {
		int initialRow 		= (int)(input.charAt(0) - 'A');
		int finalRow 		= (int)(input.charAt(3) - 'A');
		int initialColumn 	= (int)(input.charAt(1) - '0');
		int finalColumn  	= (int)(input.charAt(4) - '0');
		
		// Diagonal placements are forbidden
		if(initialColumn != finalColumn && initialRow != finalRow)
			return false;
		
		boolean isHorizontal = initialRow == finalRow;
		
		// Normalize range (ensure start < end) and verify exact length
		if(isHorizontal) {
			if(initialColumn > finalColumn) {
				int aux = initialColumn;
				initialColumn = finalColumn;
				finalColumn = aux;
			}
			if(!(finalColumn - initialColumn + 1 == boatLength))
				return false;
		} else {
			if(initialRow > finalRow) {
				int aux = initialRow;
				initialRow = finalRow;
				finalRow = aux;
			}
			if(!(finalRow - initialRow + 1 == boatLength))
				return false;
		}
		
		// Check for overlaps and adjacency (buffer zone) using emptyAround
		if(isHorizontal) {
			for(int c = initialColumn; c <= finalColumn; c++)
				if(!emptyAround(initialRow, c, boatsCoordinates))
					return false;
		} else {
			for(int r = initialRow; r <= finalRow; r++)
				if(!emptyAround(r, initialColumn, boatsCoordinates))
					return false;
		}
		return true;
	}
	
	/**
	 * Ensures a 1-cell perimeter (3x3 grid) around a coordinate is empty.
	 * This enforces the rule that boats cannot touch each other.
	 * * @param row The row index (0-9)
	 * @param column The column index (0-9)
	 * @param boatCoordinates Set of coordinates already occupied
	 * @return true if the 3x3 area is clear of other boats
	 */
	private static boolean emptyAround(int row, int column, Set<String> boatCoordinates) {
		for(int r = -1; r <= 1; r++)
			for(int c = -1; c <= 1; c++)
				// Check 3x3 grid around (row, column)
				if(boatCoordinates.contains(String.format("%c%d", row + 'A' + r, column + c)))
					return false;
		return true;
	}
}