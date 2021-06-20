package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car(6, "BMW")));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car(600, "Mercedes-Benz")));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car(406, "Peugeot")));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car(401, "Moscvich")));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(user.getCar());
            System.out.println();
        }

        System.out.println(userService.getUserByCarModelAndSeries("Moscvich", 401));
        System.out.println(userService.getUserByCarModelAndSeries("BMW", 6));
        System.out.println(userService.getUserByCarModelAndSeries("Mercedes-Benz", 600));
        System.out.println(userService.getUserByCarModelAndSeries("Peugeot", 406));
        context.close();
    }
}
