package pl.edu.wszib.dao;

import pl.edu.wszib.model.Coupon;
import pl.edu.wszib.model.CouponType;

import java.util.List;

public interface CouponDAO {

    Coupon getCouponById(Long id);

    Coupon getCouponByCouponCode(String couponCode);

    List<Coupon> getAllCoupons();

    Coupon updateCoupon(Coupon coupon);

    void deleteCoupon(Long id);

    Coupon addCoupon(Coupon coupon);

    List<Coupon> findCouponByCouponType(CouponType couponType);
}
