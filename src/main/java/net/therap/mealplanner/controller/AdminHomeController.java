package net.therap.mealplanner.controller;

import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
import net.therap.mealplanner.service.MealPlanService;
import net.therap.mealplanner.service.UserDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author bashir
 * @since 10/30/16
 */
public class AdminHomeController extends HttpServlet {
    static final Logger LOG = LogManager.getLogger(SimpleLogger.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            MealPlanService mealPlanService = new MealPlanService();
            UserDetailsService userDetailsService = new UserDetailsService();
            List<Dish> allDishList = mealPlanService.getDishList();
            Map<String, Map<String, Meal>> weeklyMealMap = userDetailsService.getWeeklyMealMap(user);
            req.getSession().setAttribute("allDishes",allDishList);
            req.getSession().setAttribute("weeklyMeal",weeklyMealMap);
            req.getRequestDispatcher("/jsp/admin/home.jsp").forward(req,resp);
        } catch (ServletException e) {
            LOG.error("AdminHomeController ::: doGet : ",e);
        } catch (IOException e) {
            LOG.error("AdminHomeController ::: doGet : ",e);
        }
    }
}
