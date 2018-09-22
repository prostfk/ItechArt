package by.itechart.contacts.dao;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T> {

    Connection connection;
    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);

    AbstractDao() {
        try{
            initDao("Database/src/main/resources/database.properties");
//            initDao("src/main/resources/database.properties");
        }catch (Exception e){
            log(e, LOGGER);
        }
    }

    private void initDao(String path) throws SQLException, IOException {
        Properties properties = new Properties();
            properties.load(new FileInputStream(path));//war style
            String url = properties.getProperty("spring.datasource.url");
            String username = properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");
            connection = DriverManager.getConnection(url,username,password);
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

    public static Map<String,String> getPrivateData(){
        Connection connection = new PhoneDao().connection;
        Map<String, String> map = new HashMap<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM private_data")) {
            while (resultSet.next()){
                if (resultSet.getLong("id")==1){
                    map.put("login", resultSet.getString("value"));
                }else if(resultSet.getLong("id")==2){
                    map.put("password", resultSet.getString("value"));
                }else{
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return map;
    }

    public abstract T findById(Long id);

    public abstract List<T> findAll();

    public abstract T save(T t);

    public abstract T update(Long id, T t);

    protected abstract T createEntity(ResultSet resultSet);

    protected void log(Exception e, Logger logger){
        e.printStackTrace();
        logger.error(e.getMessage());
    }

}
