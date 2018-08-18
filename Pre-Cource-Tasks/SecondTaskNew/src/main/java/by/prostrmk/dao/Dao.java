package by.prostrmk.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

class Dao {

    protected Connection connection;

    Dao() {
        Properties properties = new Properties();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            properties.load(new FileInputStream("/home/prostrmk/Documents/Programs/Java/Java EE/ITechArt/Pre-Cource-Tasks/SecondTaskNew/src/main/resources/db.properties"));
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            String url = properties.getProperty("db.url");
            connection = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected ResultSet executeQuery(String sql){
        try {
            return connection.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            return null;
        }
    }

    protected void execute(String sql){
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
