package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class UserTestData {
    public static final int ADMIN_ID = START_SEQ;
    public static final int USER1_ID = START_SEQ + 1;
    public static final int USER2_ID = START_SEQ + 2;

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);
    public static final User USER1 = new User(USER1_ID, "User 1", "user1@yandex.ru", "password", Role.ROLE_USER);
    public static final User USER2 = new User(USER2_ID, "User 2", "user2@yandex.ru", "password", Role.ROLE_USER);

    public static final Map<Integer, User> USERS = new ConcurrentHashMap<>();
    static {
        USERS.put(ADMIN_ID, ADMIN);
        USERS.put(USER1_ID, USER1);
        USERS.put(USER2_ID, USER2);
    }

    public static final ModelMatcher<User> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.getCaloriesPerDay(), actual.getCaloriesPerDay())
                            && Objects.equals(expected.isEnabled(), actual.isEnabled())
//                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );
}