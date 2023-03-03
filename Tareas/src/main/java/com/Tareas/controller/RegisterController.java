package com.Tareas.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Tareas.data.UserRepository;
import com.Tareas.modelo.RegistroForm;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	private UserRepository repoUsuarios;
	private PasswordEncoder passwordEncoder;
	
	public RegisterController(UserRepository unRepo, PasswordEncoder unEncoder) {
		this.repoUsuarios = unRepo;
		this.passwordEncoder = unEncoder;
	}
	
	@GetMapping
	public String registroFormulario() {
		return "registro";
	}
	
	@PostMapping
	public String procesarRegistro(RegistroForm formularioRegistro) {
		repoUsuarios.save(formularioRegistro.toUsuario(passwordEncoder));
		return "redirect:/login";
	}

}
