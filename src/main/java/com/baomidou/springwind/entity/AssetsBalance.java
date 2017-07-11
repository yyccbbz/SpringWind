package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 资产余额表
 * </p>
 *
 * @author CuiCan
 * @since 2017-07-11
 */
@TableName("assets_balance")
public class AssetsBalance implements Serializable {

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
     * 纯定期AUM
     */
	@TableField("dingqi_aum")
	private Double dingqiAum;
    /**
     * 活期AUM
     */
	@TableField("huoqi_aum")
	private Double huoqiAum;
    /**
     * 活包定AUM
     */
	@TableField("huobaoding_aum")
	private Double huobaodingAum;
    /**
     * 二级市场转让AUM
     */
	@TableField("secondmarket_aum")
	private Double secondmarketAum;
    /**
     * 持有理财AUM
     */
	@TableField("trans_aum")
	private Double transAum;
    /**
     * 账户余额AUM
     */
	@TableField("account_aum")
	private Double accountAum;
    /**
     * AUM更新日期
     */
	@TableField("aum_date")
	private Date aumDate;
    /**
     * 是否业绩池：0:否、1:是
     */
	@TableField("is_performance_pool")
	private Integer isPerformancePool;
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

	public Double getDingqiAum() {
		return dingqiAum;
	}

	public void setDingqiAum(Double dingqiAum) {
		this.dingqiAum = dingqiAum;
	}

	public Double getHuoqiAum() {
		return huoqiAum;
	}

	public void setHuoqiAum(Double huoqiAum) {
		this.huoqiAum = huoqiAum;
	}

	public Double getHuobaodingAum() {
		return huobaodingAum;
	}

	public void setHuobaodingAum(Double huobaodingAum) {
		this.huobaodingAum = huobaodingAum;
	}

	public Double getSecondmarketAum() {
		return secondmarketAum;
	}

	public void setSecondmarketAum(Double secondmarketAum) {
		this.secondmarketAum = secondmarketAum;
	}

	public Double getTransAum() {
		return transAum;
	}

	public void setTransAum(Double transAum) {
		this.transAum = transAum;
	}

	public Double getAccountAum() {
		return accountAum;
	}

	public void setAccountAum(Double accountAum) {
		this.accountAum = accountAum;
	}

	public Date getAumDate() {
		return aumDate;
	}

	public void setAumDate(Date aumDate) {
		this.aumDate = aumDate;
	}

	public Integer getIsPerformancePool() {
		return isPerformancePool;
	}

	public void setIsPerformancePool(Integer isPerformancePool) {
		this.isPerformancePool = isPerformancePool;
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
