package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author bashir
 * @since 10/17/16
 */
@Repository
public class MealDaoImpl {

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
}
