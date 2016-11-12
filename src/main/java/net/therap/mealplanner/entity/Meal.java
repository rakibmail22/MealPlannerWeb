package net.therap.mealplanner.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bashir
 * @since 10/17/16
 */
@Entity
@Table(name = "meal")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", allocationSize = 1)
    private int id;

    @Column(columnDefinition = "enum('B','L')")
    private String type;

    @Column(columnDefinition = "enum('FRI','SAT','SUN','MON','TUE','WED','THU')")
    private String day;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "meal_dish",
            joinColumns = @JoinColumn(name = "mealId", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "dishId", nullable = false)
    )
    private List<Dish> mealDishes;

    @ManyToMany(mappedBy = "mealList")
    private List<User> userList;

    public Meal() {
        mealDishes = new ArrayList<Dish>();
        userList = new ArrayList<User>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Dish> getMealDishes() {
        return mealDishes;
    }

    public List<User> getUserList() {
        return userList;
    }

    public String getMealDishesAsString() {
        String dishes = "";
        for (Dish dish : getMealDishes()) {
            dishes += dish.getName() + ",";
        }
        return dishes.substring(0, dishes.length() - 1);
    }

    public String toString() {
        return "Meal Id: " + id + " Type: " + type + " Dishes: " + getMealDishesAsString() + " DAY: " + day;
    }
}
