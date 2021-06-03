package com.example.demo.app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.app.bean.ApiNationwideData;
import com.example.demo.app.bean.ApiPrefecturesData;
import com.example.demo.app.bean.ApiPrefecturesDetailData;

@Repository
@Mapper
public interface ApiDataSelectRepository {

	/**
	 * 全国の感染者数・退院者数・死者数を取得
	 * 
	 * @return 全国の感染者数・退院者数・死者数
	 */
	public ApiNationwideData getSelectNationwideData();

	/**
	 * 各都道府県の感染者数を取得
	 * 
	 * @return 各都道府県の感染者数
	 */
	public List<ApiPrefecturesData> getSelectPrefecturesData();

	/**
	 * 検索した都道府県の詳細情報を取得
	 * 
	 * @return 検索した都道府県の詳細情報
	 */
	public ApiPrefecturesDetailData getSelectPrefecturesDetailData(@Param("param") String searchName);

	/**
	 * SelectBoxの情報を取得。
	 */
	public List<String> getSelectBoxData();

	/**
	 * 不要データ削除処理
	 */
	public void deleteTwoDaysAgoData();
}
