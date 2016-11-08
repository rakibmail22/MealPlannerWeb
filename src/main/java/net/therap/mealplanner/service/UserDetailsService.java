package net.therap.mealplanner.service;

import net.therap.mealplanner.dao.UserDaoImpl;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bashir
 * @since 10/16/16
 */
@Service
public class UserDetailsService {

    @Autowired
    UserDaoImpl userDao;

    public User getUserByUserId(int userId) {
        User user = userDao.getUserById(userId);
        return user;
    }

    public Map<String, Map<String, Meal>> getWeeklyMealMap(User user) {
        List<Meal> userMealList = getMealListByUser(user);
        Map<String, Map<String, Meal>> dayMealMap = new HashMap<String, Map<String, Meal>>();
        for (Meal userMeal : userMealList) {
            Map<String, Meal> mealMap = dayMealMap.get(userMeal.getDay());
            if (mealMap == null) {
                mealMap = new HashMap<String, Meal>();
            }
            mealMap.put(userMeal.getType(), userMeal);
            dayMealMap.put(userMeal.getDay(), mealMap);
        }
        return dayMealMap;
    }


    public List<Meal> getMealListByUser(User user) {
        List<Meal> userMealList = userDao.getMealListByUser(user);
        return userMealList;
    }

    public User validateUser(String email, String password) {
        User user = userDao.getUserByEmail(email);
        if (user != null && (user.getPassword().equals(password) && (user.getEmail().equals(email)))) {
            return user;
        } else {
            return null;
        }
    }

    public User addNewUser(User user) {
        return userDao.insertNewUser(user);
    }

    public List<Meal> getAdminMealList() {
        return userDao.getMealListAdmin();
    }
}
