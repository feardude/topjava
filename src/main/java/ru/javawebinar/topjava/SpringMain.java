package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.web.meal.MealRestController;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
//            System.out.println("\n/----- Beans -----/");
//            Arrays.asList(appContext.getBeanDefinitionNames()).forEach(System.out::println);
//            System.out.println("/----- Beans -----/\n");

//            AdminRestController adminUserController = appContext.getBean(AdminRestController.class);
//            adminUserController.save(new User(1, "userName", "email", "password", Role.ROLE_ADMIN));
//            adminUserController.getAll().forEach(System.out::println);
            appContext.getBean(MealRestController.class).getAll().forEach(System.out::println);
        }
    }
}
