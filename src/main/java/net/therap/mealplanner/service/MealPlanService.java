package net.therap.mealplanner.service;

import net.therap.mealplanner.dao.MealDaoImpl;
import net.therap.mealplanner.dao.UserDaoImpl;
import net.therap.mealplanner.dbconfig.HibernateManager;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bashir
 * @since 10/17/16
 */
@Service
public class MealPlanService {
    public List<Dish> getDishList() {
        MealDaoImpl mealDao = new MealDaoImpl();
        List<Dish> dishList = mealDao.getDishList();
        return dishList;
    }


    public List<Meal> getAllMealOfType(String type, String day) {
        MealDaoImpl mealDao = new MealDaoImpl();
        List<Meal> mealList = mealDao.getAllMealListOfTypeAndDay(type, day);
        return mealList;
    }

    public Meal getBreakfastForUserForDay(User user, String day) {
        UserDetailsService userDetailsService = new UserDetailsService();
        for (Meal meal : userDetailsService.getMealListByUser(user)) {
            if (meal.getDay().equals(day) && meal.getType().equals("B")) {
                return meal;
            }
        }
        return null;
    }

    public Meal getLunchForUserForDay(User user, String day) {
        UserDetailsService userDetailsService = new UserDetailsService();
        for (Meal meal : userDetailsService.getMealListByUser(user)) {
            if (meal.getDay().equals(day) && meal.getType().equals("L")) {
                return meal;
            }
        }
        return null;
    }

    public Meal getMealForUserForDay(User user, String day, String mealType) {
        if (mealType.equals("B")) {
            return getBreakfastForUserForDay(user, day);
        } else if (mealType.equals("L")) {
            return getLunchForUserForDay(user, day);
        }
        return null;
    }

    public int updateMealPlanForUser(Meal newMeal, Meal existingMeal, User user) {
        MealDaoImpl mealDao = new MealDaoImpl();
        int result = 0;
        result = mealDao.updateMealForUser(existingMeal, newMeal, user);
        return result;
    }

    public void insertNewMealPlanForUser(List<Dish> dishIdList, String day, String mealType, User user) {
        MealDaoImpl mealDao = new MealDaoImpl();
        Meal meal = new Meal();
        meal.setDay(day);
        meal.setType(mealType);
        mealDao.insertMealForUser(meal, dishIdList, user);
    }

    public void insertNewDish(Dish dish) {
        MealDaoImpl mealDao = new MealDaoImpl();
        mealDao.insertNewDish(dish);
    }

    public int deleteMealForUser(Meal meal, User user) {
        MealDaoImpl mealDao = new MealDaoImpl();
        return mealDao.deleteMealForUser(meal, user);
        // user.setMealList(null);
    }

    public List<Dish> getDishListByMeal(Meal meal) {
        Session session = HibernateManager.getSessionFactory().openSession();
        meal = (Meal) session.merge(meal);
        List<Dish> dishList = meal.getMealDishes();
        Hibernate.initialize(dishList.size());
        session.evict(meal);
        session.close();
        return dishList;
    }

    public List<Meal> getAllMeal(){
        MealDaoImpl mealDao = new MealDaoImpl();
        return mealDao.getAllMeal();
    }

    public void createNewMeal(List<Dish> dishList, String type){
        MealDaoImpl mealDao = new MealDaoImpl();
        mealDao.createNewMeal(dishList, type);
    }

    public void deleteMeal(List<Meal> selectedMealList) {
        MealDaoImpl mealDao = new MealDaoImpl();
        mealDao.deleteMeal(selectedMealList);
    }

    public List<Meal> getAdminMealList(){
        UserDaoImpl userDao= new UserDaoImpl();
        return userDao.getMealListAdmin();
    }

    public Map<String, Map<String,Meal>> getWeeklyMealMapForUser() {
        MealPlanService mealPlanService = new MealPlanService();
        List<Meal> userMealList = mealPlanService.getAdminMealList();
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


}
