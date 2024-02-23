package ser322;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ListExecutor extends Executor{

    public ListExecutor(String url, String user, String pass, String driver) {
        super(url, user, pass, driver);
    }

    public void run() {
        try {
            //Load the JDBC driver
            Class.forName(driver);
            //Make a connection
            connect = DriverManager.getConnection(url, user, pass);
            fullList();
        }
        catch(ClassNotFoundException e) {
            System.out.println("Driver path incorrect, please try again");
            System.exit(0);
        }
        catch(SQLException e) {
            System.out.println("SQLException, please fix query");
        }
    }
    public void fullList() {
        try {
            DatabaseMetaData meta = connect.getMetaData();
            //To get the names of all tables
            ResultSet tables = meta.getTables("GameExplorer", null, "%", null);

            //For every table
            while (tables.next()) {
                //Get and print table name
                String tableName = tables.getString("TABLE_NAME");
                System.out.println(tableName.toUpperCase());
                stmt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //Select all data in current table
                rs = stmt.executeQuery("SELECT * FROM " + tableName);
                ResultSetMetaData rsMeta = rs.getMetaData();
                //Get the number of columns
                int numColumns = rsMeta.getColumnCount();
                //Find the largest entry in the column, including the column name
                int[] columnWidth = new int[numColumns];
                for(int i = 1; i <= numColumns; i++) {
                    columnWidth[i - 1] = rsMeta.getColumnName(i).length();
                }
                while(rs.next()) {
                    for(int i = 1; i <= numColumns; i++) {
                        int length = rs.getString(i).length();
                        if (length > columnWidth[i - 1]) {
                            columnWidth[i - 1] = length;
                        }
                    }
                }

                //Print column names
                for (int i = 1; i <= numColumns; i++) {
                    System.out.printf("%-" + (columnWidth[i - 1] + 2) + "s", rsMeta.getColumnName(i));
                }
                System.out.println();

                //Go back to the start of the ResultSet
                rs.beforeFirst();
                //Iterate over ResultSet and print all entries in the current table
                while(rs.next()) {
                    for(int i = 1; i <= numColumns; i++) {
                        System.out.printf("%-" + (columnWidth[i - 1] + 2) + "s", rs.getString(i));
                    }
                    System.out.println();
                }
                //Close the ResultSet and Statement
                rs.close();
                stmt.close();
                System.out.println();
            }
            //Close the Connection
            connect.close();
        }
        catch(SQLException e) {
            System.out.println("Invalid SQL query, please fix and try again");
        }
    }
}
