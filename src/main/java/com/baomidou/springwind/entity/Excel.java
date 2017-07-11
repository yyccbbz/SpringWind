package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * Excel上传文件
 * </p>
 *
 * @author CuiCan
 * @since 2017-07-11
 */
public class Excel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户ID
     */
	private Long uid;
    /**
     * Excel源文件名
     */
	@TableField("excel_name")
	private String excelName;
    /**
     * Excel服务器文件名
     */
	@TableField("excel_real_name")
	private String excelRealName;
    /**
     * Excel服务器路径
     */
	@TableField("excel_real_path")
	private String excelRealPath;
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

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public String getExcelRealName() {
		return excelRealName;
	}

	public void setExcelRealName(String excelRealName) {
		this.excelRealName = excelRealName;
	}

	public String getExcelRealPath() {
		return excelRealPath;
	}

	public void setExcelRealPath(String excelRealPath) {
		this.excelRealPath = excelRealPath;
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
