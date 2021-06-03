package com.example.demo.app.bean;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ApiNationwideData {

	/**
	 * 陽性者数
	 */
	private int casesCount;

	/**
	 * 退院者数
	 */
	private int dischargeCount;

	/**
	 * 死者数
	 */
	private int deathCount;

	/**
	 * 陽性者数更新日時
	 */
	private LocalDate casesUpdataDate;

	/**
	 * 退院者数更新日時
	 */
	private LocalDate dischargeUpdataDate;

	/**
	 * 死者数更新日時
	 */
	private LocalDate deathUpdataDate;
}
