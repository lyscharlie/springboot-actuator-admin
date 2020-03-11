package com.lyscharlie.biz.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "lys_user")
public class UserDO {

	/**
	 * 用户id
	 */
	@TableId(value = "", type = IdType.AUTO)
	private Long userId;
	/**
	 * 用户名
	 */
	@TableField(value = "user_name")
	private String userName;
	/**
	 * 用户密码
	 */
	@TableField(value = "password")
	private String password;
	/**
	 * 邮箱
	 */
	@TableField(value = "email")
	private String email;
	/**
	 * 手机号
	 */
	@TableField(value = "mobile")
	private String mobile;
	/**
	 * 推荐人id
	 */
	@TableField(value = "register_refer_id")
	private Long registerReferId;
	/**
	 * 最近一次登录时间
	 */
	@TableField(value = "last_login_time")
	private Date lastLoginTime;
	/**
	 * 是否删除：0-否，1-是
	 */
	@TableField(value = "is_delete")
	@TableLogic
	private Integer isDelete;
	/**
	 * 创建时间
	 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;
	/**
	 * 最后修改时间
	 */
	@TableField(value = "gmt_modified")
	private Date gmtModified;

}
