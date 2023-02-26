package com.Tareas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Tareas.data.UserRepository;
import com.Tareas.modelo.Usuario;




@Service
public class UsuarioRepositoryUserDetailsService implements UserDetailsService {

	private UserRepository usuarioRepo;

	@Autowired
	  public UsuarioRepositoryUserDetailsService(UserRepository userRepo) {
	    this.usuarioRepo = userRepo;
	  }
	
	@Override
	  public UserDetails loadUserByUsername(String username)
	      throws UsernameNotFoundException {
	    Usuario unUsuario = usuarioRepo.findByUsername(username);
	    if (unUsuario != null) {
	      return unUsuario;
	    }
	    throw new UsernameNotFoundException(
	                    "Usuario '" + username + "' no encontrado");
	  }
}
