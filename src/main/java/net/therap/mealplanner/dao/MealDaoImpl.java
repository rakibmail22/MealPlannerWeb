package net.therap.mealplanner.dao;

import net.therap.mealplanner.domain.Dish;
import net.therap.mealplanner.domain.Meal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author bashir
 * @since 10/17/16
 */
@Repository
public class MealDaoImpl implements MealDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Dish> getDishList() {

        CriteriaQuery<Dish> dishCriteriaQuery = entityManager.getCriteriaBuilder().createQuery(Dish.class);
        dishCriteriaQuery.select(dishCriteriaQuery.from(Dish.class));

        return entityManager.createQuery(dishCriteriaQuery).getResultList();
    }

    public void insertNewDish(Dish dish) {

        entityManager.persist(dish);
    }

    public void deleteMeal(List<Meal> selectedMealList) {

        for (Meal meal : selectedMealList) {
            entityManager.remove(meal);
        }
    }

    public void saveMeal(Meal meal) {

        entityManager.persist(meal);
    }

    public List<Dish> getDishListById(List<Integer> idList) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Dish> cq = cb.createQuery(Dish.class);
        Root<Dish> dishRoot = cq.from(Dish.class);
        Expression<Integer> exp = dishRoot.get("id");
        Predicate predicate = exp.in(idList);
        cq.select(dishRoot).where(predicate);

        return entityManager.createQuery(cq).getResultList();

    }
}
