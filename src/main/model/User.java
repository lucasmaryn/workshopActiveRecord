package main.model;

import main.jbcrypt.BCrypt;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String salt;
    private int personGroupId;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getPersonGroupId() {
        return personGroupId;
    }

    public User setPersonGroupId(int personGroupId) {
        this.personGroupId = personGroupId;
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
    }

    public User(String username, String email, String password, int personGroupId){
        this.id = 0;
        setUsername(username).
                setEmail(email).
                setPassword(password).
                setPersonGroupId(personGroupId);
    }
}
