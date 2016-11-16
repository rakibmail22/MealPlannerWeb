package net.therap.mealplanner.dao;

import net.therap.mealplanner.domain.Meal;
import net.therap.mealplanner.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


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

    public User updateMealForUser(User user, Meal newMeal, Meal existingMeal) {

        user = entityManager.find(User.class, user.getId());

        if (existingMeal != null) {
            user.getMealList().remove(existingMeal);
        }

        user.getMealList().add(newMeal);
        entityManager.persist(user);

        return user;
    }

    public List<Meal> getMealListByUser(User user) {

        user = entityManager.find(User.class, user.getId());
        return user.getMealList();
    }
}
