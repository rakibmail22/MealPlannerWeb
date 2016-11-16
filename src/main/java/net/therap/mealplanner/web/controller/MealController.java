package net.therap.mealplanner.web.controller;

import net.therap.mealplanner.domain.User;
import net.therap.mealplanner.service.MealPlanService;
import net.therap.mealplanner.web.command.DishIdInfo;
import net.therap.mealplanner.web.helper.MealDishHelper;
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
@RequestMapping(value = "/admin")
public class MealController {

    @Autowired
    private MealPlanService mealPlanService;

    @Autowired
    private MealDishHelper mealDishHelper;

    @RequestMapping(value = "/meal/update", method = RequestMethod.POST)
    public String updateMeal(HttpServletRequest req, String action,
                             @ModelAttribute("selectedDishList")
                             @Valid DishIdInfo selectedDishIdInfo, BindingResult bindingResult,
                             String daySelect) {

        if (!bindingResult.hasErrors()) {
            User user = (User) req.getSession().getAttribute("user");
            mealPlanService.updateMealPlanForUser(selectedDishIdInfo.getDishIdList(), user, mealDishHelper.createNewMeal(daySelect, action));
        }

        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/dish/add", method = RequestMethod.GET)
    public String addDish(String dishName) {

        if (!dishName.isEmpty()) {
            mealPlanService.insertNewDish(mealDishHelper.createNewDish(dishName));
        }
        return "redirect:/admin/home";
    }
}
