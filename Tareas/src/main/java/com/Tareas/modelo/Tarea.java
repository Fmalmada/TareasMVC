package com.Tareas.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Tarea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message="Se necesita una prioridad")
	private Prioridad prioridad;
	
	@NotNull
	@NotBlank(message = "Una descripcion es requerida")
	private String descripcion;
	
	private String creadaEn = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	
	private boolean finalizada = false;
	
	public enum Prioridad {
		BAJA, MEDIA, ALTA;
	}

}
