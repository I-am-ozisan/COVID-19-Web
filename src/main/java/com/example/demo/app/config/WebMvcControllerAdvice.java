package com.example.demo.app.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class WebMvcControllerAdvice {

	/*
	 * This method changes empty character to null
	 */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		// dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
