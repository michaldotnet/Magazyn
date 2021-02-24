package pl.edu.wszib.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.edu.wszib.model.User;
import pl.edu.wszib.model.UserRole;
import pl.edu.wszib.services.CouponService;
import pl.edu.wszib.services.PointOfSaleService;
import pl.edu.wszib.services.UserService;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private CouponService couponService;
    private PointOfSaleService pointOfSaleService;

    @Autowired
    public UserController(UserService userService, CouponService couponService, PointOfSaleService pointOfSaleService) {
        this.userService = userService;
        this.couponService = couponService;
        this.pointOfSaleService = pointOfSaleService;
    }

    @RequestMapping(value = "/addUserInAdminPanelPage", method = RequestMethod.GET)
    public ModelAndView addUserPage(Model model) {
        addNecessaryObjectsToModel(model);
        return new ModelAndView("addUserToSystemPage", "userKey", new User());
    }

    @RequestMapping(value = "/addUserInAdminPanelPage", method = RequestMethod.POST)
    public String sendFormWithNewUserData(@ModelAttribute("userKey") User user,
                                          @RequestParam("login") String login,
                                          @RequestParam("role") List<UserRole> role,
                                          @RequestParam("password") String password,
                                          @RequestParam("email") String email, @RequestParam("fullName") String fullName) {
        user.setRoles(role);
        userService.addUser(user);
        return "redirect:index";
    }

    public void addNecessaryObjectsToModel(Model model) {
        model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("coupons", couponService.getAllCoupons());
    }

    @RequestMapping(value = "/deleteUserInAdminPanelPage", method = RequestMethod.GET)
    public ModelAndView deleteUserPage(Model model) {
        addNecessaryObjectsToModel(model);
        model.addAttribute("errorMessage", "");
        return new ModelAndView("deleteUserInAdminPanelPage", "userKey", new User());
    }


    @RequestMapping(value = "/deleteUserInAdminPanelPage", method = RequestMethod.POST)
    public String tryToDeleteUserByLogin(@RequestParam("login") String login, Model model) {

        try {
            User userFromDbToDelete = userService.getUserByLogin(login);
            userService.deleteUser(userFromDbToDelete.getId());

        } catch (NullPointerException e) {
            model.addAttribute("errorMessage", "There is no user with login that you specified, write login which is bind to existing user.");
            model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("coupons", couponService.getAllCoupons());
            return "deleteUserInAdminPanelPage";
        }
        return "redirect:index";
    }

    @RequestMapping(value = "/userManagementPage", method = RequestMethod.GET)
    public String usersManagementPage(Model model) {
        addNecessaryObjectsToModel(model);
        return "userManagementPage";
    }

    @RequestMapping(value = "showInfoAboutUser", method = RequestMethod.GET)
    public String getPageForDisplayingUserInfo(Model model) {
        model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("coupons", couponService.getAllCoupons());
        model.addAttribute("ErrorInfo", "");
        model.addAttribute("UserInfo", new User());
        return "showInfoAboutUserPage";
    }

    @RequestMapping(value = "showInfoAboutUser", method = RequestMethod.POST)
    public String sendRequestToShowInfoAboutUser(@ModelAttribute("UserInfo") User user, final RedirectAttributes redirectAttributes, Model model) {
        User userFromDbToShowInfoAbout = userService.getUserByLogin(user.getLogin());
        if (userFromDbToShowInfoAbout == null) {
            addNecessaryObjectsToModel(model);
            model.addAttribute("UserInfo", new User());
            model.addAttribute("ErrorInfo", "There is no user with login that you specified, write login which is bind to existing user.");
            return "showInfoAboutUserPage";
        }

        redirectAttributes.addFlashAttribute("userFromDb", userFromDbToShowInfoAbout);
        return "redirect:/showInfoAfterRedirect";
    }

    @RequestMapping(value = "showInfoAfterRedirect", method = RequestMethod.GET)
    public String showInfoAboutUser(@ModelAttribute("userFromDb") User user, Model model) {
        addNecessaryObjectsToModel(model);
        return "showInfoAfterRedirect";
    }

    @RequestMapping(value = "/showInfoAfterRedirect", method = RequestMethod.POST)
    public String goBackToUserManagementPage() {
        return "redirect:/index";
    }


}
