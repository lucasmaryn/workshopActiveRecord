package main.model;

import main.jbcrypt.BCrypt;
import main.resources.sql.DbConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String salt;
    private int person_group_id;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getPersonGroupId() {
        return person_group_id;
    }

    public User setPersonGroupId(int personGroupId) {
        this.person_group_id = personGroupId;
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
        return "id: "+this.id+" username: "+this.username+" email:"+this.email+" password:" + this.password;
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


}
