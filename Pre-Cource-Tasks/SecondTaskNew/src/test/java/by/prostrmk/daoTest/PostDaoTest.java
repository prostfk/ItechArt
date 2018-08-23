package by.prostrmk.daoTest;

import by.prostrmk.dao.PostDao;
import by.prostrmk.model.entity.Post;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class PostDaoTest {

    private PostDao postDao;

    @BeforeEach
    void setUp() {
        postDao = new PostDao();
    }

    @Test
    void findPostsByUserIdTest() {
        Post[] base = new Post[2];
        postDao.findPostsByUserId(2L).toArray(base);
        Post[] posts = new Post[]{
                new Post(9L,"asd","/resources/roman/bulb.jpg",2L),
                new Post(12L, "asd","/resources/roman/time_clock_history_coffee_java_by_matthew_kerslake_cc0_via_unsplash_1200x800-100765108-large.jpg", 2L)
        };
        System.out.println(Arrays.toString(base) + "\n" + Arrays.toString(posts));
        Assert.assertArrayEquals(base,posts);
    }
}
