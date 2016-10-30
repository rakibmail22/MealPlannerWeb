package net.therap.mealplanner.entity;

import net.therap.mealplanner.service.MealPlanService;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bashir
 * @since 10/17/16
 */
@Entity
@Table(name = "tblMeal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(columnDefinition = "enum('B','L')")
    String type;
    @Column(columnDefinition = "enum('FRI','SAT','SUN','MON','TUE','WED','THU')")
    String day;
    @ManyToMany
    @JoinTable(
            name = "tblMeal_Dish",
            joinColumns = @JoinColumn(name = "mealId", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "dishId", nullable = false)
    )
    List<Dish> mealDishes;

    @ManyToMany
    @JoinTable(
            name = "tblMeal_User",
            joinColumns = @JoinColumn(name = "mealId", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "userId", nullable = false)
    )
    List<User> userList;

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
        MealPlanService mealPlanService = new MealPlanService();
        for (Dish dish : mealPlanService.getDishListByMeal(this)) {
            dishes += dish.getName() + ",";
        }
        return dishes.substring(0, dishes.length() - 1);
    }

    public String toString() {
        return "Meal Id: " + id + " Type: " + type + " Dishes: " + getMealDishesAsString() + " DAY: " + day;
    }
}
