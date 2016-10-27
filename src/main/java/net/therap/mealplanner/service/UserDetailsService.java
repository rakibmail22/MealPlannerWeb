package net.therap.mealplanner.service;

import net.therap.mealplanner.dao.UserDaoImpl;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bashir
 * @since 10/16/16
 */
public class UserDetailsService {

    public User getUserByUserId(int userId) {

        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getUserById(userId);
        return user;

    }

    public void viewWeeklyMealItems(User user) {
        UserDetailsService userDetailsService = new UserDetailsService();
        MealPlanService mealPlanService = new MealPlanService();
        List<Meal> userMealList = userDetailsService.getMealListByUser(user);
        Map<String, List<Meal>> dayMealMap = new HashMap<String, List<Meal>>();
        for (Meal userMeal : userMealList) {
            List<Meal> mealList = dayMealMap.get(userMeal.getDay());
            if (mealList == null) {
                mealList = new ArrayList<Meal>();
            }
            mealList.add(userMeal);
            dayMealMap.put(userMeal.getDay(), mealList);
        }

        for (String day : dayMealMap.keySet()) {
            System.out.println("********* " + day + " *********");
            List<Meal> mealList = dayMealMap.get(day);
            for (Meal meal : mealList) {
                System.out.print(meal.getType() + " :");
                for (Dish dish : mealPlanService.getDishListByMeal(meal)) {
                    System.out.print(dish.getName() + " ");
                }
                System.out.println();
            }
        }
    }

    public Map<String, Map<String,Meal>> getWeeklyMealMap(User user) {
        UserDetailsService userDetailsService = new UserDetailsService();
        MealPlanService mealPlanService = new MealPlanService();
        List<Meal> userMealList = userDetailsService.getMealListByUser(user);
        Map<String, Map<String,Meal>> dayMealMap = new HashMap<String, Map<String,Meal>>();
        for (Meal userMeal : userMealList) {
            Map<String,Meal> mealMap = dayMealMap.get(userMeal.getDay());
            if (mealMap == null) {
                mealMap = new HashMap<String,Meal>();
            }
            mealMap.put(userMeal.getType(),userMeal);
            dayMealMap.put(userMeal.getDay(), mealMap);
        }
        return dayMealMap;
    }

    public List<Meal> getMealListByUser(User user) {
        UserDaoImpl userDao = new UserDaoImpl();
        List<Meal> userMealList = userDao.getMealListByUser(user);
        return userMealList;
    }

    public User validateUser(String email, String password) {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getUserByEmail(email);
        if (user != null && (user.getPassword().equals(password) && (user.getEmail().equals(email)))) {
            return user;
        } else {
            return null;
        }
    }

    public User addNewUser(User user) {
        UserDaoImpl userDao = new UserDaoImpl();
        return userDao.insertNewUser(user);
    }
}
