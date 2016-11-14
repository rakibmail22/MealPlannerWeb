package net.therap.mealplanner.web.controller;

import net.therap.mealplanner.domain.User;
import net.therap.mealplanner.service.SignUpService;
import net.therap.mealplanner.service.UserDetailsService;
import net.therap.mealplanner.web.validator.LoginFormValidator;
import net.therap.mealplanner.web.validator.SignUpFormValidator;
import net.therap.mealplanner.web.command.LoginFormInfo;
import net.therap.mealplanner.web.command.SignUpFormInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author bashir
 * @since 11/6/16
 */

@Controller
public class UserSessionController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    SignUpFormValidator signUpFormValidator;

    @Autowired
    LoginFormValidator loginFormValidator;


    @InitBinder("loginFormInfo")
    private void initLoginBinder(WebDataBinder binder) {
        binder.setValidator(loginFormValidator);
    }

    @InitBinder("signUpFormInfo")
    private void initSignUpBinder(WebDataBinder binder) {
        binder.setValidator(signUpFormValidator);
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String displayLogin(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        if (userAlreadyLoggedIn(user)) {
            return redirectUserHome(user);
        }
        model.addAttribute("signUpFormInfo", new SignUpFormInfo());
        model.addAttribute("loginFormInfo", new LoginFormInfo());

        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String loginSubmit(HttpSession session, Model model,
                              @ModelAttribute("loginFormInfo")
                              @Validated LoginFormInfo loginFormInfo,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(loginFormInfo);
            model.addAttribute("signUpFormInfo", new SignUpFormInfo());
            return "login";
        }

        User user = userDetailsService.validateUser(loginFormInfo.getUsername(), loginFormInfo.getPassword());

        if (null != user) {
            persistSessionData(user, session);
            return redirectUserHome(user);
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
                               @Validated SignUpFormInfo signUpFormInfo,
                               BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            User user = signUpService.createNewUser(signUpFormInfo.getName(), signUpFormInfo.getEmail(), signUpFormInfo.getPassword());
            user = userDetailsService.addNewUser(user);
            persistSessionData(user, session);
            return redirectUserHome(user);
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

    public boolean userAlreadyLoggedIn(User user) {

        if (null != user) {
            return true;
        } else {
            return false;
        }
    }

    private String redirectUserHome(User user) {

        if (("admin").equals(user.getRole())) {
            return "redirect:/admin/home";
        } else {
            return "redirect:/home";
        }
    }

    private void persistSessionData(User user, HttpSession session) {
        session.setAttribute("user", user);
    }
}
