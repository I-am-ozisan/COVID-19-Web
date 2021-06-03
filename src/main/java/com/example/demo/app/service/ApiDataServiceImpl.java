package com.example.demo.app.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.app.bean.ApiDataBean;
import com.example.demo.app.bean.ApiNationwideData;
import com.example.demo.app.bean.ApiNationwideDataListBean;
import com.example.demo.app.bean.ApiNewsData;
import com.example.demo.app.bean.ApiPrefectureDataListBean;
import com.example.demo.app.bean.ApiPrefecturesData;
import com.example.demo.app.bean.ApiPrefecturesDetailData;
import com.example.demo.app.bean.ApiPrefecturesDetailDataListBean;
import com.example.demo.app.repository.ApiDataSelectRepository;

@Service
public class ApiDataServiceImpl implements ApiDataService {

	/**
	 * Repository
	 */
	@Autowired
	ApiDataSelectRepository repository;

	/**
	 * NewsApiURL
	 */
	private final String newsApiUrl = "https://news.google.com/rss/search?q=コロナ&hl=ja&gl=JP&ceid=JP:ja";

	@Override
	public ApiDataBean init() {

		String searchName = "北海道";

		ApiDataBean apiDataBean = new ApiDataBean();
		apiDataBean
				.setApiNationwideDataListBean(this.getProcessingNationWideData(repository.getSelectNationwideData()));
		apiDataBean.setApiPrefecturesDataListBean(this.getProcessingData(repository.getSelectPrefecturesData()));
		apiDataBean.setApiPrefecturesDetailListData(
				this.getProcessingPrefecturesData(this.getApiPrefecturesDetailData(searchName)));
		apiDataBean.setApiNewsData(this.getNewsApiInfo());
		apiDataBean.setPrefectureSelectBoxList(repository.getSelectBoxData());

		this.getNewsApiInfo();
		return apiDataBean;
	}

	@Override
	public ApiDataBean search(String searchName) {

		ApiDataBean apiDataBean = new ApiDataBean();

		apiDataBean.setApiPrefecturesDetailListData(
				this.getProcessingPrefecturesData(this.getApiPrefecturesDetailData(searchName)));

		return apiDataBean;
	}

	/**
	 * オブジェクトを配列に変換(全国情報)
	 * 
	 * @param apiNationwideData
	 * @return
	 */
	private ApiNationwideDataListBean getProcessingNationWideData(ApiNationwideData apiNationwideData) {

		ApiNationwideDataListBean apiNationwideDataListBean = new ApiNationwideDataListBean();
		List<Integer> array = new ArrayList<>();
		array.add(apiNationwideData.getCasesCount());
		array.add(apiNationwideData.getDischargeCount());
		array.add(apiNationwideData.getDeathCount());

		apiNationwideDataListBean.setApiNationwideDataList(array);
		apiNationwideDataListBean.setCasesUpdataDate(apiNationwideData.getCasesUpdataDate());
		apiNationwideDataListBean.setDischargeUpdataDate(apiNationwideData.getDischargeUpdataDate());
		apiNationwideDataListBean.setDeathUpdataDate(apiNationwideData.getDeathUpdataDate());

		return apiNationwideDataListBean;
	}

