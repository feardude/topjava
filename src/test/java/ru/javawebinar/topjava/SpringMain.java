package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

public class SpringMain {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("spring/spring-app-test.xml")) {

            System.out.println("--- BEANS ---");
            for (String bean : appContext.getBeanDefinitionNames()) {
                System.out.println(bean);
            }
            System.out.println("--- BEANS ---");

            AdminRestController adminUserController = appContext.getBean(AdminRestController.class);
            adminUserController.create(UserTestData.USER1);
            System.out.println();

            MealRestController mealController = appContext.getBean(MealRestController.class);

            List<MealWithExceed> filteredMealsWithExceeded =
                    mealController.getBetween(
                            LocalDate.of(2015, Month.MAY, 1), LocalTime.of(12, 0),
                            LocalDate.of(2015, Month.JUNE, 1), LocalTime.of(18, 0));
            filteredMealsWithExceeded.forEach(System.out::println);
        }
    }
}
