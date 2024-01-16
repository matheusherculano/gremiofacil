package com.gremiofacil.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gremiofacil.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String email;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataUltimoLogin;
	private List<Role> roles;
	
	public static boolean isValidDTO(UsuarioDTO dto) {
        if (dto == null) {
            return false;
        }

        // Verificar se os campos obrigatórios estão vazios ou nulos
        if (dto.getNome() == null || dto.getNome().isEmpty() ||
            dto.getLogin() == null || dto.getLogin().isEmpty() ||
            dto.getSenha() == null || dto.getSenha().isEmpty() ||
            dto.getEmail() == null || dto.getEmail().isEmpty()) {
            return false;
        }

        // Verificar se o campo de e-mail é um endereço de e-mail válido
        if (!isValidEmail(dto.getEmail())) {
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        // Expressão regular para verificar um endereço de e-mail válido
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
