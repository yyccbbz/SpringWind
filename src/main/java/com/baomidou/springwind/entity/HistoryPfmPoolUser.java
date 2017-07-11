package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 历史业绩池名单
 * </p>
 *
 * @author CuiCan
 * @since 2017-07-11
 */
@TableName("history_pfm_pool_user")
public class HistoryPfmPoolUser implements Serializable {

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
     * 客户姓名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 纳入业绩池日期
     */
	@TableField("pfm_pool_date")
	private Date pfmPoolDate;
    /**
     * 投资顾问姓名
     */
	@TableField("advisor_name")
	private String advisorName;
    /**
     * 用户标识：DKH000/DKH001
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getPfmPoolDate() {
		return pfmPoolDate;
	}

	public void setPfmPoolDate(Date pfmPoolDate) {
		this.pfmPoolDate = pfmPoolDate;
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
