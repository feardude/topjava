package ru.javawebinar.topjava.web;


import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    private int caloriesPerDay = 2000;
    private List<Meal> meals = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 450),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 950),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 600),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 550),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 800),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 700)
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("forward to mealList.jsp");

        req.setAttribute("meals", getMealWithExceeded(this.meals));
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }

    private List<MealWithExceed> getMealWithExceeded(List<Meal> meals) {
        Map<LocalDate, Integer> dayToCalories = meals.stream()
                .collect(Collectors.toMap(
                        Meal::getDate,
                        Meal::getCalories,
                        Integer::sum)
                );

        return meals.stream()
                .map(m -> new MealWithExceed(m, dayToCalories.get(m.getDate()) > this.caloriesPerDay))
                .collect(Collectors.toList());
    }
}