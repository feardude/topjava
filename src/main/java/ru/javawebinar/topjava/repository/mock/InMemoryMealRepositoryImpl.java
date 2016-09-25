package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m -> this.save(1, m));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
        }
        Meal result = repository.get(meal.getId());
        if (result != null)
            return result.getUserId() == userId ? repository.put(meal.getId(), meal) : null;
        else
            return null;
    }

    @Override
    public boolean delete(int userId, int id) {
        Meal meal = repository.get(id);
        return meal != null && meal.getUserId() == userId && repository.remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        Meal meal = repository.get(id);
        return meal.getUserId() == userId ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getAll(userId, LocalDate.MIN, LocalDate.MAX);
    }

    @Override
    public List<Meal> getAll(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.values().stream()
                .filter(m -> m.getUserId() == userId)
                .filter(m -> m.getDate().compareTo(startDate) >= 0 && m.getDate().compareTo(endDate) <= 0)
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
    }
}

