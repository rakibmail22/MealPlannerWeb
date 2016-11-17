package net.therap.mealplanner.web.controller;

import net.therap.mealplanner.domain.Dish;
import net.therap.mealplanner.domain.Meal;
import net.therap.mealplanner.service.MealPlanService;
import net.therap.mealplanner.service.MealPlanServiceImpl;
import net.therap.mealplanner.utils.URL;
import net.therap.mealplanner.web.command.DishIdInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @author bashir
 * @since 11/7/16
 */
@Controller
public class HomePageController {

    @Autowired
    private MealPlanService mealPlanService;

    @RequestMapping(value = URL.ROOT+URL.ADMIN_HOME, method = RequestMethod.GET)
    public String adminHome(Model model) {

        List<Dish> allDishList = mealPlanService.getDishList();
        Map<String, Map<String, Meal>> weeklyMealMap = mealPlanService.getWeeklyMealMapForUser();

        model.addAttribute("selectedDishList", new DishIdInfo());
        model.addAttribute("weeklyMeal", weeklyMealMap);
        model.addAttribute("allDishes", allDishList);

        return URL.ADMIN_HOME;
    }

    @RequestMapping(value = URL.ROOT+URL.USER_HOME, method = RequestMethod.GET)
    public String userHome(Model model) {

        Map<String, Map<String, Meal>> weeklyMealMap = mealPlanService.getWeeklyMealMapForUser();
        model.addAttribute("weeklyMeal", weeklyMealMap);

        return URL.USER_HOME;
    }
}
