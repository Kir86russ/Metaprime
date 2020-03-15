package app.logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    private Connection connection;

    private String url;
    private String username;
    private String password;


    public Connection connect() {
        getDatabaseConfigs();
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void disconnect(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database disconnection successful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDatabaseConfigs() {
        Properties property = new Properties();
        FileInputStream fileInput;
        try {
            fileInput = new FileInputStream("src/main/resources/databaseConfig.properties");
            property.load(fileInput);
            url = property.getProperty("db.url");
            username = property.getProperty("db.username");
            password = property.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
