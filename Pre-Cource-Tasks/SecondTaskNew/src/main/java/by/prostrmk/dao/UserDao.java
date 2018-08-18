package by.prostrmk.dao;


import by.prostrmk.model.entity.User;

import java.sql.ResultSet;

public class UserDao extends Dao {

    public User findUserByUsername(String username){
        //language=SQL
        ResultSet resultSet = executeQuery(String.format("SELECT * FROM user WHERE username='%s'", username));
        try{
            if (resultSet.next()){
               return new User(resultSet.getLong("id")
               ,resultSet.getString("username"),
                       resultSet.getString("password"));
            }
        }catch (Exception e){}
        return null;
    }

    public void save(User user){
        //language=SQL
        execute(String.format("INSERT into user(username, password) VALUES ('%s','%s')", user.getUsername(),user.getPassword()));
        System.out.println("SAVED");
    }

}
