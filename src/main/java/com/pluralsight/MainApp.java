package com.pluralsight;


import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

        // Scanner allows us to receive input from the user
        Scanner scanner = new Scanner(System.in);

        // Create an ArrayList to store all Player objects
        // This allows us to have multiple players in the game
        ArrayList<Player> players = new ArrayList<>();

        // Welcome message
        System.out.println("Welcome to Blackjack!");

        // Ask the user how many players will participate
        System.out.print("How many players are playing? ");

        // Read the number entered by the user
        int numberOfPlayers = scanner.nextInt();

        // Consume the leftover newline before reading player names
        scanner.nextLine();


        // STEP 1: CREATE PLAYERS

        // Loop through the number of players entered by the user
        for (int i = 0; i < numberOfPlayers; i++) {

            // Ask each player for their name
            // i + 1 makes the player number start at 1 instead of 0
            System.out.print("Enter player " + (i + 1) + " name: ");

            // Read and store the player's name
            String name = scanner.nextLine();

            // Create a new Player object using the entered name
            // The Player constructor also creates a new Hand
            Player player = new Player(name);

            // Add the new Player object to the players ArrayList
            players.add(player);
        }


        // STEP 2: CREATE AND SHUFFLE THE DECK

        // Create a new Deck object containing 52 cards
        Deck deck = new Deck();

        // Shuffle the cards to randomize their order
        deck.shuffle();


        // STEP 3: DEAL TWO CARDS TO EACH PLAYER

        // The outer loop runs twice because each player needs two cards
        for (int i = 0; i < 2; i++) {

            // Go through every Player object in the players ArrayList
            for (Player player : players) {

                // Ask the Deck to deal one Card object
                Card card = deck.deal();

                // Check that the Deck still has a card available
                if (card != null) {

                    // Give the dealt card to the current player
                    // The Player passes the card to their Hand
                    player.receiveCard(card);
                }
            }
        }


        // STEP 4: DISPLAY EACH PLAYER'S HAND AND SCORE

        System.out.println("\n----- GAME RESULTS -----");

        // Initialize winner as null because nobody has won yet
        Player winner = null;

        // Keep track of the highest valid score
        int highestScore = 0;

        // Loop through every player to display their cards and calculate scores
        for (Player player : players) {

            // Display the current player's name
            System.out.println("\nPlayer: " + player.getName());

            // Display the player's cards
            System.out.println("Cards:");
            player.getHand().displayCards();

            // Get the total point value of the player's hand
            int score = player.getHand().getValue();

            // Display the player's total score
            System.out.println("Total points: " + score);


            // STEP 5: CHECK IF THE PLAYER IS THE WINNER

            // A player must have 21 or fewer points
            // Their score must also be higher than the current highest score
            if (score <= 21 && score > highestScore) {

                // Update the highest score
                highestScore = score;

                // Store the current player as the winner
                winner = player;
            }
        }


        // STEP 6: DECLARE THE WINNER

        System.out.println("\n----- WINNER -----");

        // Check whether a winner was found
        if (winner != null) {

            // Display the winner's name and final score
            System.out.println(
                    winner.getName() + " wins with " +
                            highestScore + " points!"
            );

        } else {

            // Display this message if every player went over 21
            System.out.println("Nobody won. Everyone went over 21.");
        }


        // Close the Scanner after the game is finished
        scanner.close();
    }

}
