package main.model;

public class UserGroup {
    private int id;
    private String name;

    public UserGroup() {
    }

    public UserGroup(String name) {
        this.id = 0;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
