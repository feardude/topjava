package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;


public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

        for (UserMealWithExceed m : filteredWithExceeded) {
            System.out.println(m.toString());
        }

    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList,
                                                                    LocalTime startTime, LocalTime endTime,
                                                                    int caloriesPerDay) {

        List<UserMealWithExceed> result = new ArrayList<>();
        Map<LocalDate, Integer> calories = new HashMap<>();

        //--- Loops ---
        // count calories per each day
        for (UserMeal userMeal : mealList) {
            LocalDate date = userMeal.getDate();
            calories.merge(date, userMeal.getCalories(), Integer::sum);

//            int count = calories.getOrDefault(date, 0);
//            calories.put(date, count + userMeal.getCalories());
        }

        // yield filtered list
        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getTime(), startTime, endTime)) {
                boolean exceed = calories.get(userMeal.getDate()) > caloriesPerDay;
                result.add(new UserMealWithExceed(userMeal, exceed));
            }
        }
        //--- Loops ---


        //---Streams---
        // count calories per each day
        mealList.stream()
                .forEach(m -> calories.merge(m.getDate(), m.getCalories(), Integer::sum));


        // yield filtered list
        mealList.stream()
                .filter(m -> TimeUtil.isBetween(m.getTime(), startTime, endTime))
                .forEach(m -> result.add(new UserMealWithExceed
                                            (m, calories.get(m.getDate()) > caloriesPerDay)));

        //---Streams---

        return result;
    }
}
