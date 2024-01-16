package com.gremiofacil.model;

import java.time.LocalDateTime;
import java.util.Set;

import com.gremiofacil.dto.UsuarioDTO;
import com.gremiofacil.enums.Cargo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", nullable = false)
	private Long id;

	@Column(name = "nome", nullable = false, length = 45)
	private String nome;

	@Column(name = "login", unique = true, nullable = false, length = 30)
	private String login;

	@Column(name = "senha", nullable = false)
	private String senha;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "dt_criacao")
	private LocalDateTime dataCriacao;

	@Column(name = "dt_ultimo_login")
	private LocalDateTime dataUltimoLogin;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_usuario_role", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> roles;

	public Usuario(UsuarioDTO dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.login = dto.getLogin();
		this.senha = dto.getSenha();
		this.email = dto.getEmail();
		this.dataCriacao = dto.getDataCriacao();
		this.dataUltimoLogin = dto.getDataUltimoLogin();
//		this.roles = dto.getRoles();
	}

}
