package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 历史正式名单
 * </p>
 *
 * @author CuiCan
 * @since 2017-07-11
 */
@TableName("history_final_user")
public class HistoryFinalUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 历史月份
     */
	@TableField("month_id")
	private String monthId;
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
     * 上报日期
     */
	@TableField("report_date")
	private Date reportDate;
    /**
     * 注册时间
     */
	@TableField("register_time")
	private Date registerTime;
    /**
     * 是否vip：1:是、0:否
     */
	@TableField("is_vipuser")
	private Integer isVipuser;
    /**
     * 成为vip日期
     */
	@TableField("vip_date")
	private Date vipDate;
    /**
     * 投资顾问ID号
     */
	@TableField("advisor_id")
	private Integer advisorId;
    /**
     * 投资顾问姓名
     */
	@TableField("advisor_name")
	private String advisorName;
    /**
     * 用户标识：1:DKH000、2:DKH001
     */
	@TableField("user_mark")
	private String userMark;
    /**
     * 是否业绩池：1:是、0:否
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

	public String getMonthId() {
		return monthId;
	}

	public void setMonthId(String monthId) {
		this.monthId = monthId;
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

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
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

	public Integer getAdvisorId() {
		return advisorId;
	}

	public void setAdvisorId(Integer advisorId) {
		this.advisorId = advisorId;
	}

	public String getAdvisorName() {
		return advisorName;
	}

	public void setAdvisorName(String advisorName) {
		this.advisorName = advisorName;
	}

	public String getUserMark() {
		return userMark;
	}

	public void setUserMark(String userMark) {
		this.userMark = userMark;
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
