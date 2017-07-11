package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户注册情况
 * </p>
 *
 * @author CuiCan
 * @since 2017-07-11
 */
@TableName("customer_registry")
public class CustomerRegistry implements Serializable {

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
     * 是否注册：0:否、1:是
     */
	@TableField("is_register")
	private Integer isRegister;
    /**
     * 客户注册时间
     */
	@TableField("register_time")
	private Date registerTime;
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

	public Integer getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(Integer isRegister) {
		this.isRegister = isRegister;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
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
