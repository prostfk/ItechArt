package by.itechart.contacts.dao;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public abstract class AbstractDao<T> {

    Connection connection;
    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);

    AbstractDao() {
        Properties properties = new Properties();
        try {
//            properties.load(new FileInputStream("Database/src/main/resources/database.properties"));//war style
            properties.load(new FileInputStream("src/main/resources/database.properties"));//for tests
            String url = properties.getProperty("spring.datasource.url");
            String username = properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Connection error: " + e.getMessage());
        }
    }

    void execute(String sql){
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println(String.format("Exception during '%s'", sql));
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            LOGGER.error(String.format("Exception during '%s'", sql));
        }
    }

    ResultSet executeQuery(String sql){
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


    List<T> createList(ResultSet resultSet) {
        List<T> list = new LinkedList<>();
        try{
            while (resultSet.next()){
                list.add(createEntity(resultSet));
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return list;
    }


    void execute(PreparedStatement preparedStatement, Object... args) {
        int index = 1;
        try{
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(index++, args[i]);
            }
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    ResultSet executeQuery(PreparedStatement preparedStatement, Object... args) {
        int index = 1;
        try{
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(index++, args[i]);
            }
            return preparedStatement.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }


    public abstract T findById(Long id);

    public abstract List<T> findAll();

    public abstract T save(T t);

    public abstract T update(Long id, T t);

    public abstract T createEntity(ResultSet resultSet);
}
