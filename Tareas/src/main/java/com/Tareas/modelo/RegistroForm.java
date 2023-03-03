package com.Tareas.modelo;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class RegistroForm {
	
	private String username;
	private String password;
	
	
	public Usuario toUsuario(PasswordEncoder unEncoder) {
		Usuario  usuarioNuevo = new Usuario(username, unEncoder.encode(password));
		return usuarioNuevo;
	}

}
