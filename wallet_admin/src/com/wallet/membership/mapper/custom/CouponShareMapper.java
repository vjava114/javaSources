package com.wallet.membership.mapper.custom;

import java.util.List;

import com.wallet.membership.model.custom.CouponShare;

public interface CouponShareMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table memb_agree
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    int countByExample(CouponShare couponShare);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table memb_agree
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
   
    List<CouponShare> selectByExample(CouponShare couponShare);  
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table memb_agree
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
//    int updateBycpnlistSelective(CouponShare couponShare);
//   int updateBymembcardlistSelective(CouponShare couponShare);
    int updateByMwCsCpnlistSelective(CouponShare couponShare);
//    int updateByMWCSCPNSHARERATIOSelective(CouponShare couponShare);
    
    int insertSelective(CouponShare couponshare);
}