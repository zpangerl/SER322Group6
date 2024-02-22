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
            ResultSet tables = meta.getTables("GameExplorer", null, "%", null);
            
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println(tableName.toUpperCase());
                stmt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs = stmt.executeQuery("SELECT * FROM " + tableName);
                ResultSetMetaData rsMeta = rs.getMetaData();
                int numColumns = rsMeta.getColumnCount();
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
                
                rs.beforeFirst();
                while(rs.next()) {
                    for(int i = 1; i <= numColumns; i++) {
                        System.out.printf("%-" + (columnWidth[i - 1] + 2) + "s", rs.getString(i));
                    }
                    System.out.println();
                }
                rs.close();
                stmt.close();
                System.out.println();
            }
            connect.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}