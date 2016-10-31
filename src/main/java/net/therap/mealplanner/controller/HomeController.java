package net.therap.mealplanner.controller;

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
import java.util.Map;

/**
 * @author bashir
 * @since 10/27/16
 */
public class HomeController extends HttpServlet {

    static final Logger LOG = LogManager.getLogger(SimpleLogger.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            MealPlanService mealPlanService = new MealPlanService();
            Map<String, Map<String, Meal>> weeklyMealMap = mealPlanService.getWeeklyMealMapForUser();
            req.getSession().setAttribute("weeklyMeal", weeklyMealMap);
            req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
        } catch (ServletException e) {
            LOG.error("HomeController ::: doGet : ", e);
        } catch (IOException e) {
            LOG.error("HomeController ::: doGet : ", e);

        }
    }
}
