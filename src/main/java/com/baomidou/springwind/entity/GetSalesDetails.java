package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 获客销售明细
 * </p>
 *
 * @author CuiCan
 * @since 2017-07-11
 */
@TableName("get_sales_details")
public class GetSalesDetails implements Serializable {

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
	private String advisorId;
    /**
     * 投资顾问姓名
     */
	@TableField("advisor_name")
	private String advisorName;
    /**
     * 推荐人客户类别：1:上报、2:分配、3:未分配vip
     */
	@TableField("t_user_type")
	private Integer tUserType;
    /**
     * 推荐人上报分配日期
     */
	@TableField("t_report_date")
	private Date tReportDate;
    /**
     * 推荐人是否业绩池：0:否、1:是
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
     * 定期产品期限类型：0:新人专享、6:6个月、12:12个月
     */
	@TableField("limit_type")
	private Integer limitType;
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
