package com.baomidou.springwind.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by on 2017/6/25.
 */
public class SalesDetailVo implements Serializable {

    private static final long serialVersionUID = -3712601135541524085L;

    private String userName;

    private String mobileNo;

    private Integer userType;

    /**
     * 投资起始日期
     */
    private Date transTimeStart;

    /**
     * 投资结束日期
     */
    private Date transTimeEnd;

    /**
     * 产品类别
     */
    private Integer productType;

    /**
     * 投资金额小于等于
     */
    private Double transAmountBig;

    /**
     * 投资金额大于等于
     */
    private Double transAmountSmall;

    /**
     * 期限类型
     */
    private Integer limitType;

    private String advisorName;

    private Integer isPerformancePool;

    private String userMark;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getTransTimeStart() {
        return transTimeStart;
    }

    public void setTransTimeStart(Date transTimeStart) {
        this.transTimeStart = transTimeStart;
    }

    public Date getTransTimeEnd() {
        return transTimeEnd;
    }

    public void setTransTimeEnd(Date transTimeEnd) {
        this.transTimeEnd = transTimeEnd;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Double getTransAmountBig() {
        return transAmountBig;
    }

    public void setTransAmountBig(Double transAmountBig) {
        this.transAmountBig = transAmountBig;
    }

    public Double getTransAmountSmall() {
        return transAmountSmall;
    }

    public void setTransAmountSmall(Double transAmountSmall) {
        this.transAmountSmall = transAmountSmall;
    }

    public Integer getLimitType() {
        return limitType;
    }

    public void setLimitType(Integer limitType) {
        this.limitType = limitType;
    }

    public String getAdvisorName() {
        return advisorName;
    }

    public void setAdvisorName(String advisorName) {
        this.advisorName = advisorName;
    }

    public Integer getIsPerformancePool() {
        return isPerformancePool;
    }

    public void setIsPerformancePool(Integer isPerformancePool) {
        this.isPerformancePool = isPerformancePool;
    }

    public String getUserMark() {
        return userMark;
    }

    public void setUserMark(String userMark) {
        this.userMark = userMark;
    }
}
