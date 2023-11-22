package com.tencoding.cuggishop.handler;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tencoding.cuggishop.handler.exception.CustomRestfulException;
import com.tencoding.cuggishop.handler.exception.UnAuthorizedException;
import com.tencoding.cuggishop.handler.exception.UnSignUpException;

/**
 * 예외 발생시 (Json,Xml)
 * 데이터를 가공해서 내려 줄 수 있다.
 *
 */

@RestControllerAdvice // IoC 대상 + App 대상
@Order(1)
public class MyRestfullExceptionHandler {

	@ExceptionHandler(CustomRestfulException.class)
	public String basicException(CustomRestfulException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('" + e.getMessage() + "');");
		sb.append("history.back();");
		sb.append("</script>");
		return sb.toString();
	}

	// 로그인을 하지 않았을 경우 예외 처리
	@ExceptionHandler(UnAuthorizedException.class)
	public String notLoginException(UnAuthorizedException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('" + e.getMessage() + "');");
		sb.append("location.href='/user/signIn';");
		sb.append("</script>");
		return sb.toString();
	}

	@ExceptionHandler(UnSignUpException.class)
	public String notSignUpException(UnSignUpException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('" + e.getMessage() + "');");
		sb.append("location.href='/user/signUp';");
		sb.append("</script>");
		return sb.toString();
	}
}