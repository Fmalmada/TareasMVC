package com.Tareas.data;

import org.springframework.data.repository.CrudRepository;

import com.Tareas.modelo.Usuario;

public interface UserRepository extends CrudRepository<Usuario, Long> {

	Usuario findByUsername(String username);
}
