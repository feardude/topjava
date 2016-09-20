package ru.javawebinar.topjava;

import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Topjava Enterprise!");

        MealRepository mealRepo = new InMemoryMealRepositoryImpl();
        mealRepo.getAll(1).forEach(System.out::println);
    }
}
