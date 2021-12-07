package com.example.demo.app.bean;

import java.util.List;

import lombok.Data;

@Data
public class ApiDataBean {

	/**
	 * 全国総感染者・総退院者・総死亡者数
	 */
	private ApiNationwideDataListBean apiNationwideDataListBean;

	/**
	 * 各都道府県総感染者数
	 */
	private ApiPrefectureDataListBean apiPrefecturesDataListBean;

	/**
	 * 都道府県詳細情報
	 */
	private ApiPrefecturesDetailDataListBean apiPrefecturesDetailListData;

	/**
	 * News情報
	 */
	private ApiNewsData apiNewsData;

	/**
	 * SELECTBOX情報
	 */
	private List<String> prefectureSelectBoxList;
}
