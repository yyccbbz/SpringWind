package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 未分配的VIP名单
 * </p>
 *
 * @author CuiCan
 * @since 2017-07-11
 */
@TableName("unassigned_vip_user")
public class UnassignedVipUser implements Serializable {

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
     * 注册时间
     */
	@TableField("register_time")
	private Date registerTime;
    /**
     * 成为vip日期
     */
	@TableField("vip_date")
	private Date vipDate;
    /**
     * 客户成为vip时的历史定期投资额
     */
	@TableField("vip_trans_dingqi")
	private Integer vipTransDingqi;
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

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getVipDate() {
		return vipDate;
	}

	public void setVipDate(Date vipDate) {
		this.vipDate = vipDate;
	}

	public Integer getVipTransDingqi() {
		return vipTransDingqi;
	}

	public void setVipTransDingqi(Integer vipTransDingqi) {
		this.vipTransDingqi = vipTransDingqi;
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
