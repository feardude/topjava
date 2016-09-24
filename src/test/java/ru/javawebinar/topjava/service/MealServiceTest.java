package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.USER1_ID;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)

public class MealServiceTest {

    private static final Logger LOG = getLogger(MealServiceTest.class);

    @Autowired
    protected MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        int mealId = START_SEQ + 3;
        Meal expected = USERS_TO_MEALS.get(USER1_ID).get(mealId);
        MATCHER.assertEquals(expected, service.get(mealId, USER1_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() {
        service.get(1, 1);
    }

    @Test
    public void testDelete() throws Exception {
        int mealId = START_SEQ + 3;
        service.delete(mealId, USER1_ID);

        List<Meal> user1Meals = USERS_TO_MEALS.get(USER1_ID).values().stream()
                .filter(m -> m.getId() != mealId)
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
        MATCHER.assertCollectionEquals(user1Meals, service.getAll(USER1_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() {
        service.delete(1, 1);
    }

    @Test
    public void getBetweenDates() throws Exception {

    }

    @Test
    public void getBetweenDateTimes() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {
        List<Meal> user1Meals = USERS_TO_MEALS.get(USER1_ID).values().stream()
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());

        MATCHER.assertCollectionEquals(user1Meals, service.getAll(USER1_ID));
    }

    @Test
    public void testGetAllNotFound() {
        Assert.assertTrue(service.getAll(1).isEmpty());
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

}