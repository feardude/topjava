package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MealRestController {
    private static final Logger LOG = getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll() {
        LOG.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int id) {
        LOG.info("get id=" + id);
        return service.get(id);
    }

    public Meal save(Meal meal) {
        meal.setId(AuthorizedUser.id());
        service.save(meal);
        return meal;
    }

    public void delete(int id) {
        LOG.info("delete id=" + id);
        service.delete(id);
    }

    public void update(int id, Meal meal) {
        LOG.info("update " + meal);
        service.update(id, meal);
    }

}
