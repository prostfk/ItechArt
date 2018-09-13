package by.itechart.contacts.dao;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public abstract class Dao<T> {

    protected Connection connection;
    private static final Logger LOGGER = Logger.getLogger(Dao.class);

    public Dao() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("Database/src/main/resources/database.properties"));
            String url = properties.getProperty("spring.datasource.url");
            String username = properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Connection error: " + e.getMessage());
        }
    }

    protected void execute(String sql){
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println(String.format("Exception during '%s'", sql));
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            LOGGER.error(String.format("Exception during '%s'", sql));
        }
    }

    protected ResultSet executeQuery(String sql){
        try {
            return connection.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(String.format("Exception during '%s'", sql));
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            LOGGER.error(String.format("Exception during '%s'", sql));
            return null;
        }
    }


    public abstract void save(T t);

    public abstract List<T> findAll();

    public abstract void delete(Long id);

    public abstract T update(Long id, T t);


}
