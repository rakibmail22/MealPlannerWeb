package net.therap.mealplanner.controller;

import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
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
 * @since 10/31/16
 */
public class WeeklyMealUpdateController extends HttpServlet {
    final static Logger LOG = LogManager.getLogger(SimpleLogger.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            MealPlanService mealPlanService = new MealPlanService();
            String[] checkBoxValues = req.getParameterValues("selectedMeals");
            if (checkBoxValues.length>1) {
                LOG.error("Multiple Checkbox Selected");
            }
            if (checkBoxValues.length==0) {
                LOG.info("No Meal Selected");
            }
            List<Meal> selectedMealList = new ArrayList<>();
            List<Meal> allMealList= (List<Meal>) req.getSession().getAttribute("allMeals");
            Meal selectedMeal = allMealList.get(Integer.parseInt(checkBoxValues[0]));
            String day = req.getParameter("daySelect");
            LOG.debug("**************************** "+day);
            User user = (User) req.getSession().getAttribute("user");
            selectedMeal.setDay(day);
            Meal existingMeal = mealPlanService.getMealForUserForDay(user, day, selectedMeal.getType());
            mealPlanService.updateMealPlanForUser(selectedMeal, existingMeal, user);
            //Meal existingMeal = mealPlanService.getMealForUserForDay();
            resp.sendRedirect(req.getContextPath()+"/admin/home");
            LOG.debug("Checking checkbox params ::: "+ Arrays.deepToString(selectedMealList.toArray()));
        } catch (Exception e) {
            LOG.error("Exception MealCreateController ::: doGet : ",e);
        }
    }
}
