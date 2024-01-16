package com.gremiofacil.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gremiofacil.dto.LoginDTO;

@Service
public class LoginService {

	@Autowired
	private TokenService tokenService;
	
	public String login(LoginDTO loginDTO) throws Exception {
		
		return tokenService.login(loginDTO);
	}
	
	public UserPrincipal getUserPrincal() {
		UserPrincipal usuarioContex = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		usuarioContex.clearPassword();
		return usuarioContex;
	}
}
