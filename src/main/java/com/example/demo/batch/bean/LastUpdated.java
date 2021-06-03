package com.example.demo.batch.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LastUpdated {

	/**
	 * 陽性者数
	 */
	@JsonProperty("cases_date")
	String casesDate;

	/**
	 * 死者数
	 */
	@JsonProperty("deaths_date")
	String deathsDate;

	/**
	 * 検査人数
	 */
	@JsonProperty("pcr_date")
	String pcrDate;

	/**
	 * 入院患者数
	 */
	@JsonProperty("hospitalize_date")
	String hospitalizeDate;

	/**
	 * 重傷者数
	 */
	@JsonProperty("severe_date")
	String severeDate;

	/**
	 * 退院者数
	 */
	@JsonProperty("discharge_date")
	String dischargeDate;

	/**
	 * 確認中数
	 */
	@JsonProperty("symptom_confirming_date")
	String symptomConfirmingDate;
}
