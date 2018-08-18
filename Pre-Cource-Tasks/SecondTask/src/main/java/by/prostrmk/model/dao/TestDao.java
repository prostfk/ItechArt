package by.prostrmk.model.dao;

import by.prostrmk.model.entity.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestDao implements Dao {
    
    private Connection connection;

    public TestDao() {
        connection = init();
    }
    
    public void save(String username, String path){
        //language=SQL
        try {
            connection.createStatement().execute(
                    String.format("INSERT INTO test(username, path) VALUES ('%s', '%s')",username,path)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Post> findPostsByUsername(String username){
        //language=SQL
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(
                    String.format("SELECT * FROM test WHERE username='%s'", username)
            );
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()){
                posts.add(new Post(
                        resultSet.getLong("id"),"",resultSet.getString("path")
                ));
            }
            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    
}
