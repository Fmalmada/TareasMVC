package com.Tareas.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.Tareas.modelo.Tarea;

public interface TareasRepository extends PagingAndSortingRepository<Tarea, Long>{

}
