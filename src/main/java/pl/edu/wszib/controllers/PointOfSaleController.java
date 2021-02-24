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
import pl.edu.wszib.model.PointOfSale;
import pl.edu.wszib.services.CouponService;
import pl.edu.wszib.services.PointOfSaleService;
import pl.edu.wszib.services.UserService;


@Controller
public class PointOfSaleController {

    private UserService userService;
    private CouponService couponService;
    private PointOfSaleService pointOfSaleService;

    @Autowired
    public PointOfSaleController(UserService userService, CouponService couponService, PointOfSaleService pointOfSaleService) {
        this.userService = userService;
        this.couponService = couponService;
        this.pointOfSaleService = pointOfSaleService;
    }

    @RequestMapping(value = "/addPosInAdminPanelPage", method = RequestMethod.GET)
    public ModelAndView addPointOfSalePage(Model model) {
        model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("coupons", couponService.getAllCoupons());
        return new ModelAndView("addPosToSystemPage", "userKey", new PointOfSale());
    }

    @RequestMapping(value = "/addPosInAdminPanelPage", method = RequestMethod.POST)
    public String sendFormWithNewPointOfSaleData(@ModelAttribute("userKey") PointOfSale pointOfSale,
                                                 @RequestParam("name") String login,
                                                 @RequestParam("location") String location,
                                                 @RequestParam("phoneNumber") String phoneNumber,
                                                 @RequestParam("mail") String mail) {
        pointOfSaleService.addPointOfSale(pointOfSale);
        return "redirect:index";
    }

    @RequestMapping(value = "/deletePosInAdminPanelPage", method = RequestMethod.GET)
    public ModelAndView deletePointOfSalePage(Model model) {
        model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("coupons", couponService.getAllCoupons());
        model.addAttribute("errorMessage", "");
        return new ModelAndView("deletePosInAdminPanelPage", "userKey", new PointOfSale());
    }


    @RequestMapping(value = "/deletePosInAdminPanelPage", method = RequestMethod.POST)
    public String tryToDeletePointOfSaleByName(@RequestParam("name") String name, Model model) {

        try {
            PointOfSale pointOfSaleFromDbToDelete = pointOfSaleService.getPointOfSaleByName(name);
            pointOfSaleService.deletePointOfSale(pointOfSaleFromDbToDelete.getId());

        } catch (NullPointerException e) {
            model.addAttribute("errorMessage", "There is no point of sale with name that you specified, write name which is bind to existing point of sale.");
            model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("coupons", couponService.getAllCoupons());
            return "deletePosInAdminPanelPage";
        }
        return "redirect:index";
    }

    @RequestMapping(value = "/posManagementPage", method = RequestMethod.GET)
    public String pointsOfSaleManagementPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("coupons", couponService.getAllCoupons());
        model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
        return "posManagementPage";
    }

    @RequestMapping(value = "showInfoAboutPos", method = RequestMethod.GET)
    public String getPageForDisplayingPointOfSaleInfo(Model model) {
        model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("coupons", couponService.getAllCoupons());
        model.addAttribute("ErrorInfo", "");
        model.addAttribute("PointOfSaleInfo", new PointOfSale());
        return "showInfoAboutPosPage";
    }

    @RequestMapping(value = "showInfoAboutPos", method = RequestMethod.POST)
    public String sendRequestToShowInfoAboutPointOfSale(@ModelAttribute("PointOfSaleInfo") PointOfSale pointOfSale, final RedirectAttributes redirectAttributes, Model model) {
        PointOfSale pointOfSaleFromDbToShowInfoAbout = pointOfSaleService.getPointOfSaleByName(pointOfSale.getName());
        if (pointOfSaleFromDbToShowInfoAbout == null) {
            model.addAttribute("PointOfSaleInfo", new PointOfSale());
            model.addAttribute("ErrorInfo", "There is no point of sale with name that you specified, write name which is bind to existing point of sale.");
            model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("coupons", couponService.getAllCoupons());
            return "showInfoAboutPosPage";
        }

        redirectAttributes.addFlashAttribute("pointOfSaleFromDb", pointOfSaleFromDbToShowInfoAbout);
        return "redirect:/showPosInfoAfterRedirect";
    }

    @RequestMapping(value = "showPosInfoAfterRedirect", method = RequestMethod.GET)
    public String showInfoAboutPointOfSale(@ModelAttribute("pointOfSaleFromDb") PointOfSale pointOfSale, Model model) {
        model.addAttribute("pointsOfSale", pointOfSaleService.getAllPointOfSale());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("coupons", couponService.getAllCoupons());
        return "showPosInfoAfterRedirect";
    }

    @RequestMapping(value = "/showPosInfoAfterRedirect", method = RequestMethod.POST)
    public String goBackToPointOfSaleManagementPage() {
        return "redirect:/index";
    }

}
