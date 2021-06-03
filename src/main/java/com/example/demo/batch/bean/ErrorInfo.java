package com.example.demo.batch.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ErrorInfo {

	/**
	 * エラーフラグ
	 */
	@JsonProperty("errorFlag")
	private String errorFlg;

	/**
	 * エラーコード
	 */
	@JsonProperty("errorCode")
	private String errorCode;

	/**
	 * エラーメッセージ
	 */
	@JsonProperty("errorMessage")
	private String errorMessage;
}
