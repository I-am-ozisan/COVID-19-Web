package com.example.demo.batch.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemList {

	/**
	 * 日付
	 */
	@JsonProperty("date")
	private String date;

	/**
	 * 都道府県名
	 */
	@JsonProperty("name_jp")
	private String name_jp;

	/**
	 * 陽性者数
	 */
	@JsonProperty("npatients")
	private String inpatients;
}
