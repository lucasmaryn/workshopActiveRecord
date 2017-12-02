package main.java.com.maryn;

import main.model.User;

public class AppMain {
    public static void main(String[] args) {
        User user00 = new User();
        user00.setEmail("john.borys@gmail.com")
                .setPassword("password123")
                .setUsername("John.Borys");
        user00.saveToDB();

        User user01 = new User();
        user01.setEmail("john.doe@gmail.com")
                .setPassword("password321")
                .setUsername("John.Doe");
        user01.saveToDB();

        System.out.println(user00);

        System.out.println(user01);
    }
}
