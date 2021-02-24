package pl.edu.wszib.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.wszib.model.*;
import pl.edu.wszib.services.CouponService;
import pl.edu.wszib.services.PointOfSaleService;
import pl.edu.wszib.services.ReportService;
import pl.edu.wszib.services.UserService;

import java.util.Arrays;
import java.util.Calendar;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService IUserService;
    private final CouponService ICouponService;
    private final PointOfSaleService IPointOfSaleService;
    private final ReportService reportService;

    @Autowired
    public DataLoader(UserService IUserService, CouponService ICouponService,
                      PointOfSaleService IPointOfSaleService, ReportService reportService) {
        this.IUserService = IUserService;
        this.ICouponService = ICouponService;
        this.IPointOfSaleService = IPointOfSaleService;
        this.reportService = reportService;
    }

    @Override
    public void run(String... args) throws Exception {
//        loadUsers();
//        loadCoupons();
//        loadPointOfSales();
//        loadReports();
    }

    private void loadReports() {
        Report report = new Report();
        report.setLogin("Cosiek");
        report.setWhenWasLogged("nwm");
        report.setPointOfSales("Niedzwiedz");

        reportService.addReport(report);

        Report report1 = new Report();
        report1.setLogin("dasdasdas");
        report1.setWhenWasLogged("eqw231");
        report1.setPointOfSales("312312");

        reportService.addReport(report1);
    }

    private void loadPointOfSales(){
        PointOfSale pointOfSale1 = new PointOfSale();
        pointOfSale1.setName("Pikobelo");
        pointOfSale1.setLocation("Sosnowiec");
        pointOfSale1.setPhoneNumber("700300200");
        pointOfSale1.setMail("pikobelosklep@dzimejl.kom");

        IPointOfSaleService.addPointOfSale(pointOfSale1);

        PointOfSale pointOfSale2 = new PointOfSale();
        pointOfSale2.setName("Ajbiem");
        pointOfSale2.setLocation("Nidek");
        pointOfSale2.setPhoneNumber("323455699");
        pointOfSale2.setMail("ajbiemklep@dzimejl.kom");

        IPointOfSaleService.addPointOfSale(pointOfSale2);

        PointOfSale pointOfSale3 = new PointOfSale();
        pointOfSale3.setName("Sikalafon");
        pointOfSale3.setLocation("Radom");
        pointOfSale3.setPhoneNumber("699334030");
        pointOfSale3.setMail("sikalafonsklep@dzimejl.kom");

        IPointOfSaleService.addPointOfSale(pointOfSale3);

        PointOfSale pointOfSale4 = new PointOfSale();
        pointOfSale4.setName("Tiktak");
        pointOfSale4.setLocation("Warszawa");
        pointOfSale4.setPhoneNumber("999756590");
        pointOfSale4.setMail("tiktaksklep@dzimejl.kom");

        IPointOfSaleService.addPointOfSale(pointOfSale4);


    }

    private void loadCoupons() {
        Coupon percentageCoupon = new Coupon();
        percentageCoupon.setFreeShipping(true);
        percentageCoupon.setCouponCode("paleciok");
        percentageCoupon.setCouponAssignment(CouponAssignment.PHONE);
        percentageCoupon.setDiscount(30);
        percentageCoupon.setCouponType(CouponType.SPECIFIC_PRODUCT);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        percentageCoupon.setExpiryDate(cal.getTime());

        ICouponService.addCoupon(percentageCoupon);

        Coupon regularCoupon = new Coupon();
        regularCoupon.setFreeShipping(false);
        regularCoupon.setCouponCode("random");
        regularCoupon.setCouponAssignment(CouponAssignment.ALL);
        regularCoupon.setDiscount(5);
        regularCoupon.setCouponType(CouponType.ALL_PRODUCTS);

        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2020);
        cal1.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal1.set(Calendar.DAY_OF_MONTH, 3);

        regularCoupon.setExpiryDate(cal1.getTime());

        ICouponService.addCoupon(regularCoupon);

        Coupon cartCoupon = new Coupon();
        cartCoupon.setFreeShipping(true);
        cartCoupon.setDiscount(3);
        cartCoupon.setCouponCode("cart");
        cartCoupon.setCouponAssignment(CouponAssignment.ALL_CART);
        cartCoupon.setCouponType(CouponType.CART);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 2020);
        cal2.set(Calendar.MONTH, Calendar.MARCH);
        cal2.set(Calendar.DAY_OF_MONTH, 12);


        cartCoupon.setExpiryDate(cal2.getTime());

        ICouponService.addCoupon(cartCoupon);
    }


    private void loadUsers() {
        User adi = new User();
        adi.setLogin("Adi");
        adi.setPassword("1");
        adi.setFullName("Adrian Rusinek");
        adi.setEmail("adek@gmail.com");
        adi.setRoles(Arrays.asList(UserRole.ADMIN /** xD **/));

        IUserService.addUser(adi);

        User dawid = new User();
        dawid.setLogin("Dejw");
        dawid.setPassword("2");
        dawid.setFullName("Dawid Jurecki");
        dawid.setEmail("dave@gmail.com");
        dawid.setRoles(Arrays.asList(UserRole.STOREKEEPER));

        IUserService.addUser(dawid);

        User michal = new User();
        michal.setLogin("Majkel");
        michal.setPassword("3");
        michal.setFullName("Michal Podsiadlo");
        michal.setEmail("michal@gmail.com");
        michal.setRoles(Arrays.asList(UserRole.CASHIER, UserRole.STOREKEEPER));

        IUserService.addUser(michal);
    }
}
