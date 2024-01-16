package com.gremiofacil.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ExceptionPersonalizada extends Exception {

	private static final long serialVersionUID = 5118917668261676338L;

	private ExceptionPersonalizadaDTO exceptionDTO;

	public ExceptionPersonalizada(HttpStatus status, String title, String detail) {
		ExceptionPersonalizadaDTO dto = new ExceptionPersonalizadaDTO(status, title, detail);
		this.exceptionDTO = dto;
	}
}
