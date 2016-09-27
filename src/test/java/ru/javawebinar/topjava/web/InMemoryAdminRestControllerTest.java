package ru.javawebinar.topjava.web;

import org.junit.*;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;

import java.util.Collection;

import static ru.javawebinar.topjava.UserTestData.ADMIN;

public class InMemoryAdminRestControllerTest {

    private final InMemoryUserRepositoryImpl repository = new InMemoryUserRepositoryImpl();

    @Test
    public void testDelete() {
        repository.delete(UserTestData.USER1_ID);
        Collection<User> actual = repository.getAll();

//        Assert.assertEquals(actual.size(), USERS.size() - 1);
        Assert.assertEquals(actual.iterator().next(), ADMIN);
    }

    @Test
    public void testDeleteNotFound() {
        Assert.assertFalse(repository.delete(10));
    }
}