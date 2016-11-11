package net.therap.mealplanner.dao;

import net.therap.mealplanner.dbconfig.HibernateManager;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bashir
 * @since 10/17/16
 */
@Repository
public class MealDaoImpl {

    @Autowired
    HibernateManager hibernateManager;

    public List<Dish> getDishList() {

        Session session;

        try {
            session = hibernateManager.getSessionFactory().openSession();
            List<Dish> dishList = session.createCriteria(Dish.class).list();
            session.close();

            return dishList;
        } catch (HibernateException e) {
            return null;
        }
    }

    public int updateMealForUser(Meal existingMeal, Meal newMeal, User user) {

        Session session;

        try {
            session = hibernateManager.getSessionFactory().openSession();
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
            return -1;
        } catch (HibernateException e) {
            return -1;
        }
    }

    public int insertMealForUser(Meal newMeal, List<Dish> dishList, User user) {

        Session session;

        try {
            session = hibernateManager.getSessionFactory().openSession();
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
            return -1;
        }
    }

    public int insertNewDish(Dish dish) {

        Session session;

        try {
            session = hibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(dish);
            session.getTransaction().commit();
            session.close();

            return 1;
        } catch (ConstraintViolationException e) {
            return -1;
        } catch (HibernateException e) {
            return -1;
        }
    }

    public int deleteMeal(List<Meal> selectedMealList) {

        Session session;

        try {
            session = hibernateManager.getSessionFactory().openSession();
            for (Meal meal : selectedMealList) {
                session.beginTransaction();
                meal = (Meal) session.merge(meal);
                session.delete(meal);
            }
            session.getTransaction().commit();
            session.close();

            return 1;
        } catch (HibernateException e) {
            return -1;
        }
    }

    public List<Dish> getDishListByMeal(Meal meal) {

        Session session;

        try {
            session = hibernateManager.getSessionFactory().openSession();
            meal = (Meal) session.merge(meal);
            List<Dish> dishList = meal.getMealDishes();
            Hibernate.initialize(dishList.size());
            session.evict(meal);
            session.close();

            return dishList;
        } catch (HibernateException e) {
            return null;
        }
    }
}
