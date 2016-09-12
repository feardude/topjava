package ru.javawebinar.topjava.model;

import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDAO {

    private Map<Integer, Meal> meals;
    private AtomicInteger counter;

    private static final Logger LOG = getLogger(MealDAO.class);

    public MealDAO() {
        meals = new HashMap<>();
        meals.put(1, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 450));
        meals.put(2, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 950));
        meals.put(3, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 600));
        meals.put(4, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 550));
        meals.put(5, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 800));
        meals.put(6, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 700));

        counter = new AtomicInteger(meals.size());
    }

    public void add(Meal m) {
        meals.put(counter.incrementAndGet(), m);

        LOG.info("Added new " + m.toString());
        LOG.debug("atomic counter = " + counter.get());
    }

    public List<Meal> retrieveAll() {
        return meals.values().stream()
                .collect(Collectors.toList());
    }

    public Meal retrieveByID(int id) {
        return null;
    }

    public void update(Meal m) {

    }

    public void delete() {

    }

}
