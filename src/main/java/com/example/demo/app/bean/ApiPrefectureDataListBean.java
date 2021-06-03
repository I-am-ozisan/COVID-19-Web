package com.example.demo.app.bean;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ApiPrefectureDataListBean {

	/**
	 * 北海道・東北(感染者数)
	 */
	private List<Integer> hokkaidoTohokuCountList;

	/**
	 * 北海道・東北(都道府県名)
	 */
	private List<String> hokkaidoTohokuNameList;

	/**
	 * 関東(感染者数)
	 */
	private List<Integer> kantoCountList;

	/**
	 * 関東(都道府県名)
	 */
	private List<String> kantoNameList;

	/**
	 * 中部(感染者数)
	 */
	private List<Integer> chubuCountList;

	/**
	 * 中部(都道府県名)
	 */
	private List<String> chubuNameList;

	/**
	 * 近畿(感染者数)
	 */
	private List<Integer> kinkiCountList;

	/**
	 * 近畿(都道府県名)
	 */
	private List<String> kinkiNameList;

	/**
	 * 中国・四国(感染者数)
	 */
	private List<Integer> chugokushikokuCountList;

	/**
	 * 中国・四国(都道府県名)
	 */
	private List<String> chugokushikokuNameList;

	/**
	 * 九州(感染者数)
	 */
	private List<Integer> kyushuCountList;

	/**
	 * 九州(都道府県名)
	 */
	private List<String> kyushuNameList;

	/**
	 * 更新日
	 */
	private LocalDate updateDate;
}
