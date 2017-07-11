package com.baomidou.springwind.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author CuiCan
 * @since 2017-07-11
 */
public class Privilege implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 上级ID
     */
	private Long pid;
    /**
     * 标题
     */
	private String title;
    /**
     * 类型 0、菜单 1、功能
     */
	private Integer type;
    /**
     * 状态 1、正常 0、禁用
     */
	private Integer state;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 地址
     */
	private String url;
    /**
     * 权限编码
     */
	@TableField("perm_code")
	private String permCode;
    /**
     * 图标
     */
	private String icon;
    /**
     * 描述
     */
	private String description;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermCode() {
		return permCode;
	}

	public void setPermCode(String permCode) {
		this.permCode = permCode;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
