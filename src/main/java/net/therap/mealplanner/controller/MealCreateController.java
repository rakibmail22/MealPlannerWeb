package net.therap.mealplanner.controller;

import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.service.MealPlanService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author bashir
 * @since 10/30/16
 */
public class MealCreateController extends HttpServlet {
    final static Logger LOG = LogManager.getLogger(SimpleLogger.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            MealPlanService mealPlanService = new MealPlanService();
            String[] checkBoxValues = req.getParameterValues("selectedDishes");
            List<Dish> selectedDishList = new ArrayList<>();
            List<Dish> allDishList = (List<Dish>) req.getSession().getAttribute("allDishes");
            for (String index: checkBoxValues) {
                selectedDishList.add(allDishList.get(Integer.parseInt(index)));
            }
            mealPlanService.createNewMeal(selectedDishList);
            req.getRequestDispatcher("/admin/home").forward(req,resp);
            LOG.debug("Checking checkbox params ::: "+ Arrays.deepToString(selectedDishList.toArray()));
        } catch (Exception e) {
            LOG.error("Exception MealCreateController ::: doGet : ",e);
        }
    }
}
