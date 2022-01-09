package com.example.demo.batch.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SickBedApiUpdateDateBean {
	/**
	 * アップロード日付
	 */
	@JsonProperty("更新日")
	private String updateDate;

	/**
	 * 出典URL
	 */
	@JsonProperty("出典")
	private String excelUrl;
}
