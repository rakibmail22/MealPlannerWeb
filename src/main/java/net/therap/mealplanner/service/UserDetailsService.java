package net.therap.mealplanner.service;

import net.therap.mealplanner.dao.UserDaoImpl;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
import net.therap.mealplanner.utils.Utils;
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

    @Autowired
    Utils utils;

    public List<Meal> getMealListByUser(User user) {

        return userDao.getMealListByUser(user);
    }

    public User validateUser(String email, String password) {

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {

            return null;
        } else {
            User user = userDao.getUserByEmail(email);

            return (user.getPassword().equals(utils.hashMd5(password))) ? user : null;
        }
    }

    public User addNewUser(User user) {

        return userDao.insertNewUser(user);
    }

    public List<Meal> getAdminMealList() {

        return userDao.getMealListAdmin();
    }
}
