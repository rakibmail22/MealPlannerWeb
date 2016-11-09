package net.therap.mealplanner.web;

import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
import net.therap.mealplanner.service.MealPlanService;
import net.therap.mealplanner.service.UserDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author bashir
 * @since 11/7/16
 */
@Controller
public class HomePageController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    MealPlanService mealPlanService;

    static final Logger log = LogManager.getLogger(SimpleLogger.class);

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String adminHome(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            List<Dish> allDishList = mealPlanService.getDishList();
            Map<String, Map<String, Meal>> weeklyMealMap = userDetailsService.getWeeklyMealMap(user);
            req.getSession().setAttribute("allDishes", allDishList);
            req.getSession().setAttribute("weeklyMeal", weeklyMealMap);
            return "admin/home";
        } catch (Exception e) {
            log.error("AdminHomeController ::: doGet : ", e);
            return null;
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String userHome(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Map<String, Map<String, Meal>> weeklyMealMap = mealPlanService.getWeeklyMealMapForUser();
            req.getSession().setAttribute("weeklyMeal", weeklyMealMap);
            return "home";
        } catch (Exception e) {
            log.error("HomeController ::: doGet : ", e);
            return null;
        }
    }
}
