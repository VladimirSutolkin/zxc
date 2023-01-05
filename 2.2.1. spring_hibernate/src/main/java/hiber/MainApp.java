package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;


public class MainApp {
   public static void main(String[] args)  {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User Vova1 = new User("Vova1", "Sutokin2", "Surolkin1@mail.com");
      User Vova2 = new User("Vova2", "Sutokin2", "Surolkin2@mail.com");
      User Vova3 = new User("Vova3", "Sutokin3", "Surolkin3@mail.com");
      User Vova4 = new User("Vova4", "Sutokin4", "Surolkin4@mail.com");

      Car Mers = new Car("Mers", 230);
      Car Audi = new Car("AUDI", 6);
      Car WV = new Car("WV", 4);
      Car BMV = new Car("BMV", 3);

      userService.add(Vova1.setCar(Mers).setUser(Vova1));
      userService.add(Vova2.setCar(Audi).setUser(Vova2));
      userService.add(Vova3.setCar(WV).setUser(Vova3));
      userService.add(Vova4.setCar(BMV).setUser(Vova4));


      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }


      System.out.println(userService.getUserByCar("WV", 4));


      try {
         User notFoundUser = userService.getUserByCar("Lada", 2110);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}