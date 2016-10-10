package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository repository;

    @Override
    public Meal save(Meal meal, int userId) {
        return repository.saveByUserIdEquals(meal, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.deleteByIdAndUserId(id, userId);
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.findOne(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.findByUserIdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return repository.findByUserIdOrderByDateTimeDesc(userId).stream()
                .filter(m -> TimeUtil.isBetween(m.getDateTime(), startDate, endDate))
                .collect(Collectors.toList());
    }
}
