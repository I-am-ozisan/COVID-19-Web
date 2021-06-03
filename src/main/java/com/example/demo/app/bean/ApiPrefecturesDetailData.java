package com.example.demo.app.bean;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ApiPrefecturesDetailData {

	/**
	 * 検索都道府県
	 */
	private String nameJa;

	/**
	 * 今日の新規感染者数
	 */
	private int todayCasesCount;

	/**
	 * 総感染者数
	 */
	private int casesCount;

	/**
	 * 死者数
	 */
	private int deathCount;

	/**
	 * 退院者数
	 */
	private int dischargeCount;

	/**
	 * 総確保受入可病床数
	 */
	private int hospitalbedCount;

	/**
	 * 使用済病床数
	 */
	private int notAcceptHospitalbedCount;

	/**
	 * 総確保宿泊施設部屋数
	 */
	private int innCount;

	/**
	 * 使用済宿泊施設部屋数
	 */
	private int notAcceptInnCount;

	/**
	 * 新規感染者数更新日時
	 */
	private LocalDate todayCasesUpdateDate;

	/**
	 * 総感染者数更新日時
	 */
	private LocalDate casesUpdateDate;

	/**
	 * 退院者数更新日時
	 */
	private LocalDate dischargeUpdateDate;

	/**
	 * 死者数更新日時
	 */
	private LocalDate deathsUpdateDate;

	/**
	 * 病床・宿泊可能数更新日時
	 */
	private LocalDate acceptUpdateDate;

}
