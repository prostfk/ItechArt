package by.prostrmk.model.dao;

import by.prostrmk.model.entity.Post;
import by.prostrmk.model.entity.User;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PostDao implements Dao{

    private Connection connection;

    public PostDao() {
        connection = init();
    }

    public void save(Post post){
        try {
            //language=SQL
            connection.createStatement().execute(String.format("INSERT INTO post(description,path,user_id) VALUES('%s','%s','%s')", post.getDescription(), post.getPathToPhoto(), post.getUserId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Post findById(String description){
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM post WHERE id='%s'", description));
            if (resultSet.next()){
                long id = resultSet.getLong("id");
                String description1 = resultSet.getString("description");
                String path = resultSet.getString("path");
                return new Post(id,description,path);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Post> findPostsByUser(User user){
        List<Post> posts = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM post WHERE user_id='" + user.getId() + "'");
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String description = resultSet.getString("description");
                String path = resultSet.getString("path");
                long user_id = resultSet.getLong("user_id");
                posts.add(new Post(id,description,path,user_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

}
