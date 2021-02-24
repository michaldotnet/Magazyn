package pl.edu.wszib.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.model.Coupon;
import pl.edu.wszib.model.CouponType;
import pl.edu.wszib.model.User;
import pl.edu.wszib.model.UserRole;
import pl.edu.wszib.services.CouponService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponWebService {

    private final CouponService couponService;

    @Autowired
    public CouponWebService(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/{couponId}")
    public ResponseEntity<?> getCouponById(@PathVariable Long couponId) {
        return new ResponseEntity<>(couponService.getCouponById(couponId), HttpStatus.OK);
    }

    @GetMapping("/getCoupons/{couponType}")
    public ResponseEntity<?> getCouponsByCouponType(@PathVariable CouponType couponType) {
        return new ResponseEntity<List<Coupon>>(couponService.findCouponByCouponType(couponType), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllCoupons() {
        return new ResponseEntity<List<Coupon>>(couponService.getAllCoupons(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveNewCoupon(@RequestBody Coupon coupon) {
        return new ResponseEntity<Coupon>(couponService.addCoupon(coupon), HttpStatus.CREATED);
    }

    @PutMapping("/{couponId}")
    public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @PathVariable Long couponId) {
        return new ResponseEntity<Coupon>(couponService.updateCoupon(couponId, coupon), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{couponId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long couponId) {
        couponService.deleteCoupon(couponId);
        return new ResponseEntity<String>("Coupon with id: " + couponId + " was deleted successfully",
                HttpStatus.OK);
    }
}
