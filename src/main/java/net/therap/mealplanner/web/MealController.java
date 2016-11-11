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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
@RequestMapping(value = "/admin")
public class MealController {

    @Autowired
    MealPlanService mealPlanService;

    @RequestMapping(value = "/createNewBreakfast", method = RequestMethod.GET)
    public String createBreakFast(HttpServletRequest req) {

        User user = (User) req.getSession().getAttribute("user");

        String[] selectedDishIds = req.getParameterValues("selectedDishes");
        List<Dish> selectedDishList = new ArrayList<>();
        List<Dish> allDishList = (List<Dish>) req.getSession().getAttribute("allDishes");

        for (String index : selectedDishIds) {
            selectedDishList.add(allDishList.get(Integer.parseInt(index)));
        }

        Meal meal = new Meal();
        meal.getMealDishes().addAll(selectedDishList);
        meal.setDay(req.getParameter("daySelect"));
        meal.setType("B");

        Meal existingMeal = mealPlanService.getMealForUserForDay(user, req.getParameter("daySelect"), "B");
        mealPlanService.updateMealPlanForUser(meal, existingMeal, user);

        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/createNewLunch", method = RequestMethod.GET)
    public String createLunch(HttpServletRequest req) {

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

        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/addNewDish", method = RequestMethod.GET)
    public String addDish(HttpServletRequest req) {

        String dishName = req.getParameter("dishName");
        Dish dish = new Dish();
        dish.setName(dishName);

        mealPlanService.insertNewDish(dish);

        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/deleteMeal", method = RequestMethod.GET)
    public String deleteMeal(HttpServletRequest req) {

        String[] checkBoxValues = req.getParameterValues("selectedMeals");
        List<Meal> selectedMealList = new ArrayList<>();
        List<Meal> allMealList = (List<Meal>) req.getSession().getAttribute("allMeals");

        for (String index : checkBoxValues) {
            selectedMealList.add(allMealList.get(Integer.parseInt(index)));
        }

        mealPlanService.deleteMeal(selectedMealList);

        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/updateWeeklyPlan", method = RequestMethod.GET)
    public String updateWeeklyMeal(HttpServletRequest req) {

        User user = (User) req.getSession().getAttribute("user");

        String[] checkBoxValues = req.getParameterValues("selectedMeals");
        List<Meal> selectedMealList = new ArrayList<>();
        List<Meal> allMealList = (List<Meal>) req.getSession().getAttribute("allMeals");

        Meal selectedMeal = allMealList.get(Integer.parseInt(checkBoxValues[0]));
        String day = req.getParameter("daySelect");
        selectedMeal.setDay(day);

        Meal existingMeal = mealPlanService.getMealForUserForDay(user, day, selectedMeal.getType());
        mealPlanService.updateMealPlanForUser(selectedMeal, existingMeal, user);

        return "redirect:/admin/home";
    }
}
