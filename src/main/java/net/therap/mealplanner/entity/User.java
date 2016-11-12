package net.therap.mealplanner.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bashir
 * @since 10/17/16
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", allocationSize = 1)
    private int id;

    private String name;

    private String password;

    private String email;

    private String role;

    @ManyToMany
    @JoinTable(
            name = "meal_user",
            joinColumns = @JoinColumn(name = "userId", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "mealId", nullable = false)
    )
    List<Meal> mealList;

    public User() {
        mealList = new ArrayList<>();
    }

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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return name;
    }

}
