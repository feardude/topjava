package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static ru.javawebinar.topjava.UserTestData.USER1_ID;
import static ru.javawebinar.topjava.UserTestData.USER2_ID;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    private static int counter = START_SEQ + 3;
    // Map userId -> (mealId -> meal)
    public static final Map<Integer, Map<Integer, Meal>> USERS_TO_MEALS = new ConcurrentHashMap<>();
    static {
        Map<Integer, Meal> meals1 = USERS_TO_MEALS.computeIfAbsent(USER1_ID, ConcurrentHashMap::new);
        meals1.put(counter, new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 10, 20), "Каша", 400));
        meals1.put(counter, new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 15, 0), "Капуста с мясом", 650));
        meals1.put(counter, new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 17, 30), "Кофе с конфетами", 350));

        Map<Integer, Meal> meals2 = USERS_TO_MEALS.computeIfAbsent(USER2_ID, ConcurrentHashMap::new);
        meals2.put(counter, new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 9, 40), "Яичница с беконом", 550));
        meals2.put(counter, new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 14, 0), "Мясной пирог", 700));
        meals2.put(counter, new Meal(counter++, LocalDateTime.of(2016, Month.SEPTEMBER, 24, 17, 30), "Чай с сендвичем", 350));
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
