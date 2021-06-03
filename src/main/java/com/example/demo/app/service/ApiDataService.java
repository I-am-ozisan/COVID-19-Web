package com.example.demo.app.service;

import com.example.demo.app.bean.ApiDataBean;

public interface ApiDataService {

	/**
	 * 初期表示
	 * 
	 * @return 画面情報
	 */
	public ApiDataBean init();

	/**
	 * 検索
	 * 
	 * @param searchName 検索都道府県
	 * @return 都道府県詳細情報
	 */
	public ApiDataBean search(String searchName);
}
