package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

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
        // TODO BUG: update creates new meal id
        // BaseEntity isNew() checks BaseEntity field instead of this.Meal
        if (meal.isNew())
            meal.setId(counter.incrementAndGet());
        meal.setUserId(userId);
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int userId, int id) {
        if (userId == repository.get(id).getUserId())
            return repository.remove(id) != null;
        return false;
    }

    @Override
    public Meal get(int userId, int id) {
        Meal meal = repository.get(id);
        if (userId == meal.getUserId())
            return meal;
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.values().stream()
                .filter(m -> m.getUserId() == userId)
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
    }
}

