package com.gremiofacil.controllers;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gremiofacil.dto.UsuarioDTO;
import com.gremiofacil.service.UsuarioService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/testes")
public class TesteController {
	
	@Autowired
	private UsuarioService usuarioService;
	
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public ResponseEntity testes(HttpServletResponse response) {
    	UsuarioDTO dto = new UsuarioDTO();
    	
    	dto.setNome("Administrador");
    	dto.setLogin("admin");
    	dto.setSenha("1234");
    	dto.setEmail("email@email.com");
    	
    	usuarioService.cadastrarUsuarioMaster(dto);

        return new ResponseEntity("Deu tudo certo! servidor em pé ", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/01", method = RequestMethod.GET)
    public ResponseEntity testes1(HttpServletResponse response) {
    	Locale locale = new Locale("pt", "BR");
    	 ResourceBundle messages = ResourceBundle.getBundle("i18n/messages_pt_BR.properties", locale);

         // Recupere e exiba uma mensagem com um parâmetro
         String greeting = messages.getString("greeting");
         String name = "John";
         System.out.println();
    	
    	return new ResponseEntity(MessageFormat.format(greeting, name), HttpStatus.OK);
    }
}
	