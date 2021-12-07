package com.example.demo.batch.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.batch.bean.FreeApiDataBean;
import com.example.demo.batch.bean.SickBedApiDataBean;

@Repository
@Mapper
public interface ApiDataRepository {

	/**
	 * 総感染者etc情報DB登録
	 * 
	 * @param arrayFreeApiDate API取得データ
	 */
	public void insertFreeApiData(@Param("param") List<FreeApiDataBean> arrayFreeApiDate);

	/**
	 * 当日感染者情報DB登録
	 * 
	 * @param itemList API取得データ
	 */
	public void insertFreeApiDataDay(@Param("param") List<FreeApiDataBean> arrayFreeApiDate);

	/**
	 * 前々日レコード既存データチェック
	 * 
	 * @param arrayFreeApiDate
	 * @return
	 */
	public Integer selectCheckData();

	/**
	 * 当日分レコード既存データチェック
	 * 
	 * @param arrayFreeApiDate
	 * @return
	 */
	public Integer selectCheckData(@Param("param") String caseDate);

	/**
	 * 病床情報DB登録
	 * 
	 * @param sickBedApiDataList API取得データ
	 */
	public void insertSickBedApiData(@Param("param") List<SickBedApiDataBean> sickBedApiDataList);

	/**
	 * 最古日付レコード削除
	 */
	public void deleteTwoDaysAgoData();
}
