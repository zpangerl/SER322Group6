package ser322;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Executor{

    ResultSet rs;
    Statement stmt;
    Connection connect;
    PreparedStatement pstmt;
    String url;
    String user;
    String pass;
    String driver;

    public Executor(String url, String user, String pass, String driver){
        rs = null;
        stmt = null;
        connect = null;
        pstmt = null;
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.driver = driver;
    }
}