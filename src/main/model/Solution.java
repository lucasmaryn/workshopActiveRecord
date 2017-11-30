package main.model;

import java.util.Date;

public class Solution {
    private int id;
    private Date created;
    private Date updated;
    private String description;


    public Solution() {
    }

    public Solution(Date created, Date updated, String description) {
        this.id = 0;
        setCreated(created).setUpdated(updated).setDescription(description);
    }

    public Date getCreated() {
        return created;
    }

    public Solution setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Date getUpdated() {
        return updated;
    }

    public Solution setUpdated(Date updated) {
        this.updated = updated;
        return this;

    }

    public String getDescription() {
        return description;
    }

    public Solution setDescription(String description) {
        this.description = description;
        return this;

    }
}
