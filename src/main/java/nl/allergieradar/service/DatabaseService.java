package nl.allergieradar.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by bernd on 16-5-2017.
 */
public class DatabaseService {

    private static DatabaseService connectionInstance;

    private DatabaseService() {
        super();

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }

    public static DatabaseService getInstance() {
        if(connectionInstance == null) {
            synchronized (DatabaseService.class){
                if(connectionInstance == null){
                    connectionInstance = new DatabaseService();
                }
            }
        }
        return connectionInstance;
    }

    public Connection getConnection(String username, String password) throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/radar";

        Properties info = new Properties();
        Connection conn = DriverManager.getConnection(URL, username, password);
        return conn;
    }

}

