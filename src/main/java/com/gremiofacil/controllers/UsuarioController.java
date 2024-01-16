package com.gremiofacil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gremiofacil.dto.UsuarioDTO;
import com.gremiofacil.exceptions.ExceptionPersonalizada;
import com.gremiofacil.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ResponseEntity cadastro(@RequestBody UsuarioDTO dto) {
		try {
			usuarioService.cadastrarUsuario(dto);
			return new ResponseEntity(HttpStatus.OK);
		} catch (ExceptionPersonalizada e) {
			return new ResponseEntity(e.getExceptionDTO(), HttpStatus.BAD_REQUEST);
		}
	}

}
