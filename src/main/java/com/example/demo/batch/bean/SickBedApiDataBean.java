package com.example.demo.batch.bean;

import lombok.Data;

@Data
public class SickBedApiDataBean {

	/**
	 * 都道府県名
	 */
	private String nameJa;

	/**
	 * 入院患者受入確保病床
	 */
	private int acceptHospitalBed;

	/**
	 * 入院患者病床使用率
	 */
	private int hospitalBedUseRate;

	/**
	 * 宿泊施設受入可能室数
	 */
	private int acceptInn;

	/**
	 * 宿泊療養施設居室使用率
	 */
	private int acceptInnUseRate;

	/**
	 * アップロード日付
	 */
	private String updateDate;

}
