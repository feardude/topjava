package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class UserMealWithExceed {

    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean exceed;


    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    public UserMealWithExceed(UserMeal meal, boolean exceed) {
        this.dateTime = meal.getDateTime();
        this.description = meal.getDescription();
        this.calories = meal.getCalories();
        this.exceed = exceed;
    }

    public String getDescription() {
        return this.description;
    }
    public LocalTime getTime() {
        return this.dateTime.toLocalTime();
    }
    public LocalDate getDate() {
        return this.dateTime.toLocalDate();
    }
    public int getCalories() {
        return this.calories;
    }
    public boolean getExceed() {
        return this.exceed;
    }
}
