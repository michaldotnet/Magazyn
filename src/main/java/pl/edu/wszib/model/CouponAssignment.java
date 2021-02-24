package pl.edu.wszib.model;

public enum CouponAssignment {

    // Products should be downloaded from API Skoczkow
    TV,
    PHONE,
    BREAD,

    // All products , only 1 coupon existing with this type
    ALL,

    // All products within cart
    ALL_CART;
}
