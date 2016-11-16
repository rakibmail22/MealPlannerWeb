package net.therap.mealplanner.service;

import net.therap.mealplanner.dao.UserDao;
import net.therap.mealplanner.domain.Meal;
import net.therap.mealplanner.domain.User;
import net.therap.mealplanner.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author bashir
 * @since 10/16/16
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private Utils utils;

    @Transactional
    public List<Meal> getMealListByUser(User user) {

        return userDao.getMealListByUser(user);
    }

    @Transactional
    public User validateUser(String email, String password) {

        User user = userDao.getUserByEmail(email);

        return (user.getPassword().equals(utils.hashMd5(password))) ? user : null;

    }

    @Transactional
    public User addNewUser(User user) {

        return userDao.insertNewUser(user);
    }

    @Transactional
    public List<Meal> getAdminMealList() {

        return userDao.getMealListAdmin();
    }
}
