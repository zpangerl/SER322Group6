package ser322;


import java.util.Scanner;


public class Main {
    // The main method, serving as the entry point for the application
    public static void main(String[] args) {

        // Verifies the number of command-line arguments to ensure they meet the expected count
        if (args.length < 4) {
            printUsage(); // Invokes method to display how to properly use the program
            System.exit(0); // Terminates the program
        }

        // Initial greeting message for users
        System.out.println("Welcome to GameExplorer!");

        String url = args[0]; // URL for the database connection
        String user = args[1]; // Username for database access
        String pass = args[2]; // Password for database access
        String driver = args[3]; // JDBC driver for database connectivity

        // Scanner object instantiation for console input reading
        Scanner in = new Scanner(System.in);
        String userInput; // Declaration of variable to capture user input
        boolean flag = true;
        while (flag) {
            displayMainMenu(); // Displays the available main menu options to the user
            userInput = in.nextLine().trim().toLowerCase();
            switch (userInput) { // Decision-making structure based on user input
                case "i":
                    displayInsertOptions(); // Shows options for data insertion
                    userInput = in.nextLine().trim().toLowerCase();
                    insertSelection(userInput, args); // Processes insertion request based on user input
                    break;
                case "u":
                    displayUpdateOptions(); // Presents options for updating data
                    userInput = in.nextLine().trim().toLowerCase();
                    updateSelection(userInput, args); // Executes update operation based on user input
                    break;
                case "l":
                    displayListOptions(); // Displays options for listing data
                    userInput = in.nextLine().trim().toLowerCase();
                    listSelection(userInput, args); // Handles the listing of data based on user choice
                    break;
                case "s":
                    displaySelectOptions(); // Shows options for data selection (queries)
                    userInput = in.nextLine().trim().toLowerCase();
                    selectSelection(userInput, args); // Executes selection operation based on user input
                    break;
                case "d":
                    displayDeleteOptions(); // Presents options for data deletion
                    userInput = in.nextLine().trim().toLowerCase();
                    deleteSelection(userInput, args); // Processes deletion request based on user input
                    break;
                case "q": // Case for quitting the application
                    System.out.println("Exiting Application..."); // Message indicating program exit
                    flag = false; // Updates the flag to stop the loop, ending the program
                    break;
                default:
                    System.out.println("Invalid option, please try again\n"); // Handles unrecognized input
                    break;
            }
        }
        in.close();
    }

    // Method detailing the correct usage of the program, for user guidance
    public static void printUsage() {
        System.out.println("Usage: java ser322.Main <url> <user> <password> <driver>");
    }

    // Method to visually present the main menu's choices to the user
    public static void displayMainMenu() {
        System.out.println("--Main Menu--");
        System.out.println("i - Enter new data(INSERT)");
        System.out.println("u - Update existing data(UPDATE)");
        System.out.println("l - List all existing data");
        System.out.println("s - Search for specific data(SELECT)");
        System.out.println("d - Delete existing data(DELETE)");
        System.out.println("q - Quit the program");
    }

    // Shows available choices for adding new records to the database
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

    // Executes the chosen action for adding new data
    public static void insertSelection(String input, String[] args) {
        InsertExecutor ie = new InsertExecutor(args[0], args[1], args[2], args[3]);
        ie.run(input);
    }

    // Presents the user with options to modify existing database records
    public static void displayUpdateOptions() {
        System.out.println("");
        System.out.println("1 - Change the price of a game");
        System.out.println("2 - Change the name of a publisher");
        System.out.println("b - Back");
    }

    // Handles the user's choice to update data
    public static void updateSelection(String input, String[] args) {
        UpdateExecutor ue = new UpdateExecutor(args[0], args[1], args[2], args[3]);
        ue.run(input);
    }

    // Lists the options for viewing database records
    public static void displayListOptions() {
        System.out.println("1 - List all data in the database");
        System.out.println("b - Back");
    }

    // Processes the user's selection for viewing database records
    public static void listSelection(String input, String[] args) {
        switch(input) {
            case "1":
                System.out.println("List all data");
                ListExecutor le = new ListExecutor(args[0], args[1], args[2], args[3]);
                le.run();
                break;
            case "b":
                System.out.println("Going back to previous menu");
                break;
            default:
                System.out.println("Invalid input, returning to main menu");
                break;
        }
    }

    // Shows options for querying the database for specific records
    public static void displaySelectOptions() {
        System.out.println("1 - List games from most expensive to least expensive");
        System.out.println("2 - List games by ESRB Rating");
        System.out.println("3 - List games by genre");
        System.out.println("4 - List all games with a rating of X or higher");
        System.out.println("5 - List all games by a specific publisher on a specific platform");
        System.out.println("6 - List all platforms with equal or greater number of entered max players");
        System.out.println("7 - List all voice actors, the publishers they've worked with, and games they've worked on");
        System.out.println("b - Back");
    }

    // Executes the selected query operation
    public static void selectSelection(String input, String[] args) {
        SelectExecutor se = new SelectExecutor(args[0], args[1], args[2], args[3]);
        se.run(input);
    }

    // Provides options for removing records from the database
    public static void displayDeleteOptions() {
        System.out.println("1 - Delete games under a specific price");
        System.out.println("2 - Delete reviews with a specific rating");
        System.out.println("b - Back");
    }

    // Carries out the deletion process as chosen by the user
    public static void deleteSelection(String input, String[] args) {
        DeleteExecutor de = new DeleteExecutor(args[0], args[1], args[2], args[3]);
        de.run(input);
    }
}
