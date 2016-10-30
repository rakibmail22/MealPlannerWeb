package net.therap.mealplanner.controller;

import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.service.MealPlanService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author bashir
 * @since 10/30/16
 */
public class MealDeleteController extends HttpServlet {
    final static Logger LOG = LogManager.getLogger(SimpleLogger.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            MealPlanService mealPlanService = new MealPlanService();
            String[] checkBoxValues = req.getParameterValues("selectedMeals");
            List<Meal> selectedMealList = new ArrayList<>();
            List<Meal> allMealList= (List<Meal>) req.getSession().getAttribute("allMeals");
            for (String index: checkBoxValues) {
                selectedMealList.add(allMealList.get(Integer.parseInt(index)));
            }
            mealPlanService.deleteMeal(selectedMealList);
            resp.sendRedirect(req.getContextPath()+"/admin/home");
            LOG.debug("Checking checkbox params ::: "+ Arrays.deepToString(selectedMealList.toArray()));
        } catch (Exception e) {
            LOG.error("Exception MealCreateController ::: doGet : ",e);
        }
    }
}
