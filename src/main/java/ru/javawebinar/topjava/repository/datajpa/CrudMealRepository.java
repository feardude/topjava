package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> findByUserIdOrderByDateTimeDesc(int userId);

    @Transactional
    Meal saveByUserIdEquals(Meal meal, int userId);

    @Query("select m from Meal m where m.id=:id and m.user.id=:userId")
    Meal findOne(int id, int userId);

    @Transactional
    @Modifying
    boolean deleteByIdAndUserId(int id, int userId);
}
