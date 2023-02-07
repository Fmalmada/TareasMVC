package com.Tareas.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Tarea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Prioridad prioridad;
	
	private String descripcion;
	
	private Date creadaEn = new Date();
	
	private boolean finalizada = false;
	
	public enum Prioridad {
		BAJA, MEDIA, ALTA;
	}

}
