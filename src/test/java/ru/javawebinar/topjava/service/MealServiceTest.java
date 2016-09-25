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
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.USER1_ID;
import static ru.javawebinar.topjava.UserTestData.USER2_ID;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)

public class MealServiceTest {

    private static final Logger LOG = getLogger(MealServiceTest.class);
    private static int mealId = START_SEQ + 3;

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
        Meal expected = USERS_TO_MEALS.get(USER1_ID).get(mealId);
        MATCHER.assertEquals(expected, service.get(mealId, USER1_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() {
        service.get(1, 1);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(mealId, USER1_ID);

        List<Meal> user1Meals = USERS_TO_MEALS.get(USER1_ID).values().stream()
                .filter(m -> m.getId() != mealId)
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
        MATCHER.assertCollectionEquals(user1Meals, service.getAll(USER1_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() {
        service.delete(1, USER1_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteWrongUser() {
        service.delete(mealId, USER2_ID);
    }

    /*
       (2016, Month.SEPTEMBER, 24, 10, 20), "Каша", 400));
       (2016, Month.SEPTEMBER, 24, 15, 0), "Капуста с мясом", 650));
       (2016, Month.SEPTEMBER, 24, 17, 30), "Кофе с конфетами", 350));
     */

    @Test
    public void getBetweenDates() {
        LocalDate startDate = LocalDate.of(2016, Month.SEPTEMBER, 24);
        LocalDate endDate = LocalDate.of(2016, Month.SEPTEMBER, 25);

        Meal meal = service.save(new Meal(endDate.atTime(LocalTime.MIN), "Омлет с сыром", 500), USER1_ID);
        Collection<Meal> actual = service.getBetweenDates(startDate, endDate, USER1_ID);

        USERS_TO_MEALS.get(USER1_ID).put(meal.getId(), meal);
        Collection<Meal> expected = USERS_TO_MEALS.get(USER1_ID).values().stream()
                .filter(m -> TimeUtil.isBetween(m.getDate(), startDate, endDate))
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
        MATCHER.assertCollectionEquals(expected, actual);
    }

    @Test
    public void getBetweenDateTimes() {
//        service.getBetweenDateTimes();
    }

    @Test
    public void testGetAll() {
        List<Meal> user1Meals = USERS_TO_MEALS.get(USER1_ID).values().stream()
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());

        MATCHER.assertCollectionEquals(user1Meals, service.getAll(USER1_ID));
    }

    @Test
    public void testGetAllWrongUser() {
        Assert.assertTrue(service.getAll(1).isEmpty());
    }

    @Test
    public void testUpdate() {
        Meal updated = new Meal(mealId, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 10, 20), "Каша", 400);
        updated.setDescription("Updated description");
        service.update(updated, USER1_ID);
        MATCHER.assertEquals(updated, service.get(mealId, USER1_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateWrongUser() {
        Meal updated = new Meal(mealId, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 10, 20), "Каша", 400);
        updated.setDescription("Updated description");
        service.update(updated, USER2_ID);
    }

    @Test
    public void testSave() {
        service.save(new Meal(LocalDateTime.of(2016, Month.SEPTEMBER, 24, 10, 20), "Новая еда", 1000), USER1_ID);
        int expected = USERS_TO_MEALS.get(USER1_ID).size() + 1;
        Assert.assertEquals(expected, service.getAll(USER1_ID).size());
    }

}