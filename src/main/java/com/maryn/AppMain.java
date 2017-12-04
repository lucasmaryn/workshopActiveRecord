package main.java.com.maryn;

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

//        Printing user with selected ID
        /*
        User loadedUser00 = new User();
        System.out.println("User with id 1:\n" + loadedUser00.loadById(1) +" \n");
        System.out.println("User with id 2:\n" +  loadedUser00.loadById(2) +" \n");
        System.out.println("User with id 3:\n" +  loadedUser00.loadById(3) +" \n");
        */

//        Reading object from DB
        /*
        User loadedUser00 = new User();
        System.out.println( loadedUser00.loadAllUsers().toString() );
        */

//        Adding and deleting user
        /*
        User user03 = new User();
        user03.setEmail("john.bro@gmail.com")
                .setPassword("password342")
                .setUsername("John.Bro");
        user03.saveToDB();
        System.out.println(user03);

        user03.delete();
        System.out.println(user03);
        */

//        Modification of user
        /*
        User user04 = new User();
        user04 = user04.loadById(3);
        user04.setUsername("Thomas.Goldfish").setEmail("thomas.goldfish@gmail.com").saveToDB();
        System.out.println(user04);

        User user05 = new User();
        user05 = user05.loadById(3);
        System.out.println(user05);
        */

//        stopApplication();
    }

    static void stopApplication(){
        DbConnectionManager.close();
    }
}
