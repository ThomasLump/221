package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    private static final Logger logger = LogManager.getLogger(AppConfig.class);

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("model1", 1)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("model2", 2)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("model3", 3)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("model4", 4)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("CarId = " + user.getCar().getId());
            System.out.println("CarModel = " + user.getCar().getModel());
            System.out.println("CarSeries = " + user.getCar().getSeries());
            System.out.println();
        }

        context.close();
    }
}
