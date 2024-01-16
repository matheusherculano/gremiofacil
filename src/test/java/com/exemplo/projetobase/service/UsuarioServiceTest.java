package com.exemplo.projetobase.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gremiofacil.dto.UsuarioDTO;
import com.gremiofacil.exceptions.ExceptionPersonalizada;
import com.gremiofacil.model.Usuario;
import com.gremiofacil.repository.UsuarioRepository;
import com.gremiofacil.service.UsuarioService;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

	@InjectMocks
	UsuarioService usuarioService;

	@Mock
	UsuarioRepository usuarioRepository;

	UsuarioDTO usuarioDTO;

	@BeforeEach
	public void setUp() {
		usuarioDTO = new UsuarioDTO(1L, "Usuário teste", "user.login", "1234", "email@email.com", LocalDateTime.now(), null,
				null);
	}

	@Test
	 void CadastrarUsuarioComSucesso() throws ExceptionPersonalizada {
		Usuario usuario = new Usuario(usuarioDTO);
		 when(usuarioRepository.findByLogin(usuarioDTO.getLogin())).thenReturn(null);
		 when(usuarioRepository.findByEmail(usuarioDTO.getEmail())).thenReturn(null);
		 usuarioService.cadastrarUsuario(usuarioDTO);
	 }
	
	@Test
	void ExceptionVerificarNomeVazio() {
		final ExceptionPersonalizada e = assertThrows(ExceptionPersonalizada.class, ()->{
			usuarioDTO.setNome("");
			usuarioService.cadastrarUsuario(usuarioDTO);
		});
		
		assertThat(e.getExceptionDTO(), notNullValue());
        assertThat(e.getExceptionDTO().getTitle(), is("Faltam dados do usuário, preencha os campos obrigatórios"));
        verifyNoMoreInteractions(usuarioRepository);
	}
	
//	@Test
//	void ExceptionEmailJaExistente() {}
}
