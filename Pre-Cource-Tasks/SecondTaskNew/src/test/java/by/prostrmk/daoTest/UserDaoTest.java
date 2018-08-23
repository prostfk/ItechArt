package by.prostrmk.daoTest;


import by.prostrmk.dao.UserDao;
import by.prostrmk.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

public class UserDaoTest {

    private UserDao userDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDao();
    }


    @Test
    void findUserByUsernameTest() {
        User roman = userDao.findUserByUsername("roman");
        User me = new User(2L,"roman","202cb962ac59075b964b07152d234b70");
        assertEquals(me,roman);
    }
}
