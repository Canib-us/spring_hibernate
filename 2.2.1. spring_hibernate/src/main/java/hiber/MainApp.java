package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
//
//      List<User> users = userService.listUsers();
//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println();
//      }


      Car car1 = new Car("Toyota Land Cruiser Prado", 200);
      User user1 = new User("Mark", "Avrely", "spqr@mail.ru", car1);

      Car car2 = new Car("BMW 3.0 CSI", 1978);
      User user2 = new User("Vladimir", "Monomach", "rus@mail.ru", car2);

      Car car3 = new Car("Volkswagen Beetle", 1976);
      User user3 = new User("Vladimir", "Lenin", "oktyabr@gmail.ru", car3);

      Car car4 = new Car("Mersedes-Benz SL Gullwing", 300);
      User user4 = new User("Nikolay", "Romanov", "empire@mail.ru", car4);

      Car car5 = new Car("Hummer", 2);
      User user5 = new User("Grakh", "Tibery", "patricia@mail.ru", car5);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);
      userService.add(user5);

      List<User> users = userService.listUsers();
      for (User user : users) {
         String haveCar = (user.getCar()!=null)
                 ?"Car: "+user.getCar().getModel()+" "+user.getCar().getSeries()
                 :"does not have a car";
         System.out.println("User: "+user.getFirstName()+" "+user.getLastName()+"\n"+"Email: "+user.getEmail()
                 +"\n"+haveCar+"\n");
      }

      User getUserByCar = userService.getUserByCar("Toyota Land Cruiser Prado", 201);
      String userNotFound = "The user's search for the car failed";
      if (getUserByCar != null) {
         System.out.println("User: "+getUserByCar.getFirstName()+" "+getUserByCar.getLastName()+"\n"+"Email: "
                 +getUserByCar.getEmail()+"\n"+"Car: "+getUserByCar.getCar().getModel()+" "+getUserByCar.getCar().getSeries());
      } else {
         System.out.println(userNotFound);
      }

      context.close();
   }
}
