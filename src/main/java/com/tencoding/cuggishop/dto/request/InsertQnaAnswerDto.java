package com.tencoding.cuggishop.dto.request;

import lombok.Data;

@Data
public class InsertQnaAnswerDto {
	private int qnaId;
	private String title;
	private String content;
}
