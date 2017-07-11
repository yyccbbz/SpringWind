package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 销售明细
 * </p>
 *
 * @author CuiCan
 * @since 2017-07-11
 */
@TableName("sales_details")
public class SalesDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 客户手机号
     */
	@TableField("mobile_no")
	private String mobileNo;
    /**
     * 客户会员号
     */
	@TableField("member_no")
	private String memberNo;
    /**
     * 客户姓名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 投资顾问ID号
     */
	@TableField("advisor_id")
	private String advisorId;
    /**
     * 投资顾问姓名
     */
	@TableField("advisor_name")
	private String advisorName;
    /**
     * 产品ID号
     */
	@TableField("product_id")
	private String productId;
    /**
     * 产品名称
     */
	@TableField("product_name")
	private String productName;
    /**
     * 产品类型：1:定期、2:活期、3:活包定、4:转让
     */
	@TableField("product_type")
	private Integer productType;
    /**
     * 产品利率
     */
	@TableField("product_rate")
	private String productRate;
    /**
     * 投资金额
     */
	@TableField("trans_amount")
	private Double transAmount;
    /**
     * 投资时间
     */
	@TableField("trans_time")
	private Date transTime;
    /**
     * 定期产品成立日
     */
	@TableField("inception_date")
	private Date inceptionDate;
    /**
     * 定期产品到期日
     */
	@TableField("due_date")
	private Date dueDate;
    /**
     * 定期产品期限（天）
     */
	@TableField("limit_days")
	private Integer limitDays;
    /**
     * 定期产品期限类型：0:新人专享、6:6个月、12:12个月、99:99个月
     */
	@TableField("limit_type")
	private Integer limitType;
    /**
     * 客户类别：1:上报、2:分配、3:未分配vip
     */
	@TableField("user_type")
	private Integer userType;
    /**
     * 客户注册时间
     */
	@TableField("register_time")
	private Date registerTime;
    /**
     * 上报分配日期
     */
	@TableField("report_date")
	private Date reportDate;
    /**
     * 是否vip：0:否、1:是
     */
	@TableField("is_vipuser")
	private Integer isVipuser;
    /**
     * 成为vip日期
     */
	@TableField("vip_date")
	private Date vipDate;
    /**
     * 是否业绩池：0:否、1:是
     */
	@TableField("is_performance_pool")
	private Integer isPerformancePool;
    /**
     * 用户标识：1:DKH000、2:DKH001
     */
	@TableField("user_mark")
	private String userMark;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAdvisorId() {
		return advisorId;
	}

	public void setAdvisorId(String advisorId) {
		this.advisorId = advisorId;
	}

	public String getAdvisorName() {
		return advisorName;
	}

	public void setAdvisorName(String advisorName) {
		this.advisorName = advisorName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getProductRate() {
		return productRate;
	}

	public void setProductRate(String productRate) {
		this.productRate = productRate;
	}

	public Double getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(Double transAmount) {
		this.transAmount = transAmount;
	}

	public Date getTransTime() {
		return transTime;
	}

	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}

	public Date getInceptionDate() {
		return inceptionDate;
	}

	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getLimitDays() {
		return limitDays;
	}

	public void setLimitDays(Integer limitDays) {
		this.limitDays = limitDays;
	}

	public Integer getLimitType() {
		return limitType;
	}

	public void setLimitType(Integer limitType) {
		this.limitType = limitType;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Integer getIsVipuser() {
		return isVipuser;
	}

	public void setIsVipuser(Integer isVipuser) {
		this.isVipuser = isVipuser;
	}

	public Date getVipDate() {
		return vipDate;
	}

	public void setVipDate(Date vipDate) {
		this.vipDate = vipDate;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
