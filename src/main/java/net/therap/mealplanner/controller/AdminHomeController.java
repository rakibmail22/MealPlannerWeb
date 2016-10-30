package net.therap.mealplanner.controller;

import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.service.MealPlanService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author bashir
 * @since 10/30/16
 */
public class AdminHomeController extends HttpServlet {
    static final Logger LOG = LogManager.getLogger(SimpleLogger.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            MealPlanService mealPlanService = new MealPlanService();
            List<Dish> allDishList = mealPlanService.getDishList();
            List<Meal> allMealList = mealPlanService.getAllMeal();
            req.getSession().setAttribute("allDishes",allDishList);
            req.getSession().setAttribute("allMeals",allMealList);
            req.getRequestDispatcher("/jsp/admin/home.jsp").forward(req,resp);
        } catch (ServletException e) {
            LOG.error("AdminHomeController ::: doGet : ",e);
        } catch (IOException e) {
            LOG.error("AdminHomeController ::: doGet : ",e);
        }
    }
}
