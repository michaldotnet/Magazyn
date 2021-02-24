package pl.edu.wszib.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.wszib.model.User;
import pl.edu.wszib.services.AdminService;
import pl.edu.wszib.services.CouponService;
import pl.edu.wszib.services.PointOfSaleService;
import pl.edu.wszib.services.UserService;

@Controller
public class LoginController {

    private UserService userService;
    private CouponService couponService;
    private AdminService adminService;
    private PointOfSaleService pointOfSaleService;

    @Autowired
    public LoginController(UserService userService, CouponService couponService, AdminService adminService, PointOfSaleService pointOfSaleService) {
        this.userService = userService;
        this.couponService = couponService;
        this.adminService = adminService;
        this.pointOfSaleService = pointOfSaleService;
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        return new ModelAndView("loginPage", "user", new User());
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.POST)
    public String loginForm(@ModelAttribute("user") User user, Model model) {
        if (adminService.verifyAccount(user)) return "redirect:index";
        return "loginPage";

    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("coupons", couponService.getAllCoupons());
        return "index";
    }
}

