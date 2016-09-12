package ru.javawebinar.topjava.web;


import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealDAO;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private int caloriesPerDay = 2000;
    private MealDAO dao;

    public MealServlet() {
        dao = new MealDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("listMeal")) {
            req.setAttribute("meals", MealsUtil.getMealWithExceeded(dao.retrieveAll(), this.caloriesPerDay));
            forward = "/listMeal.jsp";
            LOG.info("Action = listMeal; forward to /listMeal.jsp");
        }

        req.getRequestDispatcher(forward).forward(req, resp);
    }
}
