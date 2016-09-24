package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MealTestData {

    private static final Map<Integer, Meal> MEALS = new ConcurrentHashMap<>();
    static {
        MEALS.put(1, new Meal(1, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 10, 20), "Каша", 400));
        MEALS.put(2, new Meal(2, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 15, 0), "Капуста с мясом", 650));
        MEALS.put(3, new Meal(3, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 17, 30), "Кофе с конфетами", 350));
        MEALS.put(4, new Meal(4, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 9, 40), "Яичница с беконом", 550));
        MEALS.put(5, new Meal(5, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 14, 0), "Мясной пирог", 700));
        MEALS.put(6, new Meal(6, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 17, 30), "Чай с сендвичем", 350));
    }

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                    )
    );

}
