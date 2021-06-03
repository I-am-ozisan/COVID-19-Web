package com.example.demo.batch.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CabinetApiDataBean {

	/**
	 * エラー情報
	 */
	@JsonProperty("errorInfo")
	private ErrorInfo errorInfo;

	/**
	 * DATA部
	 */
	@JsonProperty("itemList")
	private List<ItemList> itemList;
}
