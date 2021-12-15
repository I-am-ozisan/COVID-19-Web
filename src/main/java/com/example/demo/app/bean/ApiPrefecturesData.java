package com.example.demo.app.bean;

import lombok.Data;

@Data
public class ApiPrefecturesData {

	/**
	 * 都道府県名
	 */
	private String nameJa;

	/**
	 * 総感染者数
	 */
	private Integer casesCount;

	/**
	 * 地方分類Id
	 */
	private String localId;
}
