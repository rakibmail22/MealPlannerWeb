package net.therap.mealplanner.web;

import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
import net.therap.mealplanner.service.MealPlanService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author bashir
 * @since 11/7/16
 */
@Controller
public class MealController {

    @Autowired
    MealPlanService mealPlanService;
    final static Logger LOG = LogManager.getLogger(SimpleLogger.class);

    @RequestMapping(value = "/admin/createNewBreakfast", method = RequestMethod.GET)
    public ModelAndView createBreakFast(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            String[] checkBoxValues = req.getParameterValues("selectedDishes");
            List<Dish> selectedDishList = new ArrayList<>();
            List<Dish> allDishList = (List<Dish>) req.getSession().getAttribute("allDishes");
            for (String index : checkBoxValues) {
                selectedDishList.add(allDishList.get(Integer.parseInt(index)));
            }
            Meal meal = new Meal();
            meal.getMealDishes().addAll(selectedDishList);
            meal.setDay(req.getParameter("daySelect"));
            meal.setType("B");
            Meal existingMeal = mealPlanService.getMealForUserForDay(user, req.getParameter("daySelect"), "B");
            mealPlanService.updateMealPlanForUser(meal, existingMeal, user);
            LOG.debug("Checking checkbox params ::: " + Arrays.deepToString(selectedDishList.toArray()));
            return new ModelAndView("redirect:/admin/home");
        } catch (Exception e) {
            LOG.error("Exception BreakfastCreateController ::: doGet : ", e);
            return null;
        }
    }

    @RequestMapping(value = "/admin/createNewLunch", method = RequestMethod.GET)
    public ModelAndView createLunch(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            String[] checkBoxValues = req.getParameterValues("selectedDishes");
            List<Dish> selectedDishList = new ArrayList<>();
            List<Dish> allDishList = (List<Dish>) req.getSession().getAttribute("allDishes");
            for (String index : checkBoxValues) {
                selectedDishList.add(allDishList.get(Integer.parseInt(index)));
            }
            Meal meal = new Meal();
            meal.getMealDishes().addAll(selectedDishList);
            meal.setDay(req.getParameter("daySelect"));
            meal.setType("L");
            Meal existingMeal = mealPlanService.getMealForUserForDay(user, req.getParameter("daySelect"), "L");
            mealPlanService.updateMealPlanForUser(meal, existingMeal, user);
            LOG.debug("Checking checkbox params ::: " + Arrays.deepToString(selectedDishList.toArray()));
            return new ModelAndView("forward:/admin/home");
        } catch (Exception e) {
            LOG.error("Exception LunchCreateController ::: doGet : ", e);
            return null;
        }
    }

    @RequestMapping(value = "/admin/addNewDish", method = RequestMethod.GET)
    public ModelAndView addDish(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String dishName = req.getParameter("dishName");
            Dish dish = new Dish();
            dish.setName(dishName);
            mealPlanService.insertNewDish(dish);
            LOG.debug("Checking checkbox params ::: " + dishName);
            return new ModelAndView("redirect:/admin/home");
        } catch (Exception e) {
            LOG.error("Exception DishAddController ::: doGet : ", e);
            return null;
        }
    }

    @RequestMapping(value = "/admin/deleteMeal", method = RequestMethod.GET)
    public ModelAndView deleteMeal(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String[] checkBoxValues = req.getParameterValues("selectedMeals");
            List<Meal> selectedMealList = new ArrayList<>();
            List<Meal> allMealList = (List<Meal>) req.getSession().getAttribute("allMeals");
            for (String index : checkBoxValues) {
                selectedMealList.add(allMealList.get(Integer.parseInt(index)));
            }
            mealPlanService.deleteMeal(selectedMealList);
            LOG.debug("Checking checkbox params ::: " + Arrays.deepToString(selectedMealList.toArray()));
            return new ModelAndView("redirect:/admin/home");
        } catch (Exception e) {
            LOG.error("Exception MealCreateController ::: doGet : ", e);
            return null;
        }
    }

    @RequestMapping(value = "/admin/updateWeeklyPlan", method = RequestMethod.GET)
    public ModelAndView updateWeeklyMeal(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String[] checkBoxValues = req.getParameterValues("selectedMeals");
            if (checkBoxValues.length > 1) {
                LOG.error("Multiple Checkbox Selected");
            }
            if (checkBoxValues.length == 0) {
                LOG.info("No Meal Selected");
            }
            List<Meal> selectedMealList = new ArrayList<>();
            List<Meal> allMealList = (List<Meal>) req.getSession().getAttribute("allMeals");
            Meal selectedMeal = allMealList.get(Integer.parseInt(checkBoxValues[0]));
            String day = req.getParameter("daySelect");
            LOG.debug("**************************** " + day);
            User user = (User) req.getSession().getAttribute("user");
            selectedMeal.setDay(day);
            Meal existingMeal = mealPlanService.getMealForUserForDay(user, day, selectedMeal.getType());
            mealPlanService.updateMealPlanForUser(selectedMeal, existingMeal, user);
            LOG.debug("Checking checkbox params ::: " + Arrays.deepToString(selectedMealList.toArray()));
            return new ModelAndView("redirect:/admin/home");
        } catch (Exception e) {
            LOG.error("Exception MealCreateController ::: doGet : ", e);
            return null;
        }
    }

}
