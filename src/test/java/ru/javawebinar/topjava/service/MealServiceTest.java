package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)

public class MealServiceTest {

    private static final int MEAL_ID = START_SEQ + 3;
    private static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();

    @Autowired
    protected MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() {
        dbPopulator.execute();
    }

    @Test
    public void testGet() {
        MATCHER.assertEquals(meal1, service.get(meal1.getId(), USER1_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() {
        service.get(1, 1);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL_ID, USER1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(meal3, meal2), service.getAll(USER1_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() {
        service.delete(1, USER1_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteWrongUser() {
        service.delete(MEAL_ID, USER2_ID);
    }

    @Test
    public void testGetBetweenDates() {
        LocalDate startDate;
        LocalDate endDate = startDate = LocalDate.of(2016, Month.SEPTEMBER, 25);

        Collection<Meal> actual = service.getBetweenDates(startDate, endDate, USER1_ID);
        Collection<Meal> expected = Collections.singletonList(meal3);

        MATCHER.assertCollectionEquals(expected, actual);
    }

    @Test
    public void testGetBetweenDateTimes() {
        LocalDateTime startDateTime = LocalDateTime.of(2016, Month.SEPTEMBER, 24, 12, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2016, Month.SEPTEMBER, 24, 18, 0);

        Collection<Meal> expected = Collections.singletonList(meal2);
        Collection<Meal> actual = service.getBetweenDateTimes(startDateTime, endDateTime, USER1_ID);

        MATCHER.assertCollectionEquals(expected, actual);
    }

    @Test
    public void testGetAll() {
        List<Meal> expected = Arrays.asList(meal3, meal2, meal1);
        MATCHER.assertCollectionEquals(expected, service.getAll(USER1_ID));
    }

    @Test
    public void testGetAllWrongUser() {
        Assert.assertTrue(service.getAll(1).isEmpty());
    }

    @Test
    public void testUpdate() {
        Meal updated = new Meal(MEAL_ID, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 10, 20), "Каша", 400);
        updated.setDescription("Updated description");
        service.update(updated, USER1_ID);
        MATCHER.assertEquals(updated, service.get(MEAL_ID, USER1_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateWrongUser() {
        Meal updated = new Meal(MEAL_ID, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 10, 20), "Каша", 400);
        updated.setDescription("Updated description");
        service.update(updated, USER2_ID);
    }

    @Test
    public void testSave() {
        service.save(new Meal(LocalDateTime.of(2016, Month.SEPTEMBER, 24, 10, 20), "Новая еда", 1000), USER1_ID);
        Assert.assertEquals(4, service.getAll(USER1_ID).size());
    }
}