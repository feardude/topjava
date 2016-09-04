package ru.javawebinar.topjava;

import static org.junit.Assert.*;

import org.junit.Test;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class UserMealTest {

    UserMeal meal = new UserMeal(LocalDateTime.of(2016, Month.AUGUST, 20, 11, 0), "Завтрак", 500);

    @Test
    public void dateIsInRange() {
        boolean result = meal.isInRange(LocalTime.of(7, 0), LocalTime.of(12, 0));
        assertTrue(result);
    }

    @Test
    public void dateIsNotInRange() {
        boolean result = meal.isInRange(LocalTime.of(7, 0), LocalTime.of(9, 0));
        assertFalse(result);
    }

    @Test
    public void dateIsOnRangeBoundaries() {
        boolean result = meal.isInRange(LocalTime.of(7, 0), LocalTime.of(11, 0));
        assertTrue(result);
    }

}
