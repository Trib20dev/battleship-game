package main_container;

import java.util.ArrayList;
import java.util.Scanner;

public class Start {
	
	//Static scanner
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Create needed variables
		Player player1 = new Player();
		Player player2 = new Player();
		Player[] players = {player1,player2};
		int currentPlayer = 0; //0 for player 1, 1 for player 2
		int currentEnemy = 1;
		
		//First may do some introductions, but im to lazy to do those as of now
		
		//Loops to create the boats
		//Players with for
		System.out.println("The boats shall be created with the format:\n A0 A4\n Where the you indicate from which space to which space your boat exists");
		for(int i=0;i<2;i++) {
			System.out.printf("It's player %d's turn\n",(i==0)?1:2);
			
			//Easier reading varables
			int boatsAccesIndex;
			String boatName;
			ArrayList<Boat> boats;
			int boatLength;
			
			while(players[i].getCurrentBoats()!=5) {
				//Initialize those variables
				boats = players[i].getBoats();
				boatsAccesIndex = players[i].getCurrentBoats();
				boatName = players[i].getBoatsNames().get(boatsAccesIndex);
				boatLength = boats.get(boatsAccesIndex).getTotalCoordinates();
				
				System.out.printf("Where will the %s which ocupies %d spaces be?\n"
						,boatName
						,boatLength);
				
				do {
					
				}while(!input.matches("^[A-J][0-9] [A-J][0-9]$"));
				
				
			}
		}
		
		
		
		
		
		
		//Game loop for shots, will continue until either of them has no boats left
		while(player1.getSunkBoats()!=5&&player2.getSunkBoats()!=5) {
			//Tell which players turn it is
			System.out.printf("It's player %d's turn\n",(currentPlayer==0)?1:2);
	
			String input;
			//Input your desired operation
			do {
				System.out.println("what will you do?");
				input = scanner.nextLine().toUpperCase();
				if(input.matches("(?i).*see my boats.*")) 
					players[currentPlayer].printMyBoats();;
				
				if(input.matches("(?i).*see my shots.*")) 
					players[currentPlayer].printMyShots();
				
			} while(!input.matches("^[A-J][0-9]$"));
			
			//Check whether that move has been made already
			if(players[currentPlayer].getShotsMade().contains(input)) {
				System.out.printf("You already shot at %s, not the wisest move",input);
				currentPlayer = 1 - currentPlayer;
				currentEnemy = 1 - currentEnemy;
				continue;
			}
			
			System.out.printf("You shot at: %s\n", input);
			//We add the shot to the current player AAND the enemy
			players[currentPlayer].getShotsMade().add(input);
			players[currentEnemy].getShotsReceived().add(input);
			
			//We store the input as numbers, dont remember why i needed this but anyways
//			String inputAsNumbers = String.format("%d%d", input.charAt(0) -'A', input.charAt(1) - '0');
			
			//Now i'll check whether you hit something
			if(players[currentEnemy].getBoatsCoordinates().contains(input)) {
				//Add the found coordinate
				players[currentPlayer].getEnemyFoundCoordinates().add(input);
				//Go trough all the boats
				for(int i=0;i<players[currentEnemy].getBoats().size();i++) 
					//Check wheter that boat is the one with such coordinates
					if(players[currentEnemy].getBoats().get(i).getCoordinates().contains(input)) {
						//We know search the index of the concret coordinate and modifie its state
						for(int j=0;j<players[currentEnemy].getBoats().get(i).getCoordinates().size();j++)
							if(players[currentEnemy].getBoats().get(i).getCoordinates().get(j).contains(input))
								players[currentEnemy].getBoats().get(i).setStateHitAt(j);
							
						//Now we look up whether that boat has sunk
						if(players[currentEnemy].getBoats().get(i).isSunk()) {
							//TODO Add which boat was sunk using the index to look for the name and then use a print 
							
							//Now i have to add the sunk coordinates to the current player and the enemy player
							players[currentPlayer].getEnemySunkCoordinates().addAll(players[currentEnemy].getBoats().get(i).getCoordinates());
							players[currentEnemy].getOwnSunkCoordinates().addAll(players[currentEnemy].getBoats().get(i).getCoordinates());
							//Then i should update the current sunk boats count
							players[currentEnemy].oneMoreSunkBoat();
						}
						
					}
			}
			
			//Now i dont think theres anything left but changing the player
			currentPlayer = 1 - currentPlayer;
			currentEnemy = 1 - currentEnemy;
		}
		
	}
}
