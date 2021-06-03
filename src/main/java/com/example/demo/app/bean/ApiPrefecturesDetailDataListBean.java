package com.example.demo.app.bean;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ApiPrefecturesDetailDataListBean {

	/**
	 * 検索都道府県
	 */
	private String nameJa;

	/**
	 * 今日の新規感染者数
	 */
	private int todayCasesCount;

	/**
	 * 総感染者数・死者・退院者数
	 */
	List<Integer> otherDataList;

	/**
	 * 総病床数・使用可病床数
	 */
	List<Integer> hospitalBedCountList;

	/**
	 * 総宿泊部屋数・使用可部屋数
	 */
	List<Integer> innCountList;

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
