package com.gremiofacil.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gremiofacil.service.UsuarioService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter{

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token;
		
		var authorizationHeader = request.getHeader("Authorization");
	
		if(authorizationHeader != null && !authorizationHeader.equals("Basic Og==")) {
			token = authorizationHeader.replace("Bearer", "").strip();
			var subject = this.tokenService.getSubject(token); //nome de usuario
		
			var usuario = new UserPrincipal(usuarioService.getUsuarioByLogin(subject));
		
			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		
			//setando dados do usuário e as pemissões no contexto de login
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			/*   
			 * //pegando usuario autenticado 
			 * 
			 * import com.gestor.app.model.Usuario;
			 * 
			Usuario usuarioContex = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			*
			*/
		}
		
		filterChain.doFilter(request, response);
	}

}
