package by.prostrmk.model.dao;

import by.prostrmk.model.Post;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class PostDao {

    private Connection connection;

    public PostDao() {
        Properties properties = new Properties();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            properties.load(new FileInputStream("/home/prostrmk/Documents/Programs/Java/Java EE/ITechArt/Pre-Cource-Tasks/SecondTask/src/main/resources/db.properties"));
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            String url = properties.getProperty("db.url");
            connection = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(Post post){
        try {
            connection.createStatement().execute(String.format("INSERT INTO post(description,path) VALUES('%s','%s')", post.getDescription(), post.getPathToPhoto()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
