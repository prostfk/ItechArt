package by.prostrmk.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public interface Dao {

    default Connection init(){
        Properties properties = new Properties();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            properties.load(new FileInputStream("/home/prostrmk/Documents/Programs/Java/Java EE/ITechArt/Pre-Cource-Tasks/SecondTask/src/main/resources/db.properties"));
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            String url = properties.getProperty("db.url");
            return DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            for (int i = 0; i < 10; i++) {
                System.out.println("EXCEPTION!!!");
            }
            e.printStackTrace();
        }
        return null;
    }

}
