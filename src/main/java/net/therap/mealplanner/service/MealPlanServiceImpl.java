package net.therap.mealplanner.service;

import net.therap.mealplanner.dao.MealDao;
import net.therap.mealplanner.dao.UserDao;
import net.therap.mealplanner.domain.Dish;
import net.therap.mealplanner.domain.Meal;
import net.therap.mealplanner.domain.User;
import net.therap.mealplanner.web.security.AuthUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
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
public class MealPlanServiceImpl implements MealPlanService {

    final static Logger log = LogManager.getLogger(SimpleLogger.class);

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
    public void updateMealPlanForUser(List<Integer> dishIdList, AuthUser authUser, Meal newMeal) {

        List<Dish> dishList = mealDao.getDishListById(dishIdList);
        newMeal.getMealDishes().addAll(dishList);
        User user = userDao.getUserById(authUser.getUserId());

        Meal existingMeal = null;
        for (Meal meal : user.getMealList()) {
            if (meal.getType().equals(newMeal.getType()) && meal.getDay().equals(newMeal.getDay())) {
                existingMeal = meal;
            }
        }

        if (null != existingMeal) {
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
    public Map<String, Map<String, Meal>> getWeeklyMealMapForUser() {
        List<Meal> userMealList = userDetailsService.getAdminMealList();
        Map<String, Map<String, Meal>> dayMealMap = new HashMap<>();
        for (Meal userMeal : userMealList) {
            Map<String, Meal> mealMap = dayMealMap.get(userMeal.getDay().name());
            if (mealMap == null) {
                mealMap = new HashMap<>();
            }

            log.debug("MEALTYPE:::: "+userMeal.getType().getMealType());

            mealMap.put(userMeal.getType().getMealType(), userMeal);
            dayMealMap.put(userMeal.getDay().name(), mealMap);
        }

        return dayMealMap;
    }
}
