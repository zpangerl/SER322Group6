package ser322;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertExecutor extends Executor{

    Scanner in;

    public InsertExecutor(String url, String user, String pass, String driver) {
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
        }
        catch(ClassNotFoundException e) {
            System.out.println("Driver path incorrect, please try again");
            System.exit(0);
        }
        catch(SQLException e) {
            System.out.println("SQLException, please fix query");
        }
        switch(input) {
            case "1":
                System.out.println("Adding new Platform");
                addPlatform();
                break;
            case "2":
                //Handle adding Publisher
                System.out.println("Adding new Publisher");
                addPublisher();
                break;
            case "3":
                //Handle adding Video Game
                System.out.println("Adding new Video Game");
                addVideoGame();
                break;
            case "4":
                //Handle adding Review
                System.out.println("Adding new Review");
                addReview();
                break;
            case "5":
                //Handle adding Voice Actor
                System.out.println("Adding new Voice Actor");
                addVoiceActor();
                break;
            case "6":
                //Handle adding Works_For
                System.out.println("Adding new Works_For relationship");
                addWorksFor();
                break;
            case "7":
                //Handle adding Appears_In
                System.out.println("Adding new Appears_In relationship");
                addAppearsIn();
                break;
            case "8":
                //Handle adding Played_On
                System.out.println("Adding new Played_On relationship");
                addPlayedOn();
                break;
            case "b":
                System.out.println("Going back to previous menu");
                break;
            default:
                System.out.println("Invalid input, returning to main menu");
                break;
        }
        try {
            connect.close();
        } catch (SQLException e) {
            System.out.println("Connection not closed!");
        }
    }

    public void addPlatform() {
        System.out.println("Please enter the new Platform's name:");
        String platformName = in.nextLine().trim();
        System.out.println("Please enter the new Platform's max resolution:");
        String maxResolution = in.nextLine().trim();
        System.out.println("Please enter the new Platform's max players:");
        int maxPlayers = 0;
        try {
            maxPlayers = Integer.parseInt(in.nextLine().trim());
            if(maxPlayers < 1) throw new NumberFormatException();
        } catch(NumberFormatException e) {
            System.out.println("Max Players must be a positive integer!");
            return;
        }
        System.out.println("Please enter the new Platform's format:");
        String format = in.nextLine().trim();
        try {
            pstmt = connect.prepareStatement("INSERT INTO PLATFORM (PlatformName, MaxRes, MaxPlayers, Format) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, platformName);
            pstmt.setString(2, maxResolution);
            pstmt.setInt(3, maxPlayers);
            pstmt.setString(4, format);
            //Make sure insertion is successful
            int addedRows = pstmt.executeUpdate();
            if(addedRows > 0) {
                //Commit
                connect.commit();
                System.out.println("Platform added successfully");
            }
            else {
                //No commit
                System.out.println("Platform add unsuccessful.");
            }
            pstmt.close();
        }catch(SQLException e) {
            //If the platformName already exists in the database:
            System.out.println("Platform with the entered name already exists. Please try again!");
            return;
        }
    }

    public void addPublisher() {
        System.out.println("Please enter the new Publisher's name:");
        String publisherName = in.nextLine().trim();
        System.out.println("Please enter the new Publisher's country:");
        String country = in.nextLine().trim();
        try {
            pstmt = connect.prepareStatement("INSERT INTO PUBLISHER (PublisherName, Country) VALUES (?, ?)");
            pstmt.setString(1, publisherName);
            pstmt.setString(2, country);
            //Make sure insertion is successful
            int addedRows = pstmt.executeUpdate();
            if(addedRows > 0) {
                //Commit
                connect.commit();
                System.out.println("Publisher added successfully");
            }
            else {
                //No commit
                System.out.println("Publisher add unsuccessful.");
            }
            pstmt.close();
        }catch(SQLException e) {
            //If the publisherName already exists in the database:
            System.out.println("Publisher with the entered name already exists. Please try again!");
            return;
        }
    }

    public void addVideoGame() {
        System.out.println("Please enter the name of the new Video Game:");
        String gameTitle = in.nextLine().trim();
        System.out.println("Please enter the price of the new Video Game:");
        float gamePrice = -1;
        try {
            gamePrice = Float.parseFloat(in.nextLine().trim());
            if(gamePrice < 0) throw new NumberFormatException();
        }catch(NumberFormatException e) {
            System.out.println("Game Price must be a number 0 or higher!");
            return;
        }
        System.out.println("Please enter the maximum number of players of the new Video Game:");
        int gamePlayers = 0;
        try {
            gamePlayers = Integer.parseInt(in.nextLine().trim());
            if(gamePlayers < 1) throw new NumberFormatException();
        }catch(NumberFormatException e) {
            System.out.println("Number of players must be a positive integer!");
            return;
        }
        System.out.println("Please enter the genre of the new Video Game:");
        String genre = in.nextLine().trim();
        System.out.println("Please enter the ESRB Rating of the new Video Game:");
        String ESRBRating = in.nextLine().trim();
        System.out.println("Please enter the publisher name of the new Video Game:");
        String publisherName = in.nextLine().trim();
        try {
            pstmt = connect.prepareStatement("INSERT INTO VIDEO_GAME "
                    + "(GameTitle, Price, NumPlayers, Genre, ESRB_Rating, PublisherName) "
                    + "VALUES (?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, gameTitle);
            pstmt.setDouble(2, gamePrice);
            pstmt.setInt(3, gamePlayers);
            pstmt.setString(4, genre);
            pstmt.setString(5, ESRBRating);
            pstmt.setString(6, publisherName);
            //Make sure insertion is successful
            int addedRows = pstmt.executeUpdate();
            if(addedRows > 0) {
                //Commit
                connect.commit();
                System.out.println("Video Game added successfully");
            }
            else {
                //No commit
                System.out.println("Video Game add unsuccessful.");
            }
            pstmt.close();
        }catch(SQLException e) {
            //Check error code for duplicate entry
            if(e.getErrorCode() == 1062) {
                System.out.println("Video Game with this name already exists!");
                return;
            }
            System.out.println("Publisher with this name does not exist!");
            System.out.println("Please enter an existing publisher name or add a new publisher.");
            return;
        }
    }

    public void addReview() {
        System.out.println("Please enter the ID of the new Review:");
        int reviewID = -1;
        try {
            reviewID = Integer.parseInt(in.nextLine().trim());
            if(reviewID < 1) throw new NumberFormatException();
        }catch(NumberFormatException e) {
            System.out.println("Review ID must be an integer!");
            return;
        }
        System.out.println("Please enter the author of the new Review:");
        String author = in.nextLine().trim();
        System.out.println("Please enter the text of the new Review:");
        String reviewText = in.nextLine().trim();
        System.out.println("Please enter the rating of the new Review(1 to 5)");
        int reviewRating = -1;
        try {
            reviewRating = Integer.parseInt(in.nextLine().trim());
            if(reviewRating < 1 || reviewRating > 5) {
                throw new NumberFormatException();
            }
        }catch(NumberFormatException e) {
            System.out.println("Review rating must be an integer between 1 and 5!");
            return;
        }
        System.out.println("Please enter the game title the new Review is for:");
        String gameTitle = in.nextLine().trim();
        try {
            pstmt = connect.prepareStatement("INSERT INTO REVIEW (ReviewID, Author, ReviewText, Rating, GameTitle) "
                    + "VALUES (?, ?, ?, ?, ?)");
            pstmt.setInt(1, reviewID);
            pstmt.setString(2, author);
            pstmt.setString(3, reviewText);
            pstmt.setInt(4, reviewRating);
            pstmt.setString(5, gameTitle);
            //Make sure insertion is successful
            int addedRows = pstmt.executeUpdate();
            if(addedRows > 0) {
                //Commit
                connect.commit();
                System.out.println("Review added successfully");
            }
            else {
                //No commit
                System.out.println("Review add unsuccessful.");
            }
            pstmt.close();
        }catch(SQLException e) {
            //Check error code for duplicate entry
            if(e.getErrorCode() == 1062) {
                System.out.println("Review with this ID already exists!");
                return;
            }
            System.out.println("Video Game with this title does not exist!");
            System.out.println("Please enter an existing video game title or add a new video game.");
            return;
        }
    }

    public void addVoiceActor() {
        System.out.println("Please enter the name of the new Voice Actor:");
        String actorName = in.nextLine().trim();
        try {
            pstmt = connect.prepareStatement("INSERT INTO VOICE_ACTOR (ActorName) VALUES (?)");
            pstmt.setString(1, actorName);
            //Make sure insertion is successful
            int addedRows = pstmt.executeUpdate();
            if(addedRows > 0) {
                //Commit
                connect.commit();
                System.out.println("Voice Actor added successfully");
            }
            else {
                //No commit
                System.out.println("Voice Actor add unsuccessful.");
            }
            pstmt.close();
        }catch(SQLException e) {
            System.out.println("Voice Actor with the entered name already exists. Please try again!");
            return;
        }
    }

    public void addWorksFor() {
        System.out.println("Please enter the name of the Voice Actor:");
        String actorName = in.nextLine().trim();
        System.out.println("Please enter the name of the Publisher:");
        String publisherName = in.nextLine().trim();
        System.out.println("Please enter the number of games the Voice Actor has worked with this Publisher on:");
        int gamesWorkedCount = -1;
        try {
            gamesWorkedCount = Integer.parseInt(in.nextLine().trim());
            if(gamesWorkedCount < 1) throw new NumberFormatException();
        }catch(NumberFormatException e) {
            System.out.println("The number of games must be a positive integer!");
            return;
        }
        try {
            pstmt = connect.prepareStatement("INSERT INTO WORKS_FOR (ActorName, PublisherName, GamesWorkedCount) "
                    + "VALUES (?, ?, ?)");
            pstmt.setString(1, actorName);
            pstmt.setString(2, publisherName);
            pstmt.setInt(3, gamesWorkedCount);
            //Make sure insertion is successful
            int addedRows = pstmt.executeUpdate();
            if(addedRows > 0) {
                //Commit
                connect.commit();
                System.out.println("Works For relationship added successfully");
            }
            else {
                //No commit
                System.out.println("Works For relationship add unsuccessful.");
            }
            pstmt.close();
        }catch(SQLException e) {
            //Check error code for duplicate entry
            if(e.getErrorCode() == 1062) {
                System.out.println("The given Voice Actor and Publisher already have an entry!");
                return;
            }
            System.out.println("Voice Actor and/or Publisher with provided name does not exist.");
            System.out.println("Please enter valid names, or add a new Voice Actor and/or Publisher.");
            return;
        }
    }

    public void addAppearsIn() {
        System.out.println("Please enter the title of the Video Game:");
        String gameTitle = in.nextLine().trim();
        System.out.println("Please enter the name of the Voice Actor:");
        String actorName = in.nextLine().trim();
        try {
            pstmt = connect.prepareStatement("INSERT INTO APPEARS_IN (GameTitle, ActorName) VALUES (?, ?)");
            pstmt.setString(1, gameTitle);
            pstmt.setString(2, actorName);
            //Make sure insertion is successful
            int addedRows = pstmt.executeUpdate();
            if(addedRows > 0) {
                //Commit
                connect.commit();
                System.out.println("Appears In relationship added successfully");
            }
            else {
                //No commit
                System.out.println("Appears In relationship add unsuccessful.");
            }
            pstmt.close();
        }catch(SQLException e) {
            //Check error code for duplicate entry
            if(e.getErrorCode() == 1062) {
                System.out.println("The given Video Game and Voice Actor already have an entry!");
                return;
            }
            System.out.println("Video Game and/or Voice Actor with provided name does not exist.");
            System.out.println("Please enter valid names, or add a new Video Game and/or Voice Actor.");
            return;
        }
    }

    public void addPlayedOn() {
        System.out.println("Please enter the title of the Video Game:");
        String gameTitle = in.nextLine().trim();
        System.out.println("Please enter the name of the Platform:");
        String platformName = in.nextLine().trim();
        try {
            pstmt = connect.prepareStatement("INSERT INTO PLAYED_ON (GameTitle, PlatformName) VALUES (?, ?)");
            pstmt.setString(1, gameTitle);
            pstmt.setString(2, platformName);
            //Make sure insertion is successful
            int addedRows = pstmt.executeUpdate();
            if(addedRows > 0) {
                //Commit
                connect.commit();
                System.out.println("Played On relationship added successfully");
            }
            else {
                //No commit
                System.out.println("Played On relationship add unsuccessful.");
            }
            pstmt.close();
        }catch(SQLException e) {
            //Check error code for duplicate entry
            if(e.getErrorCode() == 1062) {
                System.out.println("The given Video Game and Platform already have an entry!");
                return;
            }
            System.out.println("Video Game and/or Platform with provided name does not exist.");
            System.out.println("Please enter valid names, or add a new Video Game and/or Platform.");
            return;
        }
    }
}
