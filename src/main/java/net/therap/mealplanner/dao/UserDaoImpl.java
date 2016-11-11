package net.therap.mealplanner.dao;

import net.therap.mealplanner.dbconfig.HibernateManager;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bashir
 * @since 10/16/16
 */
@Repository
public class UserDaoImpl {

    @Autowired
    HibernateManager hibernateManager;

    public User getUserById(int id) {

        Session session;

        try {
            session = hibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            User user = session.load(User.class, id);
            Hibernate.initialize(user);
            session.getTransaction().commit();
            session.close();

            return user;
        } catch (HibernateException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Meal> getMealListByUser(User user) {

        Session session;

        try {
            session = hibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            user = (User) session.merge(user);
            Hibernate.initialize(user.getMealList().size());
            session.evict(user);
            session.close();

            return user.getMealList();
        } catch (HibernateException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Meal> getMealListAdmin() {

        Session session;

        try {
            session = hibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            User admin = (User) session.createCriteria(User.class).add(Restrictions.eq("role", "admin")).uniqueResult();
            List<Meal> weeklyMealPlan = admin.getMealList();
            for (Meal meal : weeklyMealPlan) {
                Hibernate.initialize(meal.getMealDishes());
            }
            session.getTransaction().commit();
            session.close();

            return weeklyMealPlan;
        } catch (HibernateException e) {
            return null;
        }
    }

    public User getUserByEmail(String email) {

        Session session;

        try {
            session = hibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            User user = (User) session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
            Hibernate.initialize(user);
            session.getTransaction().commit();
            session.close();

            return user;
        } catch (HibernateException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public User insertNewUser(User user) {

        Session session;

        try {
            session = hibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            int userId = (int) session.save(user);
            user.setId(userId);
            session.getTransaction().commit();
            session.close();

            return user;
        } catch (HibernateException e) {
            System.out.println(e);
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
