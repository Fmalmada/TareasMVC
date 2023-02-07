package com.Tareas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.Tareas.data.TareasRepository;
import com.Tareas.modelo.Tarea;

@Controller

public class TareasController {
	
	@Autowired
	private TareasRepository tareasRepo;
	
	ModelAndView modelo;

	public TareasController(TareasRepository unRepo) {
		this.tareasRepo = unRepo;
	}
	
	@GetMapping("/")
	public ModelAndView tareas(Tarea unaTarea) {
		modelo = new ModelAndView("listaForm");
		modelo.addObject("tareas", tareasRepo.findAll());
		modelo.addObject("prioridades", Tarea.Prioridad.values());
		modelo.addObject("tarea", unaTarea);
		return modelo;
	}
	
	@PostMapping("/")
	public ModelAndView agregarTarea(@Valid @ModelAttribute("tarea") Tarea nuevaTarea, BindingResult resultados, Errors errores) {
		if (errores.hasErrors()) {
			ModelAndView nuevoModelo = new ModelAndView("listaForm", resultados.getModel());
			nuevoModelo.addObject("tareas", tareasRepo.findAll());
			nuevoModelo.addObject("prioridades", Tarea.Prioridad.values());
			return nuevoModelo;
		}
		tareasRepo.save(nuevaTarea);
		return tareas(new Tarea());
	}
		
}


