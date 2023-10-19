package com.tencoding.cuggishop.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
// JSON 형식의 코딩 컨벤션이 스네이크 케이스를 자바 객체 카멜 노테이션으로 할당
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OAuthToken {
	private String accessToken;
	private String tokenType;
	private String refreshToken;
	private String scope;
	private Integer expiresIn;

}