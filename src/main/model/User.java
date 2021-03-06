package main.model;

import main.jbcrypt.BCrypt;
import main.resources.sql.DbConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String salt;
    private int user_group_id;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getPersonGroupId() {
        return user_group_id;
    }

    public User setPersonGroupId(int personGroupId) {
        this.user_group_id = personGroupId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, salt);
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString(){
        return "id: "+this.id+" username: "+this.username+" email:"+this.email
                +" password:" + this.password+" user_group_id:" + this.user_group_id
                +"\n";
    }

    public User() {
//        this.id = 1;

    }

    public User(String username, String email, String password, int personGroupId){
        this.id = 0;
        setUsername(username).
                setEmail(email).
                setPassword(password).
                setPersonGroupId(personGroupId);
    }

    //methods DB

    public void saveToDB() {
        if (this.id==0) {
            try {
                String generatedColumns[] = { "ID" };
                PreparedStatement prpstmnt = DbConnectionManager.getPreparedStatement("INSERT INTO user(username,email,password,salt) VALUES (?,?,?,?)",generatedColumns);
                prpstmnt.setString(1, this.username);
                prpstmnt.setString(2, this.email);
                prpstmnt.setString(3, this.password);
                prpstmnt.setString(4, this.salt);
                prpstmnt.executeLargeUpdate();
                ResultSet result = prpstmnt.getGeneratedKeys();

                if (result.next()) {
                    this.id = result.getInt(1);

                    System.out.println("Inserted ID -" + this.id);

                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            try {
                PreparedStatement prpstmnt = DbConnectionManager.getPreparedStatement("UPDATE user SET username = ?, email = ?, password = ?, salt = ? WHERE id = ?");
                prpstmnt.setString(1, this.username);
                prpstmnt.setString(2, this.email);
                prpstmnt.setString(3, this.password);
                prpstmnt.setString(4, this.salt);
                prpstmnt.setInt(5, this.id);
                prpstmnt.executeLargeUpdate();
                System.out.println("else, Inserted ID -" + this.id);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void delete(){
        String sql = "DELETE FROM user WHERE id= ?";
        try{
            if(this.id!=0){
                PreparedStatement stmt = DbConnectionManager.getPreparedStatement(sql);
                stmt.setInt(1, this.id);
                stmt.executeUpdate();
                this.id=0;
            }
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // static methods
    public static User loadById(int id){
        try {
            String sql = "SELECT * FROM user where id=?";
            PreparedStatement stmt = DbConnectionManager.getPreparedStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                User loadedUser = new User();
                loadedUser.id = resultSet.getInt("id");
                loadedUser.username = resultSet.getString("username");
                loadedUser.password = resultSet.getString("password");
                loadedUser.email = resultSet.getString("email");
                loadedUser.salt = resultSet.getString("salt");
                return loadedUser;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static ArrayList<User> loadAllUsers(){
        String sql = "SELECT * FROM user";
        PreparedStatement stmt = DbConnectionManager.getPreparedStatement(sql);
        return getUsersFromStatement(stmt);
    }
    private static ArrayList<User> getUsersFromStatement(PreparedStatement stmt) {
        try {
            ArrayList<User> users = new ArrayList<User>();
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                User loadedUser = new User();
                loadedUser.id = resultSet.getInt("id");
                loadedUser.username = resultSet.getString("username");
                loadedUser.password = resultSet.getString("password");
                loadedUser.email = resultSet.getString("email");
                loadedUser.salt = resultSet.getString("salt");
                loadedUser.user_group_id = resultSet.getInt("user_group_id");
                users.add(loadedUser);
            }
            return users;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
