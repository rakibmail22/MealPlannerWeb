package net.therap.mealplanner.dao;

import net.therap.mealplanner.dbconfig.HibernateManager;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bashir
 * @since 10/17/16
 */
public class MealDaoImpl {
    static final Logger LOG = LogManager.getLogger(SimpleLogger.class);
    public List<Dish> getDishList() {
        Session session = null;
        try {
            session = HibernateManager.getSessionFactory().openSession();
            List<Dish> dishList = session.createCriteria(Dish.class).list();
            session.close();
            return dishList;
        } catch (HibernateException e) {
            LOG.error("MealDaoImpl:: getDishList(): ",e);
            return null;
        }
    }

    public List<Meal> getAllMealListOfTypeAndDay(String type, String day) {
        Session session = null;
        try {
            session = HibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            List<Meal> mealList = session.createCriteria(Meal.class).list();
            session.getTransaction().commit();

            List<Meal> mealListFiltered = new ArrayList<Meal>();
            for (Meal meal : mealList) {
                if (meal.getType().equals(type) && meal.getDay().equals(day)) {
                    mealListFiltered.add(meal);
                }
            }
            session.close();
            return mealListFiltered;
        } catch (HibernateException e) {
            LOG.error("MealDaoImpl:: getAllMealListOfTypeAndDay(): ",e);
            return null;
        }
    }

    public int updateMealForUser(Meal existingMeal, Meal newMeal, User user) {
        Session session = null;
        try {
            session = HibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            user = (User) session.merge(user);
            newMeal = (Meal) session.merge(newMeal);
            List<Meal> userMealList = user.getMealList();
            if (existingMeal != null) {
                existingMeal = (Meal) session.merge(existingMeal);
                userMealList.remove(existingMeal);
                existingMeal.getUserList().remove(user);
            }
            userMealList.add(newMeal);
            newMeal.getUserList().add(user);
            session.getTransaction().commit();
            session.flush();
            session.close();
            return 1;
        } catch (ConstraintViolationException e) {
            LOG.error("MealDaoImpl:: updateMealForUser(): ",e);
            return -1;
        } catch (HibernateException e) {
            LOG.error("MealDaoImpl:: updateMealForUser(): ",e);
            return -1;
        }
    }

    public int insertMealForUser(Meal newMeal, List<Dish> dishList, User user) {
        Session session = null;
        try {
            session = HibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            user = (User) session.merge(user);
            session.save(newMeal);
            for (Dish dish : dishList) {
                dish = (Dish) session.merge(dish);
                newMeal.getMealDishes().add(dish);
            }
            newMeal.getUserList().add(user);
            session.getTransaction().commit();
            session.close();
            return 1;
        } catch (HibernateException e) {
            LOG.error("MealDaoImpl:: insertMealForUser(): ",e);
            return -1;
        }
    }

    public int insertNewDish(Dish dish) {
        Session session = null;
        try {
            session = HibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(dish);
            session.getTransaction().commit();
            session.close();
            return 1;
        } catch (ConstraintViolationException e) {
            LOG.error("MealDaoImpl:: insertNewDish(): ",e);
            return -1;
        } catch (HibernateException e) {
            LOG.error("MealDaoImpl:: insertNewDish(): ",e);
            return -1;
        }
    }

    public int deleteMealForUser(Meal meal, User user) {
        Session session = null;
        try {
            session = HibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            meal = (Meal) session.merge(meal);
            user = (User) session.merge(user);
            List<Meal> userMealList = user.getMealList();
            userMealList.remove(meal);
            meal.getUserList().remove(user);
            session.getTransaction().commit();
            session.close();
            return 1;
        } catch (HibernateException e) {
            LOG.error("MealDaoImpl:: deleteMealForUser(): ",e);
            return -1;
        }
    }
}