	/**
	 * オブジェクトを配列に変換(都道府県情報)
	 * 
	 * @param apiPrefecturesDatasList 都道府県情報
	 * @return 分割後情報
	 */
	private ApiPrefectureDataListBean getProcessingData(List<ApiPrefecturesData> apiPrefecturesDatasList) {
		// 都道府県を地方ごとに分割・javascript用に加工。
		ApiPrefectureDataListBean apiPrefectureDataListBean = new ApiPrefectureDataListBean();
		List<Integer> hokkaidoTohokuCountList = new ArrayList<>();
		List<String> hokkaidoTohokuNameList = new ArrayList<>();
		List<Integer> kantoCountList = new ArrayList<>();
		List<String> kantoNameList = new ArrayList<>();
		List<Integer> chubuCountList = new ArrayList<>();
		List<String> chubuNameList = new ArrayList<>();
		List<Integer> kinkiCountList = new ArrayList<>();
		List<String> kinkiNameList = new ArrayList<>();
		List<Integer> chugokushikokuCountList = new ArrayList<>();
		List<String> chugokushikokuNameList = new ArrayList<>();
		List<Integer> kyushuCountList = new ArrayList<>();
		List<String> kyushuNameList = new ArrayList<>();

		for (int i = 0; i < apiPrefecturesDatasList.size(); i++) {

			if (i <= 0 || i <= 6) {
				// 北海道・東北地方
				hokkaidoTohokuCountList.add(apiPrefecturesDatasList.get(i).getCasesCount());
				hokkaidoTohokuNameList.add(apiPrefecturesDatasList.get(i).getNameJa());
			} else if (i <= 13) {
				// 関東地方
				kantoCountList.add(apiPrefecturesDatasList.get(i).getCasesCount());
				kantoNameList.add(apiPrefecturesDatasList.get(i).getNameJa());
			} else if (i <= 22) {
				// 中部地方
				chubuCountList.add(apiPrefecturesDatasList.get(i).getCasesCount());
				chubuNameList.add(apiPrefecturesDatasList.get(i).getNameJa());
			} else if (i <= 29) {
				// 近畿地方
				kinkiCountList.add(apiPrefecturesDatasList.get(i).getCasesCount());
				kinkiNameList.add(apiPrefecturesDatasList.get(i).getNameJa());
			} else if (i <= 38) {
				// 中国・四国地方
				chugokushikokuCountList.add(apiPrefecturesDatasList.get(i).getCasesCount());
				chugokushikokuNameList.add(apiPrefecturesDatasList.get(i).getNameJa());
			} else {
				// 九州・沖縄
				kyushuCountList.add(apiPrefecturesDatasList.get(i).getCasesCount());
				kyushuNameList.add(apiPrefecturesDatasList.get(i).getNameJa());
			}
		}

		apiPrefectureDataListBean.setHokkaidoTohokuCountList(hokkaidoTohokuCountList);
		apiPrefectureDataListBean.setHokkaidoTohokuNameList(hokkaidoTohokuNameList);
		apiPrefectureDataListBean.setKantoCountList(kantoCountList);
		apiPrefectureDataListBean.setKantoNameList(kantoNameList);
		apiPrefectureDataListBean.setChubuCountList(chubuCountList);
		apiPrefectureDataListBean.setChubuNameList(chubuNameList);
		apiPrefectureDataListBean.setKinkiCountList(kinkiCountList);
		apiPrefectureDataListBean.setKinkiNameList(kinkiNameList);
		apiPrefectureDataListBean.setChugokushikokuCountList(chugokushikokuCountList);
		apiPrefectureDataListBean.setChugokushikokuNameList(chugokushikokuNameList);
		apiPrefectureDataListBean.setKyushuCountList(kyushuCountList);
		apiPrefectureDataListBean.setKyushuNameList(kyushuNameList);

		return apiPrefectureDataListBean;

	}

	/**
	 * オブジェクトを配列に変換(検索都道府県情報)
	 * 
	 * @param apiPrefecturesDetailData
	 * @return
	 */
	private ApiPrefecturesDetailDataListBean getProcessingPrefecturesData(
			ApiPrefecturesDetailData apiPrefecturesDetailData) {

		ApiPrefecturesDetailDataListBean apiPrefecturesDetailDataListBean = new ApiPrefecturesDetailDataListBean();
		List<Integer> otherDataList = new ArrayList<>();
		List<Integer> hospitalBedCountList = new ArrayList<>();
		List<Integer> innCountList = new ArrayList<>();

		otherDataList.add(apiPrefecturesDetailData.getCasesCount());
		otherDataList.add(apiPrefecturesDetailData.getDischargeCount());
		otherDataList.add(apiPrefecturesDetailData.getDeathCount());

		hospitalBedCountList.add(apiPrefecturesDetailData.getHospitalbedCount());
		hospitalBedCountList.add(apiPrefecturesDetailData.getNotAcceptHospitalbedCount());

		innCountList.add(apiPrefecturesDetailData.getInnCount());
		innCountList.add(apiPrefecturesDetailData.getNotAcceptInnCount());

		apiPrefecturesDetailDataListBean.setNameJa(apiPrefecturesDetailData.getNameJa());
		apiPrefecturesDetailDataListBean.setTodayCasesCount(apiPrefecturesDetailData.getTodayCasesCount());
		apiPrefecturesDetailDataListBean.setOtherDataList(otherDataList);
		apiPrefecturesDetailDataListBean.setHospitalBedCountList(hospitalBedCountList);
		apiPrefecturesDetailDataListBean.setInnCountList(innCountList);
		apiPrefecturesDetailDataListBean.setTodayCasesUpdateDate(apiPrefecturesDetailData.getTodayCasesUpdateDate());
		apiPrefecturesDetailDataListBean.setCasesUpdateDate(apiPrefecturesDetailData.getCasesUpdateDate());
		apiPrefecturesDetailDataListBean.setDischargeUpdateDate(apiPrefecturesDetailData.getDischargeUpdateDate());
		apiPrefecturesDetailDataListBean.setDeathsUpdateDate(apiPrefecturesDetailData.getDeathsUpdateDate());
		apiPrefecturesDetailDataListBean.setAcceptUpdateDate(apiPrefecturesDetailData.getAcceptUpdateDate());

		return apiPrefecturesDetailDataListBean;

	}

	/**
	 * 1都道府県の詳細情報を取得
	 * 
	 * @param searchName 検索都道府県
	 * @return 1都道府県詳細情報
	 */
	private ApiPrefecturesDetailData getApiPrefecturesDetailData(String searchName) {
		return repository.getSelectPrefecturesDetailData(searchName);
	}

	/**
	 * newsの情報を取得。
	 */
	private ApiNewsData getNewsApiInfo() {
		RestTemplate restTemplate = new RestTemplate();

		return JAXB.unmarshal(new StringReader(restTemplate.getForObject(newsApiUrl, String.class)), ApiNewsData.class);
	}

}
