package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private static final AtomicInteger counter = new AtomicInteger();
//    public static List<User> USERS = new ArrayList<>(Arrays.asList(
//            new User(counter.incrementAndGet(), "admin", "admin@topjava.ru", "admin", Role.ROLE_ADMIN),
//            new User(counter.incrementAndGet(), "user1", "user1@topjava.ru", "user1", Role.ROLE_USER),
//            new User(counter.incrementAndGet(), "vasya", "vasya@topjava.ru", "vasya", Role.ROLE_USER),
//            new User(counter.incrementAndGet(), "kimbabig", "kimbabig@topjava.ru", "kimbabig", Role.ROLE_USER),
//            new User(counter.incrementAndGet(), "boris", "boris@topjava.ru", "boris", Role.ROLE_USER),
//            new User(counter.incrementAndGet(), "user3", "user3@topjava.ru", "user3", Role.ROLE_USER)
//    ));
    private static Map<Integer, User> USERS = new ConcurrentHashMap<>();

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        return USERS.remove(id) != null;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        USERS.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return USERS.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return USERS.values().stream()
                .sorted((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        return USERS.values().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst().orElse(null);
    }
}
