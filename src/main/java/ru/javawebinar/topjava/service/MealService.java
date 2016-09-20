package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MealService {

    Meal save(int userId, Meal meal);

    void delete(int userId, int id) throws NotFoundException;

    Meal get(int userId, int id) throws NotFoundException;

    List<Meal> getAll(int userId);

    List<Meal> getAll(int userId, LocalDate startDate, LocalDate endDate);

    void update(int userId, Meal meal);

}
