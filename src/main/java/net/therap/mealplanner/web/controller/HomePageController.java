package net.therap.mealplanner.web.controller;

import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.service.MealPlanService;
import net.therap.mealplanner.service.UserDetailsService;
import net.therap.mealplanner.web.command.DishIdInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author bashir
 * @since 11/7/16
 */
@Controller
public class HomePageController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MealPlanService mealPlanService;

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String adminHome(HttpSession session, Model model) {

        List<Dish> allDishList = mealPlanService.getDishList();
        Map<String, Map<String, Meal>> weeklyMealMap = mealPlanService.getWeeklyMealMapForUser();

        model.addAttribute("selectedDishList", new DishIdInfo());
        model.addAttribute("weeklyMeal", weeklyMealMap);
        model.addAttribute("allDishes", allDishList);

        return "admin/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String userHome(HttpSession session) {

        Map<String, Map<String, Meal>> weeklyMealMap = mealPlanService.getWeeklyMealMapForUser();
        session.setAttribute("weeklyMeal", weeklyMealMap);

        return "home";
    }
}
