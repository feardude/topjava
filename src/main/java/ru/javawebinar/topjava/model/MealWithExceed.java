package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class MealWithExceed {
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean exceed;
    private final int id;

    public MealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed, int id) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        this.id = id;
    }
    public MealWithExceed(Meal meal, boolean exceed) {
        this(meal.getDateTime(),
             meal.getDescription(),
             meal.getCalories(),
             exceed,
             meal.getId());
    }

    @Override
    public String toString() {
        return "MealWithExceed{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public String getDescription() {
        return description;
    }
    public int getCalories() {
        return calories;
    }
    public boolean isExceed() {
        return exceed;
    }
    public int getId() {
        return id;
    }
}
