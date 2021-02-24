package pl.edu.wszib.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.dao.CouponDAO;
import pl.edu.wszib.model.Coupon;
import pl.edu.wszib.model.CouponType;
import pl.edu.wszib.services.CouponService;

import java.util.List;

@Component
public class CouponServiceImpl implements CouponService {

    private CouponDAO couponDAO;

    @Autowired
    public CouponServiceImpl(CouponDAO couponDAO) {
        this.couponDAO = couponDAO;
    }

    @Override
    public List<Coupon> findCouponByCouponType(CouponType couponType) {
        return couponDAO.findCouponByCouponType(couponType);
    }

    @Override
    public Coupon getCouponById(Long id) {
        return couponDAO.getCouponById(id);
    }

    @Override
    public Coupon getCouponByCouponCode(String couponCode) {
        return couponDAO.getCouponByCouponCode(couponCode);
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponDAO.getAllCoupons();
    }

    @Override
    public Coupon updateCoupon(Long couponId, Coupon coupon) {
        coupon.setId(couponId);
        return couponDAO.updateCoupon(coupon);
    }

    @Override
    public void deleteCoupon(Long id) {
        couponDAO.deleteCoupon(id);
    }

    @Override
    public Coupon addCoupon(Coupon coupon) {
        return couponDAO.addCoupon(coupon);
    }
}
