package com.wywrot.githubusers;

import com.wywrot.githubusers.data.UserDAO;
import com.wywrot.githubusers.data.model.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class UserDaoTest {

    @Mock
    public UserDAO userDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserDao() {
        User user = new User("testLogin", 123, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", true);
        when(userDao.getUserById(anyInt())).thenReturn(user);
        assertEquals("testLogin", userDao.getUserById(anyInt()).component1());
    }
}
