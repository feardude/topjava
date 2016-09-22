package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MealRestController {
    private static final Logger LOG = getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll() {
        LOG.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealWithExceed> getAll (String paramStartDate, String paramEndDate,
                                        String paramStartTime, String paramEndTime) {
        LOG.info("getAll filtered");

        LocalDate startDate = paramStartDate.isEmpty() ? LocalDate.MIN : LocalDate.parse(paramStartDate);
        LocalDate endDate = paramEndDate.isEmpty() ? LocalDate.MAX : LocalDate.parse(paramEndDate);
        LocalTime startTime = paramStartTime.isEmpty() ? LocalTime.MIN : LocalTime.parse(paramStartTime);
        LocalTime endTime = paramEndTime.isEmpty() ? LocalTime.MAX : LocalTime.parse(paramEndTime);

        return MealsUtil.getFilteredWithExceeded(service.getAll(AuthorizedUser.id(), startDate, endDate),
                                                 startTime, endTime, MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int id) {
        LOG.info("get id=" + id);
        return service.get(AuthorizedUser.id(), id);
    }

    public Meal save(Meal meal) {
        return service.save(AuthorizedUser.id(), meal);
    }

    public void delete(int id) {
        LOG.info("delete id=" + id);
        service.delete(AuthorizedUser.id(), id);
    }

    public void update(Meal meal) {
        LOG.info("update " + meal);
        service.update(AuthorizedUser.id(), meal);
    }

}
