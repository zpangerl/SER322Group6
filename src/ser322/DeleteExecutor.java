package ser322;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteExecutor extends Executor {

    Scanner in;

    public DeleteExecutor(String url, String user, String pass, String driver) {
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
                    deleteGamesUnderSpecificPrice();
                    break;
                case "2":
                    deleteReviewsWithSpecificRating();
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

    private void deleteGamesUnderSpecificPrice() {
        System.out.println("Enter the price threshold:");
        float priceThreshold = Float.parseFloat(in.nextLine().trim());

        String query = "DELETE FROM VIDEO_GAME WHERE Price < ?";
        try {
            pstmt = connect.prepareStatement(query);
            pstmt.setFloat(1, priceThreshold);

            int affectedRows = pstmt.executeUpdate();
            connect.commit();
            System.out.println(affectedRows + " games deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting games: " + e.getMessage());
        }
    }

    private void deleteReviewsWithSpecificRating() {
        System.out.println("Enter the rating threshold:");
        int ratingThreshold = Integer.parseInt(in.nextLine().trim());

        String query = "DELETE FROM REVIEW WHERE Rating = ?";
        try {
            pstmt = connect.prepareStatement(query);
            pstmt.setInt(1, ratingThreshold);

            int affectedRows = pstmt.executeUpdate();
            connect.commit();
            System.out.println(affectedRows + " reviews deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting reviews: " + e.getMessage());
        }
    }
}
