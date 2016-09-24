package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;

import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.USER1_ID;


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
//        Meal expected = USERS_TO_MEALS.get(1);
//        MATCHER.assertEquals(expected, service.get(1, USER1_ID));
    }

    @Test
    public void delete() throws Exception {

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
    public void update() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

}