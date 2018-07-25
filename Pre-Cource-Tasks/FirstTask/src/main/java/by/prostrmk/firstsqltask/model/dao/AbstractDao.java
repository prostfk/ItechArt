package by.prostrmk.firstsqltask.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class AbstractDao {

    public static Connection defaultConnection(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/database.properties"));
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            String url = properties.getProperty("db.url");
            return DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
