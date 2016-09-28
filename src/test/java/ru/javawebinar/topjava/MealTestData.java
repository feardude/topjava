package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    private static int counter = START_SEQ + 3;

    // Only User1 is needed for tests
    public static final Meal MEAL_1 = new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 10, 20), "Каша", 400);
    public static final Meal MEAL_2 = new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 15, 0), "Капуста с мясом", 650);
    public static final Meal MEAL_3 = new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 25, 17, 30), "Кофе с конфетами", 350);
}
