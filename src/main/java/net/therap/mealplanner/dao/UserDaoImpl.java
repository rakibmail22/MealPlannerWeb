package net.therap.mealplanner.dao;

import net.therap.mealplanner.dbconfig.HibernateManager;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
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

    public User getUserById(int id) {
        try {
            Session session = HibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            User user = session.load(User.class, id);
            Hibernate.initialize(user);
            session.getTransaction().commit();
            session.close();
            return user;
        } catch (HibernateException e) {
            return null;
        }
    }

    public List<Meal> getMealListByUser(User user) {
        try {
            Session session = HibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            user = (User) session.merge(user);
            Hibernate.initialize(user.getMealList().size());
            session.evict(user);
            session.close();
            return user.getMealList();
        } catch (HibernateException e) {
            return null;
        }
    }

    public User getUserByEmail(String email) {
        try {
            Session session = HibernateManager.getSessionFactory().openSession();
            session.beginTransaction();
            User user = (User) session.createCriteria(User.class).add(Restrictions.eq("email",email)).uniqueResult();
            Hibernate.initialize(user);
            session.getTransaction().commit();
            session.close();
            return user;
        } catch (HibernateException e) {
            return null;
        }
    }
}
