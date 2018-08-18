package by.prostrmk.model.dao;

import by.prostrmk.model.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements Dao{

    private Connection connection;

    public UserDao(){
        connection = init();
    }

    public void save(User user){
        try {
            //language=SQL
            connection.createStatement().execute(String.format("INSERT INTO user(username, password) VALUES('%s','%s')", user.getUsername(),user.getPassword()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findUserByUsername(String username){
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM user WHERE username='" + username + "'")) {
            if (resultSet.next()){
                long id = resultSet.getLong("id");
                String password = resultSet.getString("password");
                return new User(id,username,password);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
