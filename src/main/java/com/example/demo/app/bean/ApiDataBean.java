package com.example.demo.app.bean;

import java.util.List;

import lombok.Data;

@Data
public class ApiDataBean {

	/**
	 * 全国感染者・退院者・死亡者
	 */
	private ApiNationwideDataListBean apiNationwideDataListBean;

	/**
	 * 全都道府県の情報
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
