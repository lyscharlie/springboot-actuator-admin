package com.lyscharlie.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AreaVO {

	/**
	 * 地区id
	 */
	@ApiModelProperty(value = "地区id")
	private Long areaId;
	/**
	 * 地区名称
	 */
	@ApiModelProperty(value = "地区名称")
	private String areaName;
	/**
	 * 地区等级：1-国家，2-省，3-市，4-区/县/镇
	 */
	@ApiModelProperty(value = "地区等级：1-国家，2-省，3-市，4-区/县/镇")
	private Integer areaLevel;
	/**
	 * 地区信息
	 */
	@ApiModelProperty(value = "地区信息")
	private String areaInfo;
	/**
	 * 上级地区id
	 */
	@ApiModelProperty(value = "上级地区id")
	private Long parentId;
	/**
	 * 状态：1-启用，2-停用
	 */
	@ApiModelProperty(value = "状态：1-启用，2-停用")
	private Integer status;

}
