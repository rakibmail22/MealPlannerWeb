package net.therap.mealplanner.web.controller;

import net.therap.mealplanner.domain.User;
import net.therap.mealplanner.service.MealPlanService;
import net.therap.mealplanner.service.MealPlanServiceImpl;
import net.therap.mealplanner.utils.URL;
import net.therap.mealplanner.web.command.DishIdInfo;
import net.therap.mealplanner.web.helper.MealDishHelper;
import net.therap.mealplanner.web.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author bashir
 * @since 11/7/16
 */
@Controller
@RequestMapping(value = URL.ROOT+URL.ADMIN+"/")
public class MealController {

    @Autowired
    private MealPlanService mealPlanService;

    @Autowired
    private MealDishHelper mealDishHelper;

    @RequestMapping(value = URL.UPDATE_MEAL, method = RequestMethod.POST)
    public String updateMeal(HttpServletRequest req, String action,
                             @ModelAttribute("selectedDishList")
                             @Valid DishIdInfo selectedDishIdInfo, BindingResult bindingResult,
                             String daySelect) {

        if (!bindingResult.hasErrors()) {
            AuthUser authUser = (AuthUser) req.getSession().getAttribute("user");
            mealPlanService.updateMealPlanForUser(selectedDishIdInfo.getDishIdList(), authUser, mealDishHelper.createNewMeal(daySelect, action));
        }

        return "redirect:/"+URL.ADMIN_HOME;
    }

    @RequestMapping(value = URL.ADD_DISH, method = RequestMethod.POST)
    public String addDish(String dishName) {

        if (!dishName.isEmpty()) {
            mealPlanService.insertNewDish(mealDishHelper.createNewDish(dishName));
        }
        return "redirect:/"+URL.ADMIN_HOME;
    }
}
