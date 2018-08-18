package by.prostrmk.dao;

import by.prostrmk.model.entity.Post;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostDao extends Dao {

    public void save(Post post){
        //language=SQL
        execute(String.format("INSERT into post(title, path, user_id) values('%s','%s','%d')", post.getTitle(),post.getPath(),post.getUserId()));
    }

    public List<Post> findPostsByUserId(long userId){
        //language=SQL
        ResultSet resultSet = executeQuery(String.format("SELECT * FROM post WHERE user_id='%d'", userId));
        List<Post> posts = new ArrayList<>();
        try {
            while (resultSet.next()){
                posts.add(new Post(
                        resultSet.getLong("id"),resultSet.getString("title"),
                        resultSet.getString("path"),resultSet.getLong("user_id")
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return posts;
    }

}
