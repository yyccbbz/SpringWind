package com.baomidou.springwind.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;


/**
 * <p>
 * Excel文件类
 * </p>
 *
 * @author CuiCan
 * @since 2017-04-12
 */
public class Excel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
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
     * 更新时间
     */
	private Date mtime;
    /**
     * 创建时间
     */
	private Date ctime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
