package model;
import java.sql.DriverManager;
import java.sql.Connection;

public class Connect{
    private String cadconexion = "jdbc:postgresql://localhost:5432/iosand";
    private String user = "paco";
    private String pass = "1234";
    private String driver = "org.postgresql.Driver";
    
    private Connection conn;

    public Connect(){
        try{
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(cadconexion, user, pass);
        }
        catch(Exception e) {}
    }

    public Connection getConn(){
        return conn;
    }
}
