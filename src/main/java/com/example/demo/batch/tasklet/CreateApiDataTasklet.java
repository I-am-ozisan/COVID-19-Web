package com.example.demo.batch.tasklet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.batch.bean.FreeApiDataBean;
import com.example.demo.batch.bean.SickBedApiDataBean;
import com.example.demo.batch.bean.SickBedApiUpdateDateBean;
import com.example.demo.batch.repository.ApiDataRepository;

@Component
@StepScope
public class CreateApiDataTasklet implements Tasklet {

	/**
	 * 有志API_URL
	 */
	private final String urlFreeApi = "https://covid19-japan-web-api.vercel.app/api/v1/prefectures";

	/**
	 * 病床API_URL
	 */
	private final String urlSickBedApi = "https://www.stopcovid19.jp/data/covid19japan_beds/latest.json";

	/**
	 * repository
	 */
	@Autowired
	ApiDataRepository repository;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		this.insertFreeApiData();
		this.insertSickBedDate();
		System.out.println("complete");
		return RepeatStatus.FINISHED;

	}

	/**
	 * 各都道府県APIData取得
	 */
	private void insertFreeApiData() {
		RestTemplate restTemplate = new RestTemplate();
		// API取得
		List<FreeApiDataBean> arrayFreeApiDate = Arrays
				.asList(restTemplate.getForObject(urlFreeApi, FreeApiDataBean[].class));
		// Insert実施
		repository.insertFreeApiData(arrayFreeApiDate);
		this.insertTTodayApiDataTbl(arrayFreeApiDate);
	}

	/**
	 * 当日感染者情報DB登録
	 */
	private void insertTTodayApiDataTbl(List<FreeApiDataBean> arrayFreeApiDate) {

		Integer deleteCheckCount = repository.selectCheckData();
		Integer insertCheckCount = repository.selectCheckData(arrayFreeApiDate.get(1).getLastUpdates().getCasesDate());
		if (deleteCheckCount >= 95) {
			repository.deleteTwoDaysAgoData();
		}
		if (insertCheckCount == 0) {
			repository.insertFreeApiDataDay(arrayFreeApiDate);
		}
	}

	/**
	 * 病床APIData取得
	 * 
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws EncryptedDocumentException
	 */
	private void insertSickBedDate() throws EncryptedDocumentException, InvalidFormatException, IOException {
		// Insert実施
		RestTemplate restTemplate = new RestTemplate();
		// APi取得
		List<SickBedApiUpdateDateBean> sickBedApiUpdateDateBeanList = Arrays
				.asList(restTemplate.getForObject(urlSickBedApi, SickBedApiUpdateDateBean[].class));
		// Excelファイルからデータを取得後、DBへ登録
		repository.insertSickBedApiData(this.getSickBedApiDataBeanList(sickBedApiUpdateDateBeanList));
	}

	/**
	 * Excelファイルをダウンロード後、Dtoを作成
	 * 
	 * @param sickBedApiUpdateDateBeanList
	 * @return DB登録情報
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	private List<SickBedApiDataBean> getSickBedApiDataBeanList(
			List<SickBedApiUpdateDateBean> sickBedApiUpdateDateBeanList)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		List<SickBedApiDataBean> sickBedApiDataList = new ArrayList<>();
		// Excelファイル取得
		URL fetchWebsite = new URL(sickBedApiUpdateDateBeanList.get(0).getExcelUrl());
		File file = new File("tmp/SickBedApi.xlsx");
		FileUtils.copyURLToFile(fetchWebsite, file);

		// エクセルファイルへアクセス
		Workbook excel;
		excel = WorkbookFactory.create(new File("tmp/SickBedApi.xlsx"));
		// シート名を指定
		Sheet sheet = excel.getSheet("公表資料");
		// ExcelからBeanに設定
		for (int row = 8; row <= 54; row++) {
			SickBedApiDataBean sickBedApiDataBean = new SickBedApiDataBean();
			sickBedApiDataBean.setNameJa(
					sheet.getRow(row).getCell(0).getStringCellValue().replaceAll("[0-9]", "").replaceAll(" ", ""));
			sickBedApiDataBean.setAcceptHospitalBed((int) sheet.getRow(row).getCell(7).getNumericCellValue());
			sickBedApiDataBean.setHospitalBedUseRate((int) sheet.getRow(row).getCell(9).getNumericCellValue());
			sickBedApiDataBean.setAcceptInn((int) sheet.getRow(row).getCell(23).getNumericCellValue());
			sickBedApiDataBean.setAcceptInnUseRate((int) sheet.getRow(row).getCell(25).getNumericCellValue());
			sickBedApiDataBean.setUpdateDate(sickBedApiUpdateDateBeanList.get(0).getUpdateDate());

			sickBedApiDataList.add(sickBedApiDataBean);
		}

		return sickBedApiDataList;
	}
}
