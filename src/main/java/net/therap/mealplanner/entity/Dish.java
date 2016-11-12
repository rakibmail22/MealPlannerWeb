package net.therap.mealplanner.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bashir
 * @since 10/17/16
 */
@Entity
@Table(name="dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", allocationSize = 1)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "mealDishes")
    private List<Meal> mealList;

    public Dish() {
        mealList = new ArrayList<Meal>();
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

    public String toString() {
        return "ID: " + id + " Name: " + name;
    }
}
