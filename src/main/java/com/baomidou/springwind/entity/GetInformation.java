package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 获客信息
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-15
 */
@TableName("get_information")
public class GetInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 推荐人手机号
     */
	@TableField("t_mobile_no")
	private String tMobileNo;
    /**
     * 推荐人会员号
     */
	@TableField("t_member_no")
	private String tMemberNo;
    /**
     * 推荐人姓名
     */
	@TableField("t_user_name")
	private String tUserName;
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
     * 推荐人客户类别：1：上报，2：分配，3：未分配vip
     */
	@TableField("t_user_type")
	private Integer tUserType;
    /**
     * 推荐人上报分配日期
     */
	@TableField("t_report_date")
	private Date tReportDate;
    /**
     * 推荐人是否业绩池：0：否，1：是
     */
	@TableField("t_is_performance_pool")
	private Integer tIsPerformancePool;
    /**
     * 被推荐人手机号
     */
	@TableField("bt_mobile_no")
	private String btMobileNo;
    /**
     * 被推荐人会员号
     */
	@TableField("bt_member_no")
	private String btMemberNo;
    /**
     * 被推荐人姓名
     */
	@TableField("bt_user_name")
	private String btUserName;
    /**
     * 被推荐人注册时间
     */
	@TableField("bt_register_time")
	private Date btRegisterTime;
    /**
     * 被推荐人投资金额（不含活期和转让）
     */
	@TableField("bt_trans_amount")
	private Double btTransAmount;
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

	public String gettMobileNo() {
		return tMobileNo;
	}

	public void settMobileNo(String tMobileNo) {
		this.tMobileNo = tMobileNo;
	}

	public String gettMemberNo() {
		return tMemberNo;
	}

	public void settMemberNo(String tMemberNo) {
		this.tMemberNo = tMemberNo;
	}

	public String gettUserName() {
		return tUserName;
	}

	public void settUserName(String tUserName) {
		this.tUserName = tUserName;
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

	public Integer gettUserType() {
		return tUserType;
	}

	public void settUserType(Integer tUserType) {
		this.tUserType = tUserType;
	}

	public Date gettReportDate() {
		return tReportDate;
	}

	public void settReportDate(Date tReportDate) {
		this.tReportDate = tReportDate;
	}

	public Integer gettIsPerformancePool() {
		return tIsPerformancePool;
	}

	public void settIsPerformancePool(Integer tIsPerformancePool) {
		this.tIsPerformancePool = tIsPerformancePool;
	}

	public String getBtMobileNo() {
		return btMobileNo;
	}

	public void setBtMobileNo(String btMobileNo) {
		this.btMobileNo = btMobileNo;
	}

	public String getBtMemberNo() {
		return btMemberNo;
	}

	public void setBtMemberNo(String btMemberNo) {
		this.btMemberNo = btMemberNo;
	}

	public String getBtUserName() {
		return btUserName;
	}

	public void setBtUserName(String btUserName) {
		this.btUserName = btUserName;
	}

	public Date getBtRegisterTime() {
		return btRegisterTime;
	}

	public void setBtRegisterTime(Date btRegisterTime) {
		this.btRegisterTime = btRegisterTime;
	}

	public Double getBtTransAmount() {
		return btTransAmount;
	}

	public void setBtTransAmount(Double btTransAmount) {
		this.btTransAmount = btTransAmount;
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
