package by.itechart.contacts.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public abstract class Dao<T> {

    protected Connection connection;

    public Dao() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
            String url = properties.getProperty("spring.datasource.url");
            String username = properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void execute(String sql){
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected ResultSet executeQuery(String sql){
        try {
            return connection.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public abstract void save(T t);

    public abstract List<T> findAll();

    public abstract void delete(Long id);

    public abstract T update(Long id, T t);


}
