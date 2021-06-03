package com.example.demo.batch.tasklet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		this.insertCabinetApiData();
		this.insertSickBedDate();
		System.out.println("complete");
		return RepeatStatus.FINISHED;

	}

	/**
	 * 有志APIData取得
	 */
	private void insertFreeApiData() {
		RestTemplate restTemplate = new RestTemplate();
		// API取得
		List<FreeApiDataBean> arrayFreeApiDate = Arrays
				.asList(restTemplate.getForObject(urlFreeApi, FreeApiDataBean[].class));
		// Insert実施
		repository.insertFreeApiData(arrayFreeApiDate);
	}

	/**
	 * 有志APIData取得(当日・前日登録)
	 */
	private void insertCabinetApiData() {

		RestTemplate restTemplate = new RestTemplate();
		// Api取得
		List<FreeApiDataBean> arrayFreeApiDate = Arrays
				.asList(restTemplate.getForObject(urlFreeApi, FreeApiDataBean[].class));

		Integer deleteCheckCount = repository.selectCheckApiCabnetData();
		Integer insertCheckCount = repository
				.selectCheckApiCabnetData(arrayFreeApiDate.get(1).getLastUpdates().getCasesDate());
		if (deleteCheckCount >= 95) {
			// Insert実施
			repository.deleteTwoDaysAgoData();
		}
		if (insertCheckCount == 0) {
			repository.insertFreeApiDataDay(arrayFreeApiDate);
		}
	}

	/**
	 * 病床APIData取得
	 */
	private void insertSickBedDate() {
		RestTemplate restTemplate = new RestTemplate();
		// APi取得
		List<SickBedApiDataBean> sickBedApiDataList = this
				.replacePercent(Arrays.asList(restTemplate.getForObject(urlSickBedApi, SickBedApiDataBean[].class)));
		// Insert実施
		repository.insertSickBedApiData(sickBedApiDataList);
	}

	/**
	 * Jsonのパーセントを除去する。
	 * 
	 * @param array 病床APIデータ
	 * @return 病床APIデータ(編集済み)
	 */
	private List<SickBedApiDataBean> replacePercent(List<SickBedApiDataBean> array) {
		List<SickBedApiDataBean> sickBedApiDataList = new ArrayList<>();
		for (SickBedApiDataBean instance : array) {

			SickBedApiDataBean sickBedApiDataBean = new SickBedApiDataBean();
			sickBedApiDataBean.setNameJa(instance.getNameJa());
			sickBedApiDataBean.setAcceptHospitalBed(instance.getAcceptHospitalBed());
			sickBedApiDataBean.setHospitalBedUseRate(instance.getHospitalBedUseRate().replace("%", ""));
			sickBedApiDataBean.setAcceptInn(instance.getAcceptInn());
			sickBedApiDataBean.setAcceptInnUseRate(instance.getAcceptInnUseRate().replace("%", ""));
			sickBedApiDataBean.setUpdateDate(instance.getUpdateDate());

			sickBedApiDataList.add(sickBedApiDataBean);
		}

		return sickBedApiDataList;
	}
}
