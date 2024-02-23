package ser322;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class SelectExecutor extends Executor{
    Scanner in;

    public SelectExecutor(String url, String user, String pass, String driver) {
        super(url, user, pass, driver);
    }

    public void run(String input) {
        in = new Scanner(System.in);
        try {
            //Load the JDBC driver
            Class.forName(driver);
            //Make a connection
            connect = DriverManager.getConnection(url, user, pass);
            connect.setAutoCommit(false);

            switch (input) {
                case "1":
                    listGamesFromMostToLeastExpensive();
                    break;
                case "2":
                    listGamesByESRBRating();
                    break;
                case "3":
                    listGamesByGenre();
                    break;
                case "4": listGamesRatedOverX();
                    break;
                case "5": listGamesByPubAndPlat();
                    break;
                case "6": listGamesByNumOfPlayers();
                    break;
                case "7": listVoiceActors();
                    break;
                case "b":
                    System.out.println("Going back to previous menu");
                    break;
                default:
                    System.out.println("Invalid input, returning to main menu");
                    break;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver path incorrect, please try again");
            System.exit(0);
        } catch(SQLException e) {
            System.out.println("SQLException, please fix query and connection details");
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                } if (stmt != null) {
                    stmt.close();
                }
                if(pstmt != null) {
                    pstmt.close();
                } if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                System.out.println("Connection not closed properly!");
            }
        }
    }

    private void listGamesFromMostToLeastExpensive() {
        try {
            stmt = connect.createStatement();

            rs = stmt.executeQuery("SELECT GameTitle, Price "
                    + "FROM VIDEO_GAME "
                    + "ORDER BY PRICE DESC");

            System.out.println("Game Title \t\tPrice");
            System.out.println("========== \t\t=====");
            while (rs.next()) {
                System.out.println(rs.getString("GameTitle") + "\t\t $" + rs.getString("Price"));
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Invalid SQL query, please fix and try again");
        }
    }

    private void listGamesByESRBRating() {
        try {
            stmt = connect.createStatement();

            rs = stmt.executeQuery("SELECT GameTitle, ESRB_Rating "
                    + "FROM VIDEO_GAME "
                    + "ORDER BY ESRB_RATING");

            System.out.println("Game Title \tESRB Rating");
            System.out.println("========== \t===========");
            while (rs.next()) {
                System.out.println(rs.getString("GameTitle") + "\t" + rs.getString("ESRB_Rating"));
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Invalid SQL query, please fix and try again");
        }
    }

    private void listGamesByGenre() {
        try {
            stmt = connect.createStatement();

            rs = stmt.executeQuery("SELECT GameTitle, Genre "
                    + "FROM VIDEO_GAME "
                    + "ORDER BY Genre");

            System.out.println("Game Title \tGenre");
            System.out.println("========== \t=====");
            while (rs.next()) {
                System.out.println(rs.getString("GameTitle") + "\t" + rs.getString("Genre"));
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Invalid SQL query, please fix and try again");
        }
    }
    
    private void listGamesRatedOverX() {
        int rating = 0;
        int MIN = 1;
        int MAX = 5;
        Scanner scanner = new Scanner(System.in);
        while(rating < MIN || rating > MAX) {
            try {
                System.out.print("Select a rating from " + MIN + " to " + MAX + ": ");
                rating = scanner.nextInt();
            }catch(InputMismatchException e) {
                System.out.println("Invalid selection.");
                scanner.nextLine();
            }
        }
        
        try {
            stmt = connect.createStatement();

            rs = stmt.executeQuery("SELECT VIDEO_GAME.GameTitle, REVIEW.Rating "
                    + "FROM VIDEO_GAME JOIN REVIEW ON VIDEO_GAME.GameTitle = REVIEW.GameTitle "
                    + "WHERE REVIEW.Rating > " + rating + 
                    " ORDER BY REVIEW.Rating DESC");

            System.out.println("Game Name \tReview Rating");
            System.out.println("========= \t=============");
            while (rs.next()) {
                System.out.println(rs.getString("GameTitle") + "\t" + rs.getString("Rating"));
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Invalid SQL query, please fix and try again");
        }
        
    }
    

    private void listGamesByNumOfPlayers() {
        int number = 0;
        Scanner scanner = new Scanner(System.in);
        while(number < 1) {
            try {
                System.out.print("Select a number of players: ");
                number = scanner.nextInt();
            }catch(InputMismatchException e) {
                System.out.println("Invalid selection.");
                scanner.nextLine();
            }
        }
        
        try {
            stmt = connect.createStatement();

            rs = stmt.executeQuery("SELECT PlatformName, MaxPlayers "
                    + "FROM PLATFORM "
                    + "WHERE MaxPlayers >= " + number +
                    " ORDER BY MaxPlayers DESC");

            System.out.println("Platform Name \tMax Players");
            System.out.println("============= \t===========");
            while (rs.next()) {
                System.out.println(rs.getString("PlatformName") + "\t" + rs.getString("MaxPlayers"));
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Invalid SQL query, please fix and try again");
        }
    }

    private void listGamesByPubAndPlat() {
        String publisher, platform = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the publisher: ");
        publisher = scanner.nextLine();
        System.out.print("Enter the name of the platform: ");
        platform = scanner.nextLine();
        
        try {
            stmt = connect.createStatement();

            rs = stmt.executeQuery("SELECT VIDEO_GAME.GameTitle\r\n"
                    + "FROM VIDEO_GAME JOIN PLAYED_ON ON VIDEO_GAME.GameTitle = PLAYED_ON.GameTitle\r\n"
                    + "WHERE VIDEO_GAME.PublisherName = '" + publisher + "' AND PLAYED_ON.PlatformName = '" + platform + "'");

            System.out.println("Game Title");
            System.out.println("==========");
            while (rs.next()) {
                System.out.println(rs.getString("GameTitle"));
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Invalid SQL query, please fix and try again");
        }
        
    }
    
    private void listVoiceActors() {
        try {
            stmt = connect.createStatement();

            rs = stmt.executeQuery("SELECT WORKS_FOR.ActorName, WORKS_FOR.PublisherName, APPEARS_IN.GameTitle FROM WORKS_FOR JOIN PUBLISHER ON WORKS_FOR.PublisherName = PUBLISHER.PublisherName JOIN APPEARS_IN ON WORKS_FOR.ActorName = APPEARS_IN.ActorName ORDER BY ActorName ASC");

            System.out.println("Actor Name \tPublisher");
            System.out.println("========== \t=========");
            while (rs.next()) {
                System.out.println(rs.getString("ActorName") + "\t" + rs.getString("PublisherName"));
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Invalid SQL query, please fix and try again");
        }
    }
}