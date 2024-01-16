package com.gremiofacil.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ExceptionPersonalizadaDTO {

	private String status;
	private String title;
	private String detail;
	
	public ExceptionPersonalizadaDTO(HttpStatus status, String title, String detail) {
		super();
		this.status = Integer.toString(status.value()) + " - " + status.getReasonPhrase();
		this.title = title;
		this.detail = detail;
	}
	
	
}
