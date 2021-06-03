package com.example.demo.batch.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FreeApiDataBean {

	/**
	 * id
	 */
	@JsonProperty("id")
	int id;

	/**
	 * 都道府県名(日本語)
	 */
	@JsonProperty("name_ja")
	String nameJapan;

	/**
	 * 都道府県名(英語)
	 */
	@JsonProperty("name_en")
	String nameEnglish;

	/**
	 * 緯度
	 */
	@JsonProperty("lat")
	int lat;

	/**
	 * 経度
	 */
	@JsonProperty("lng")
	int lng;

	/**
	 * 人口
	 */
	@JsonProperty("population")
	int population;

	/**
	 * アップロード時刻情報
	 */
	@JsonProperty("last_updated")
	LastUpdated lastUpdates;

	/**
	 * 陽性者数
	 */
	@JsonProperty("cases")
	int cases;

	/**
	 * 死者数
	 */
	@JsonProperty("deaths")
	int deaths;

	/**
	 * 検査人数
	 */
	@JsonProperty("pcr")
	int pcr;

	/**
	 * 入院患者数
	 */
	@JsonProperty("hospitalize")
	int hospitalize;

	/*
	 * 重症者数
	 */
	@JsonProperty("severe")
	int severe;

	/**
	 * 退院者数
	 */
	@JsonProperty("discharge")
	int discharge;

	/**
	 * 確認中
	 */
	@JsonProperty("symptomConfirming")
	int symptomConfirming;

}
