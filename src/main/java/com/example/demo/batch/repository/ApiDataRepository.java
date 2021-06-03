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
	 * 有志APIDATA登録
	 * 
	 * @param arrayFreeApiDate API取得データ
	 */
	public void insertFreeApiData(@Param("param") List<FreeApiDataBean> arrayFreeApiDate);

	/**
	 * 有志APIDATA登録(前日・当日分登録)
	 * 
	 * @param itemList API取得データ
	 */
	public void insertFreeApiDataDay(@Param("param") List<FreeApiDataBean> arrayFreeApiDate);

	/**
	 * 病床API登録
	 * 
	 * @param sickBedApiDataList API取得データ
	 */
	public void insertSickBedApiData(@Param("param") List<SickBedApiDataBean> sickBedApiDataList);

	/**
	 * 有志APIDATA登録(前日・当日分登録)前Check
	 * 
	 * @param arrayFreeApiDate
	 * @return
	 */
	public Integer selectCheckApiCabnetData();

	/**
	 * 有志APIDATA登録(前日・当日分登録)前Check
	 * 
	 * @param arrayFreeApiDate
	 * @return
	 */
	public Integer selectCheckApiCabnetData(@Param("param") String caseDate);

	/**
	 * 最も古い日付のレコードを削除
	 * 
	 */
	public void deleteTwoDaysAgoData();
}
