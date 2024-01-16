package com.gremiofacil.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.gremiofacil.model.Role;
import com.gremiofacil.model.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserPrincipal {
	
	private static final long serialVersionUID = 6270973984841812866L;
	private Long id;
	private String username;
	private String name;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Usuario usuario) {
		this.id = usuario.getId();
		this.username = usuario.getLogin();
		this.name = usuario.getNome();
		this.password = usuario.getSenha();

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		List<Role> userRoles = new ArrayList<>(usuario.getRoles());
		
		userRoles.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getNome())));
		});
		
		this.authorities = authorities;
	}
	
	public void clearPassword() {
		this.password = "";
	}

}
