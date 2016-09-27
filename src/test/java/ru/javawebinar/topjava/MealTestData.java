package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    private static int counter = START_SEQ + 3;

    public static Meal meal1 = new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 10, 20), "Каша", 400);
    public static Meal meal2 = new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 15, 0), "Капуста с мясом", 650);
    public static Meal meal3 = new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 25, 17, 30), "Кофе с конфетами", 350);
    public static Meal meal4 = new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 9, 40), "Яичница с беконом", 550);
    public static Meal meal5 = new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 14, 0), "Мясной пирог", 700);
    public static Meal meal6 = new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 17, 30), "Чай с сендвичем", 350);
}
