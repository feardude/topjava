package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Meal save(Meal Meal, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("delete from meals where id=? and userid=?", id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> result =  jdbcTemplate.query(
                "select * from meals where id=? and userid=?", ROW_MAPPER, id, userId);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<Meal> getAll(int userId) {
//        return jdbcTemplate.query("select * from meals where userid=? order by datetime desc", ROW_MAPPER, userId);
        return getBetween(LocalDateTime.MIN, LocalDateTime.MAX, userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query(
                "select * from meals " +
                "where datetime <@ tsrange(?, ?)" +
                "and userid=?" +
                "order by datetime desc",
                ROW_MAPPER, startDate, endDate, userId);
    }
}
