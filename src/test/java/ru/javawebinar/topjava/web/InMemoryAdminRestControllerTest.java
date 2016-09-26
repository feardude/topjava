package ru.javawebinar.topjava.web;

import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Collection;

import static ru.javawebinar.topjava.UserTestData.ADMIN;

public class InMemoryAdminRestControllerTest {

    private final InMemoryUserRepositoryImpl repository = new InMemoryUserRepositoryImpl();

    @Test
    public void testDelete() {
        repository.delete(UserTestData.USER1_ID);
        Collection<User> actual = repository.getAll();

        Assert.assertEquals(actual.size(), USERS.size() - 1);
        Assert.assertEquals(actual.iterator().next(), ADMIN);
    }

    @Test
    public void testDeleteNotFound() {
        Assert.assertFalse(repository.delete(10));
    }
}