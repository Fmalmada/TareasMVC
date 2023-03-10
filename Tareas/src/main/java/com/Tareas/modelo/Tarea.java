package com.Tareas.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@NotNull(message = "Una descripción es requerida")
	@NotBlank(message = "Una descripcion es requerida")
	private String descripcion;
	
	private String creadaEn = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	
	private boolean finalizada = false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	public enum Prioridad {
		BAJA, MEDIA, ALTA;
	}

}
