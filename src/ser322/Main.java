package ser322;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        
        if (args.length < 4) {
            printUsage();
            System.exit(0);
        }
        
        System.out.println("Welcome to GameExplorer!");
        String url = args[0];
        String user = args[1];
        String pass = args[2];
        String driver = args[3];
        
        Scanner in = new Scanner(System.in);
        String userInput;
        boolean flag = true;
        while(flag) {
            displayMainMenu();
            userInput = in.nextLine().trim().toLowerCase();
            switch(userInput) {
            case "i":
                displayInsertOptions();
                userInput = in.nextLine().trim().toLowerCase();
                insertSelection(userInput);
                break;
            case "u":
                displayUpdateOptions();
                userInput = in.nextLine().trim().toLowerCase();
                updateSelection(userInput);
                break;
            case "l":
                displayListOptions();
                userInput = in.nextLine().trim().toLowerCase();
                listSelection(userInput);
                break;
            case "s":
                displaySelectOptions();
                userInput = in.nextLine().trim().toLowerCase();
                selectSelection(userInput);
                break;
            case "d":
                displayDeleteOptions();
                userInput = in.nextLine().trim().toLowerCase();
                deleteSelection(userInput);
                break;
            case "q":
                System.out.println("Exiting Application...");
                flag = false;
                break;
            default:
                System.out.println("Invalid option, please try again");
                System.out.println("");
                break;
            }
        }
        in.close();
    }
    
    public static void printUsage() {
        System.out.println("Usage: java ser322.Main <url> <user> <password> <driver>");
    }
    
    public static void displayMainMenu() {
        System.out.println("--Main Menu--");
        System.out.println("i - Enter new data(INSERT)");
        System.out.println("u - Update existing data(UPDATE)");
        System.out.println("l - List all existing data");
        System.out.println("s - Search for specific data(SELECT)");
        System.out.println("d - Delete existing data(DELETE)");
        System.out.println("q - Quit the program");
    }
    
    public static void displayInsertOptions() {
        System.out.println("");
        System.out.println("1 - Add a new Platform");
        System.out.println("2 - Add a new Publisher");
        System.out.println("3 - Add a new Video Game");
        System.out.println("4 - Add a new Review");
        System.out.println("5 - Add a new Voice Actor");
        System.out.println("6 - Add a new Works_For entry");
        System.out.println("7 - Add a new Appears_In entry");
        System.out.println("8 - Add a new Played_On entry");
        System.out.println("b - Back");
    }
    
    public static void insertSelection(String input) {
        switch(input) {
        case "1":
            System.out.println("Handle adding Platform");
            break;
        case "2":
            //Handle adding Publisher
            System.out.println("Handle adding Publisher");
            break;
        case "3":
            //Handle adding Video Game
            System.out.println("Handle adding Video Game");
            break;
        case "4":
            //Handle adding Review
            System.out.println("Handle adding Review");
            break;
        case "5":
            //Handle adding Voice Actor
            System.out.println("Handle adding Voice Actor");
            break;
        case "6":
            //Handle adding Works_For
            System.out.println("Handle adding WorksFor");
            break;
        case "7":
            //Handle adding Appears_In
            System.out.println("Handle adding AppearsIn");
            break;
        case "8":
            //Handle adding Played_On
            System.out.println("Handle adding PlayedOn");
            break;
        case "b":
            System.out.println("Going back to previous menu");
            break;
        default:
            System.out.println("Invalid input, returning to main menu");
            break;
        }
    }
    
    public static void displayUpdateOptions() {
        System.out.println("");
        System.out.println("1 - Change the price of a game");
        System.out.println("2 - Change the name of a publisher");
        System.out.println("b - Back");
    }
    
    public static void updateSelection(String input) {
        switch(input) {
        case "1":
            System.out.println("Change price of a game");
            break;
        case "2":
            System.out.println("Change name of a publisher");
        case "b":
            System.out.println("Going back to previous menu");
            break;
        default:
            System.out.println("Invalid input, returning to main menu");
            break;
        }
    }
    
    public static void displayListOptions() {
        System.out.println("1 - List all data in the database");
        System.out.println("b - Back");
    }
    
    public static void listSelection(String input) {
        switch(input) {
        case "1":
            System.out.println("List all data");
            break;
        case "b":
            System.out.println("Going back to previous menu");
            break;
        default:
            System.out.println("Invalid input, returning to main menu");
            break;
        }
    }
    
    public static void displaySelectOptions() {
        System.out.println("1 - List games from most expensive to least expensive");
        System.out.println("2 - List games by ESRB Rating");
        System.out.println("3 - List games by genre");
        System.out.println("4 - List all games with a rating of X or higher");
        System.out.println("5 - List all games by a specific publisher on a specific platform");
        System.out.println("6 - List all platforms by the number of players supported");
        System.out.println("7 - List all voice actors, the publishers they've worked with, and games they've worked on");
        System.out.println("b - Back");
    }
    
    public static void selectSelection(String input) {
        switch(input) {
        case "1":
            System.out.println("List games from most to least expensive");
            break;
        case "2":
            System.out.println("List games by ESRB Rating");
            break;
        case "3":
            System.out.println("List games by genre");
            break;
        case "4":
            System.out.println(" List all games with a rating of X or higher");
            break;
        case "5":
            System.out.println("List all games by a specific publisher on a specific platform");
            break;
        case "6":
            System.out.println("List all platforms by players supported");
            break;
        case "7":
            System.out.println("List voice actors query");
            break;
        case "b":
            System.out.println("Going back to previous menu");
            break;
        default:
            System.out.println("Invalid input, returning to main menu");
            break;
        }
    }
    
    public static void displayDeleteOptions() {
        System.out.println("1 - Delete games under a specific price");
        System.out.println("2 - Delete reviews with a specific rating");
        System.out.println("b - Back");
    }

    public static void deleteSelection(String input) {
        switch(input) {
        case "1":
            System.out.println("Delete games under a specific price");
            break;
        case "2":
            System.out.println("Delete reviews with a specific rating");
            break;
        case "b":
            System.out.println("Going back to previous menu");
            break;
        default:
            System.out.println("Invalid input, returning to main menu");
            break;
        }
    }
}