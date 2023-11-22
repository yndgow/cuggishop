package com.tencoding.cuggishop.service;

import javax.annotation.Resource;

import com.tencoding.cuggishop.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tencoding.cuggishop.dto.request.QnaFormRequestDto;
import com.tencoding.cuggishop.dto.response.QnaPersonResponseDto;
import com.tencoding.cuggishop.handler.exception.CustomRestfulException;
import com.tencoding.cuggishop.repository.interfaces.PersonRepository;
import com.tencoding.cuggishop.repository.interfaces.QnaRepository;
import com.tencoding.cuggishop.util.Mail;

@Service
public class QnaService {

	@Autowired
	QnaRepository qnaRepository;

	@Autowired
	PersonRepository personRepository;

	@Resource(name = "mail")
	private Mail mail;

	public QnaPersonResponseDto readPerson(int id) {
		QnaPersonResponseDto qnaPersonResponseDto = new QnaPersonResponseDto(personRepository.findByUserId(id));
		return qnaPersonResponseDto;
	}

	public void qnaInsert(QnaFormRequestDto qnaFormRequestDto) {

		int result = qnaRepository.insert(qnaFormRequestDto);
		mail.sendSimpleEmail(qnaFormRequestDto);
		if (result != 1) {
			throw new CustomRestfulException("문의사항 기입이 되지 않았습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
