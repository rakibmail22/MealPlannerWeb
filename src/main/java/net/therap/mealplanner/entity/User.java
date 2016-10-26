package net.therap.mealplanner.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author bashir
 * @since 10/17/16
 */
@Entity
@Table(name = "tblUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String password;
    String email;
    @ManyToMany(mappedBy = "userList")
    List<Meal> mealList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Meal> getMealList() {
        return mealList;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return name;
    }

}
