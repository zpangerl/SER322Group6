package ser322;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateExecutor extends Executor {

    Scanner in;

    public UpdateExecutor(String url, String user, String pass, String driver) {
        super(url, user, pass, driver);
    }

    public void run(String input) {
        in = new Scanner(System.in);
        try {
            // Load the JDBC driver
            Class.forName(driver);
            // Make a connection
            connect = DriverManager.getConnection(url, user, pass);
            connect.setAutoCommit(false);

            switch (input) {
                case "1":
                    updateGamePrice();
                    break;
                case "2":
                    updatePublisherName();
                    break;
                default:
                    System.out.println("Invalid input, returning to main menu");
                    break;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver path incorrect, please try again");
            System.exit(0);
        } catch (SQLException e) {
            System.out.println("SQLException, please check your query and connection details");
        } finally {
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
                System.out.println("Connection not closed!");
            }
        }
    }

    private void updateGamePrice() {
        System.out.println("Enter the name of the game to update price:");
        String gameName = in.nextLine().trim();
        System.out.println("Enter the new price:");
        float newPrice = Float.parseFloat(in.nextLine().trim());

        String query = "UPDATE VIDEO_GAME SET Price = ? WHERE GameTitle = ?";
        try (PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setFloat(1, newPrice);
            pstmt.setString(2, gameName);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                connect.commit();
                System.out.println("Game price updated successfully.");
            } else {
                System.out.println("Game not found or price update failed.");
            }
        } catch (SQLException e) {
            System.out.println("Could not update the game price. Error: " + e.getMessage());
        }
    }

    private void updatePublisherName() {
        System.out.println("Enter the old name of the publisher:");
        String oldName = in.nextLine().trim();
        System.out.println("Enter the new name of the publisher:");
        String newName = in.nextLine().trim();

        String query = "UPDATE PUBLISHER SET PublisherName = ? WHERE PublisherName = ?";
        try (PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, oldName);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                connect.commit();
                System.out.println("Publisher name updated successfully.");
            } else {
                System.out.println("Publisher not found or name update failed.");
            }
        } catch (SQLException e) {
            System.out.println("Could not update the publisher name. Error: " + e.getMessage());
        }
    }
}