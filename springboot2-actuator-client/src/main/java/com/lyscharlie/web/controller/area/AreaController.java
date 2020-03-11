package com.lyscharlie.web.controller.area;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lyscharlie.biz.entity.AreaDO;
import com.lyscharlie.biz.service.AreaService;
import com.lyscharlie.web.vo.AreaVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "area")
@RestController
@RequestMapping(value = "area")
@Slf4j
public class AreaController {

	@Autowired
	private AreaService areaService;

	@ApiOperation(value = "中国省份列表")
	@RequestMapping(value = "chinaList.php", method = RequestMethod.GET)
	public List<AreaVO> queryListForChina() {
		List<AreaVO> resultList = new ArrayList<>();
		List<AreaDO> list = this.areaService.list(Wrappers.<AreaDO>lambdaQuery().eq(AreaDO::getParentId, 1));
		for (AreaDO areaDO : list) {
			AreaVO vo = new AreaVO();
			BeanUtils.copyProperties(areaDO, vo);
			resultList.add(vo);
		}
		return resultList;
	}

}
