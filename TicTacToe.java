package com.example;

import java.util.Scanner;

public class TicTacToe {
    private char currentPlayer;
    // Reference numbered board
    public char[][] mainBoard = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
    };
    // Empty game board
    public char[][] gameBoard = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    Scanner input = new Scanner(System.in);

    public TicTacToe() {
        currentPlayer = 'X';
    }

    // Main method to run the program
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(); // Create an instance of TicTacToe

        // Introduction message
        System.out.println("Welcome to Haniyah's Tic Tac Toe Game! Aim of the game is simple; Beat the Computer :)");
        System.out.println(" ");

        // Call the printBoard method to print board display to console
        game.printMainBoard();

        // Call playGame method to start the game
        game.playGame();
    }

    // Print numbered board for the start of the game as reference
    public void printMainBoard() {
        System.out.println("Starting Board:");
        System.out.println(" ");
        System.out.println(" " + mainBoard[0][0] + " | " + mainBoard[0][1] + " | " + mainBoard[0][2]);
        System.out.println("-----------");
        System.out.println(" " + mainBoard[1][0] + " | " + mainBoard[1][1] + " | " + mainBoard[1][2]);
        System.out.println("-----------");
        System.out.println(" " + mainBoard[2][0] + " | " + mainBoard[2][1] + " | " + mainBoard[2][2]);
        System.out.println(); // Adding an extra newline for spacing
    }

    // Method to print the game board
    public void printBoard() {
        System.out.println("Current Board:");
        System.out.println(" ");
        System.out.println(" " + gameBoard[0][0] + " | " + gameBoard[0][1] + " | " + gameBoard[0][2]);
        System.out.println("-----------");
        System.out.println(" " + gameBoard[1][0] + " | " + gameBoard[1][1] + " | " + gameBoard[1][2]);
        System.out.println("-----------");
        System.out.println(" " + gameBoard[2][0] + " | " + gameBoard[2][1] + " | " + gameBoard[2][2]);
        System.out.println(); // Adding an extra newline for spacing
    }

    protected boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean winCheck(char currentPlayer) {
        return (checkColumns(currentPlayer) || checkRows(currentPlayer) || checkDiagonal(currentPlayer));
    }

    private boolean checkRows(char currentPlayer) {
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == currentPlayer && gameBoard[i][1] == currentPlayer && gameBoard[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(char player) {
        for (int i = 0; i < 3; i++) {
            if (gameBoard[0][i] == player && gameBoard[1][i] == player && gameBoard[2][i] == player) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal(char currentPlayer) {
        return ((gameBoard[0][0] == currentPlayer && gameBoard[1][1] == currentPlayer && gameBoard[2][2] == currentPlayer) ||
                (gameBoard[0][2] == currentPlayer && gameBoard[1][1] == currentPlayer && gameBoard[2][0] == currentPlayer));
    }

    public void playGame() {
        // Input to check if player goes first
        System.out.print("Do you want to go first? (y/n): ");
        String response = input.nextLine().toLowerCase();
    
        boolean isGameOver = false;
    
        // Computer's turn if player chose to go second
        if (response.equals("n")) {
            // Computer goes first
            computerMove(); // Call computer move function
            printBoard();
            if (winCheck('O')) {
                System.out.println("Computer wins! Better luck next time.");
                return; // game ends when comp wins
            }
        }
    
        // Player's turns
        while (!isGameOver) {
            // Player's turn if they chose to go first or after computer's move
            if (response.equals("y") || (response.equals("n") && !isGameOver)) {
                playerMove();
                printBoard();
                if (winCheck('X')) {
                    System.out.println("Congrats! You Won!");
                    break; // End game if player wins
                }
                if (isBoardFull()) {
                    System.out.println("It's a Tie :/");
                    break; // End game if the board is full
                }
            }
    
            // Computer's turn
            if (!isGameOver) {
                computerMove(); // Call computer move function
                printBoard();
                if (winCheck('O')) {
                    System.out.println("Computer wins! Better luck next time.");
                    break;
                }
                if (isBoardFull()) {
                    System.out.println("It's a Tie :/");
                    break;
                }
            }
        }
    }
    

    private void playerMove() {
        int move;

        while (true) {
            System.out.print("It's your move! Pick a square (1-9): ");
            move = input.nextInt();

            // Check if the move is valid
            if (move > 0 && move <= 9 && gameBoard[(move - 1) / 3][(move - 1) % 3] == ' ') {
                gameBoard[(move - 1) / 3][(move - 1) % 3] = 'X'; // Place 'X' in the game board
                break; // Exit loop once a valid move is made
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
    }

    private void computerMove() {
        int move;
        int min = 1;
        int max = 9;
        boolean validMove = false;

        while (!validMove) {
            move = (int) (Math.random() * (max - min + 1)) + min;

            // Check if the square is empty
            if (gameBoard[(move - 1) / 3][(move - 1) % 3] == ' ') {
                gameBoard[(move - 1) / 3][(move - 1) % 3] = 'O'; // Place 'O' in the game board
                validMove = true;
                System.out.println("Computer placed O in square " + move);
            }
        }
    }
}
