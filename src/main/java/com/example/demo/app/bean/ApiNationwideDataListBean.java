package com.example.demo.app.bean;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ApiNationwideDataListBean {

	/**
	 * 全国感染者・退院者・死亡者
	 */
	private List<Integer> apiNationwideDataList;

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
