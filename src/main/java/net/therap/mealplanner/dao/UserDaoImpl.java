package net.therap.mealplanner.dao;

import net.therap.mealplanner.dbconfig.HibernateManager;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author bashir
 * @since 10/16/16
 */
public class UserDaoImpl {
    static final Logger LOG = LogManager.getLogger(SimpleLogger.class);

    public User getUserById(int id) {
        Session session = null;
        try {
            session = HibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            User user = session.load(User.class, id);
            Hibernate.initialize(user);
            session.getTransaction().commit();
            session.close();
            return user;
        } catch (HibernateException e) {
            LOG.error("MealDaoImpl:: getUserById(int id): ", e);
            session.getTransaction().rollback();
            return null;
        } catch (Exception e) {
            LOG.error("MealDaoImpl:: getUserById(int id): ", e);
            return null;
        }
    }

    public List<Meal> getMealListByUser(User user) {
        Session session = null;
        try {
            session = HibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            user = (User) session.merge(user);
            Hibernate.initialize(user.getMealList().size());
            session.evict(user);
            session.close();
            return user.getMealList();
        } catch (HibernateException e) {
            LOG.error("MealDaoImpl:: getMealListByUser(User user): ", e);
            session.getTransaction().rollback();
            return null;
        } catch (Exception e) {
            LOG.error("MealDaoImpl:: getUserById(int id): ", e);
            return null;
        }
    }

    public User getUserByEmail(String email) {
        Session session = null;
        try {
            session = HibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            User user = (User) session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
            Hibernate.initialize(user);
            session.getTransaction().commit();
            session.close();
            return user;
        } catch (HibernateException e) {
            LOG.error("MealDaoImpl:: getUserByEmail(String email): ", e);
            session.getTransaction().rollback();
            return null;
        } catch (Exception e) {
            LOG.error("MealDaoImpl:: getUserById(int id): ", e);
            return null;
        }
    }

    public User insertNewUser(User user) {
        Session session = null;
        try {
            LOG.debug("adding new user " + user);
            session = HibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            int userId = (int) session.save(user);
            user.setId(userId);
            session.getTransaction().commit();
            session.close();
            return user;
        } catch (HibernateException e) {
            LOG.error("MealDaoImpl:: getUserByEmail(String email): ", e);
            session.getTransaction().rollback();
            return null;
        } catch (Exception e) {
            LOG.error("MealDaoImpl:: getUserById(int id): ", e);
            return null;
        }
    }
}
