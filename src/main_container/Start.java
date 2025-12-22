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
		
		//First may do some introductions, but im to lazy to do those as of now
		
		
		
		//Trying to make it work
		player1.boatsCoordinates.add("01");
		player1.ownSunkCoordinates.add("01");
		player1.boatsCoordinates.add("02");
		player1.boatsCoordinates.add("03");
		player1.boatsCoordinates.add("14");
		
		player1.printMyBoats();
		
		
		
		
		
		
		
		//Loops to create the boats
		//Players with for
		for(int i=0;i<2;i++) {
			while(players[0].getCurrentBoats()!=5) {
				
			}
		}
		
		//Game loop for shots, will continue until either of them has no boats left
		while(player1.getNotSunkBoats()!=0&&player2.getNotSunkBoats()!=0) {
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
				currentPlayer = (currentPlayer==1)?2:1;
				continue;
			}
			
			System.out.printf("You shot at: %s\n", input);
			players[currentPlayer].getShotsMade().add(input);
			
//			players[(currentPlayer==1)?0:1]
			
			int row = input.charAt(0) -'A';
			int column = input.charAt(1) - '0';
			String inputAsNumbers = String.format("%d%d", row, column);
			//Have some exams, will have to wait
			
			
			
		}
		
	}
}
