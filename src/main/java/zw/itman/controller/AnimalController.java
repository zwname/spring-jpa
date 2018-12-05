package zw.itman.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zw.itman.service.AnimalService;

/**
* @author: zhengwei E-mail:613295775@qq.com
* @version: 创建时间：2018年12月4日 下午4:04:37
* @description:
*/
@RestController
public class AnimalController {
	
	@Autowired
	private AnimalService service;
	
	@RequestMapping("/page")
	public Map showPage() {
		Map page = service.showPage();
		return page;
		
	}

}
