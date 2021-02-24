package pl.edu.wszib.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.edu.wszib.model.Coupon;
import pl.edu.wszib.model.CouponAssignment;
import pl.edu.wszib.model.CouponType;
import pl.edu.wszib.services.CouponService;
import pl.edu.wszib.services.PointOfSaleService;
import pl.edu.wszib.services.UserService;

import java.util.Date;

@Controller
public class CouponController {

    private UserService userService;
    private CouponService couponService;
    private PointOfSaleService pointOfSaleService;

    @Autowired
    public CouponController(UserService userService, CouponService couponService, PointOfSaleService pointOfSaleService) {
        this.userService = userService;
        this.couponService = couponService;
        this.pointOfSaleService = pointOfSaleService;
    }

    @RequestMapping(value = "/addCouponInAdminPanelPage", method = RequestMethod.GET)
    public ModelAndView addCouponPage(Model model) {
        model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
        model.addAttribute("coupons", couponService.getAllCoupons());
        model.addAttribute("users", userService.getAllUsers());
        return new ModelAndView("addCouponToSystemPage", "userKey", new Coupon());
    }

    @RequestMapping(value = "/addCouponInAdminPanelPage", method = RequestMethod.POST)
    public String sendFormWithNewCouponData(@ModelAttribute("userKey") Coupon coupon,
                                            @RequestParam("couponCode") String couponCode,
                                            @RequestParam("freeShipping") Boolean freeShipping,
                                            @RequestParam("couponType") CouponType couponType,
                                            @RequestParam("couponAssignment") CouponAssignment couponAssignment,
                                            @RequestParam("discount") Integer discount,
                                            @RequestParam("expiryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date expiryDate) {
        coupon.setExpiryDate(expiryDate);
        couponService.addCoupon(coupon);
        return "redirect:index";
    }

    @RequestMapping(value = "/deleteCouponInAdminPanelPage", method = RequestMethod.GET)
    public ModelAndView deleteCouponPage(Model model) {
        model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
        model.addAttribute("coupons", couponService.getAllCoupons());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("errorMessage", "");
        return new ModelAndView("deleteCouponInAdminPanelPage", "userKey", new Coupon());
    }


    @RequestMapping(value = "/deleteCouponInAdminPanelPage", method = RequestMethod.POST)
    public String tryToDeleteCouponByCouponCode(@RequestParam("couponCode") String couponCode, Model model) {

        try {
            Coupon couponFromDbToDelete = couponService.getCouponByCouponCode(couponCode);
            couponService.deleteCoupon(couponFromDbToDelete.getId());

        } catch (NullPointerException e) {
            model.addAttribute("errorMessage", "There is no coupon with code that you specified, write coupon code which is bind to existing coupon.");
            model.addAttribute("coupons", couponService.getAllCoupons());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
            return "deleteCouponInAdminPanelPage";
        }
        return "redirect:index";
    }

    @RequestMapping(value = "/couponManagementPage", method = RequestMethod.GET)
    public String couponsManagementPage(Model model) {
        model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
        model.addAttribute("coupons", couponService.getAllCoupons());
        model.addAttribute("users", userService.getAllUsers());
        return "couponManagementPage";
    }


    @RequestMapping(value = "showInfoAboutCoupon", method = RequestMethod.GET)
    public String getPageForDisplayingCouponInfo(Model model) {
        model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
        model.addAttribute("coupons", couponService.getAllCoupons());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("ErrorInfo", "");
        model.addAttribute("CouponInfo", new Coupon());
        return "showInfoAboutCouponPage";
    }

    @RequestMapping(value = "showInfoAboutCoupon", method = RequestMethod.POST)
    public String sendRequestToShowInfoAboutCoupon(@ModelAttribute("CouponInfo") Coupon coupon, final RedirectAttributes redirectAttributes, Model model) {
        Coupon couponFromDbToShowInfoAbout = couponService.getCouponByCouponCode(coupon.getCouponCode());
        if (couponFromDbToShowInfoAbout == null) {
            model.addAttribute("CouponInfo", new Coupon());
            model.addAttribute("ErrorInfo", "There is no coupon with code that you specified, write coupon code which is bind to existing coupon.");
            model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("coupons", couponService.getAllCoupons());
            return "showInfoAboutCouponPage";
        }

        redirectAttributes.addFlashAttribute("couponFromDb", couponFromDbToShowInfoAbout);
        return "redirect:/showCouponInfoAfterRedirect";
    }

    @RequestMapping(value = "showCouponInfoAfterRedirect", method = RequestMethod.GET)
    public String showInfoAboutCoupon(@ModelAttribute("couponFromDb") Coupon coupon, Model model) {
        model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("coupons", couponService.getAllCoupons());
        return "showCouponInfoAfterRedirect";
    }

    @RequestMapping(value = "/showCouponInfoAfterRedirect", method = RequestMethod.POST)
    public String goBackToCouponManagementPage() {
        return "redirect:/index";
    }
}
