package net.therap.mealplanner.service;

import net.therap.mealplanner.dao.MealDao;
import net.therap.mealplanner.dao.MealDaoImpl;
import net.therap.mealplanner.dao.UserDao;
import net.therap.mealplanner.dao.UserDaoImpl;
import net.therap.mealplanner.domain.Dish;
import net.therap.mealplanner.domain.Meal;
import net.therap.mealplanner.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bashir
 * @since 10/17/16
 */
@Service
public class MealPlanService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MealDao mealDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    public List<Dish> getDishList() {
        List<Dish> dishList = mealDao.getDishList();

        return dishList;
    }

    @Transactional
    public Meal getBreakfastForUserForDay(User user, String day) {
        for (Meal meal : userDetailsService.getMealListByUser(user)) {
            if (meal.getDay().equals(day) && meal.getType().equals("B")) {
                return meal;
            }
        }

        return null;
    }

    @Transactional
    public Meal getLunchForUserForDay(User user, String day) {
        for (Meal meal : userDetailsService.getMealListByUser(user)) {
            if (meal.getDay().equals(day) && meal.getType().equals("L")) {
                return meal;
            }
        }

        return null;
    }

    @Transactional
    public Meal getMealForUserForDay(User user, String day, String mealType) {
        if (mealType.equals("B")) {
            return getBreakfastForUserForDay(user, day);
        } else if (mealType.equals("L")) {
            return getLunchForUserForDay(user, day);
        }

        return null;
    }

    @Transactional
    public void updateMealPlanForUser(List<Integer> dishIdList, User user, Meal newMeal) {

        List<Dish> dishList = mealDao.getDishListById(dishIdList);
        newMeal.getMealDishes().addAll(dishList);
        user = userDao.getUserById(user.getId());

        Meal existingMeal=null;
        for (Meal meal: user.getMealList()) {
            if (meal.getType().equals(newMeal.getType()) && meal.getDay().equals(newMeal.getDay())) {
                existingMeal = meal;
            }
        }

        if (null!=existingMeal) {
            user.getMealList().remove(existingMeal);
        }

        newMeal.getUserList().add(user);
        user.getMealList().add(newMeal);
        userDao.insertNewUser(user);
    }

    @Transactional
    public void insertNewDish(Dish dish) {
        mealDao.insertNewDish(dish);
    }

    @Transactional
    public List<Dish> getDishListByMeal(Meal meal) {
        return meal.getMealDishes();
    }

    @Transactional
    public void deleteMeal(List<Meal> selectedMealList) {
        mealDao.deleteMeal(selectedMealList);
    }

    @Transactional
    public String getMealDishesAsString(Meal meal) {
        String dishes = "";
        for (Dish dish : meal.getMealDishes()) {
            dishes += dish.getName() + ",";
        }

        return dishes.substring(0, dishes.length() - 1);
    }

    @Transactional
    public Map<String, Map<String, Meal>> getWeeklyMealMapForUser() {
        List<Meal> userMealList = userDetailsService.getAdminMealList();
        Map<String, Map<String, Meal>> dayMealMap = new HashMap<>();
        for (Meal userMeal : userMealList) {
            Map<String, Meal> mealMap = dayMealMap.get(userMeal.getDay().name());
            if (mealMap == null) {
                mealMap = new HashMap<>();
            }
            mealMap.put(userMeal.getType(), userMeal);
            dayMealMap.put(userMeal.getDay().name(), mealMap);
        }

        return dayMealMap;
    }


}
