package main.java.com.maryn;

import main.model.User;
import main.resources.sql.DbConnectionManager;

public class AppMain {
    public static void main(String[] args) {

//        Adding new users
        /*
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

        User user02 = new User();
        user02.setEmail("john.paster@gmail.com")
                .setPassword("password3322")
                .setUsername("John.Paster");
        user02.saveToDB();
        */

//      Printing users with selected ID
        User loadedUser00 = new User();
        System.out.println("User with id 1:\n" + loadedUser00.loadById(1) +" \n");
        System.out.println("User with id 2:\n" +  loadedUser00.loadById(2) +" \n");
        System.out.println("User with id 3:\n" +  loadedUser00.loadById(3) +" \n");

        stopApplication();
    }

    static void stopApplication(){
        DbConnectionManager.close();
    }
}
