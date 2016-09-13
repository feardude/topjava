package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class Meal {
    private LocalDateTime dateTime;
    private String description;
    private int calories;
    private int id;

    public Meal(LocalDateTime dateTime, String description, int calories, int id) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.id = id;
    }
    public Meal() {}

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public String getDescription() {
        return description;
    }
    public int getCalories() {
        return calories;
    }
    public int getId() {
        return id;
    }
    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }
    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
