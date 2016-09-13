package ru.javawebinar.topjava.web;


import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealDAO;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private int caloriesPerDay = 2000;
    private MealDAO dao;
    private static final String LIST_MEAL = "/listMeal.jsp";
    private static final String ADD_OR_UPDATE = "/meal.jsp";

    public MealServlet() {
        dao = new MealDAO();
    }

    private List<MealWithExceed> retrieveAllWithExceeded() {
        return MealsUtil.getMealWithExceeded(dao.retrieveAll(), this.caloriesPerDay);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("listMeal")) {
            LOG.debug("action=listMeal; forward to listMeal.jsp");
            req.setAttribute("meals", retrieveAllWithExceeded());
            forward = LIST_MEAL;
        }
        else if (action.equalsIgnoreCase("delete")) {
            LOG.debug("action=delete; forward to listMeal.jsp");
            forward = LIST_MEAL;
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
            req.setAttribute("meals", retrieveAllWithExceeded());
        }
        else if (action.equalsIgnoreCase("update")) {
            LOG.debug("action=update; forward to meal.jsp");
            forward = ADD_OR_UPDATE;
            int id = Integer.parseInt(req.getParameter("id"));
            Meal meal = dao.retrieveByID(id);
            req.setAttribute("meal", meal);
        }
        else if (action.equalsIgnoreCase("add")) {
            LOG.debug("action=add; forward to meal.jsp");
            forward = ADD_OR_UPDATE;
        }

        req.getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Meal meal = new Meal();
        meal.setDescription(req.getParameter("description"));
        meal.setCalories(Integer.parseInt(req.getParameter("calories")));
        meal.setDateTime(LocalDateTime.parse(req.getParameter("dateTime")));

        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            dao.add(meal);
        } else {
            meal.setId(Integer.parseInt(id));
            dao.update(meal);
        }

        req.setAttribute("meals", retrieveAllWithExceeded());
        req.getRequestDispatcher(LIST_MEAL).forward(req, resp);
    }
}
