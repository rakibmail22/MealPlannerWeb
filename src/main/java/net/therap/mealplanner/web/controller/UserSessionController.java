package net.therap.mealplanner.web.controller;

import net.therap.mealplanner.domain.User;
import net.therap.mealplanner.service.UserDetailsService;
import net.therap.mealplanner.service.UserDetailsServiceImpl;
import net.therap.mealplanner.web.command.LoginFormInfo;
import net.therap.mealplanner.web.command.SignUpFormInfo;
import net.therap.mealplanner.web.helper.LoginHelper;
import net.therap.mealplanner.web.helper.SignUpHelper;
import net.therap.mealplanner.web.validator.SignUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author bashir
 * @since 11/6/16
 */

@Controller
public class UserSessionController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SignUpHelper signUpHelper;

    @Autowired
    private LoginHelper loginHelper;

    @Autowired
    SignUpFormValidator signUpFormValidator;

    @InitBinder("signUpFormInfo")
    private void initSignUpBinder(WebDataBinder binder) {
        binder.addValidators(signUpFormValidator);
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String displayLogin(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        if (loginHelper.userAlreadyLoggedIn(user)) {
            return loginHelper.redirectUserHome(user);
        }

        model.addAttribute("signUpFormInfo", new SignUpFormInfo());
        model.addAttribute("loginFormInfo", new LoginFormInfo());

        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String loginSubmit(HttpSession session, Model model,
                              @ModelAttribute("loginFormInfo")
                              @Valid LoginFormInfo loginFormInfo,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("signUpFormInfo", new SignUpFormInfo());
            return "login";
        }

        User user = userDetailsService.validateUser(loginFormInfo.getUsername(), loginFormInfo.getPassword());

        if (null != user) {
            loginHelper.persistSessionData(user, session);
            return loginHelper.redirectUserHome(user);
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String displaySignUp(Model model) {

        model.addAttribute("loginFormInfo", new LoginFormInfo());
        model.addAttribute("signUpFormInfo", new SignUpFormInfo());
        return "forward:/login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpSubmit(HttpSession session,
                               @ModelAttribute("signUpFormInfo")
                               @Valid SignUpFormInfo signUpFormInfo,
                               BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            User user = signUpHelper.createNewUser(signUpFormInfo);
            user = userDetailsService.addNewUser(user);
            loginHelper.persistSessionData(user, session);
            return loginHelper.redirectUserHome(user);
        }

        model.addAttribute(signUpFormInfo);
        model.addAttribute("loginFormInfo", new LoginFormInfo());
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String pageNotFound() {

        return "404";
    }

}
