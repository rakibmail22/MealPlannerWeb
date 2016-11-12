package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

/**
 * @author bashir
 * @since 10/16/16
 */
@Repository
public class UserDaoImpl {


    @PersistenceContext
    EntityManager entityManager;


    public User getUserById(int id) {

        return entityManager.find(User.class, id);
    }

    public List<Meal> getMealListAdmin() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Metamodel m = entityManager.getMetamodel();
        EntityType<User> userEntityType = m.entity(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.where(cb.equal(userRoot.get("role"), "admin"));
        User admin = entityManager.createQuery(cq).getSingleResult();
        return admin.getMealList();
    }

    public User getUserByEmail(String email) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Metamodel m = entityManager.getMetamodel();
        EntityType<User> user = m.entity(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.where(cb.equal(userRoot.get("email"), email));
        return entityManager.createQuery(cq).getSingleResult();
    }

    public User insertNewUser(User user) {

        entityManager.persist(user);
        return user;

    }

    public User updateMealForUser(User user, Meal meal) {

        entityManager.refresh(user);
        entityManager.persist(user);
        return user;

    }


    public List<Meal> getMealListByUser(User user) {
        User user1 = entityManager.find(User.class, user.getId());
        return user1.getMealList();
    }
}
