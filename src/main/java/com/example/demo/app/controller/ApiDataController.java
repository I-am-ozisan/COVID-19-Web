package com.example.demo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.app.bean.ApiDataBean;
import com.example.demo.app.service.ApiDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/covid-19")
public class ApiDataController {

	@Autowired
	private ApiDataService apiDataService;

	/**
	 * 初期表示
	 * 
	 * @param model
	 * @return 画面情報
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(Model model) throws JsonProcessingException {

		ApiDataBean apiDataBean = apiDataService.init();

		model.addAttribute("apiDataBean", this.getJsonString(apiDataBean));
		model.addAttribute("apiDataBeanModel", apiDataBean);

		return "index";
	}

	/**
	 * 検索
	 * 
	 * @param model
	 * @return 検索結果
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String search(Model model, @RequestParam(name = "userName", required = false) String searchName)
			throws JsonMappingException, JsonProcessingException {

		ApiDataBean apiDataBean = apiDataService.search(searchName);

		return this.getJsonString(apiDataBean);
	}

	/**
	 * Json変換
	 * 
	 * @param object
	 * @return json文字列
	 * @throws JsonProcessingException
	 */
	private String getJsonString(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(object);
		return json;
	}
}
