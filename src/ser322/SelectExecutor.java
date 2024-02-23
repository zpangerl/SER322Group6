package ser322;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            while (rs.next()) {
                System.out.println("Game: " + rs.getString("GameTitle")
                        + ", Price: " + rs.getFloat("Price"));
            }

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

            while (rs.next()) {
                System.out.println("Game: " + rs.getString("GameTitle")
                        + ", ESRB_Rating: " + rs.getString("ESRB_Rating"));
            }

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

            while (rs.next()) {
                System.out.println("Game: " + rs.getString("GameTitle")
                        + ", Genre: " + rs.getString("Genre"));
            }

        } catch (SQLException e) {
            System.out.println("Invalid SQL query, please fix and try again");
        }
    }

}

