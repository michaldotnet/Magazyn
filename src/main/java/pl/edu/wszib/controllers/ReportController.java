package pl.edu.wszib.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.services.CouponService;
import pl.edu.wszib.services.PointOfSaleService;
import pl.edu.wszib.services.UserService;


@Controller
public class ReportController {

    private UserService userService;
    private CouponService couponService;
    private PointOfSaleService pointOfSaleService;

    @Autowired
    public ReportController(UserService userService, CouponService couponService, PointOfSaleService pointOfSaleService) {
        this.userService = userService;
        this.couponService = couponService;
        this.pointOfSaleService = pointOfSaleService;
    }

    @RequestMapping(value = "/addReportingToSystemPage", method = RequestMethod.GET)
    public String addReportPage(Model model) {
        addNecessaryObjectsToModel(model);
        return "addReportingToSystemPage";
    }

    public void addNecessaryObjectsToModel(Model model) {
        model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("coupons", couponService.getAllCoupons());
    }

    @RequestMapping(value = "/reportManagementPage", method = RequestMethod.GET)
    public String reportManagementPage(Model model) {
        addNecessaryObjectsToModel(model);
        return "reportManagementPage";
    }


}
