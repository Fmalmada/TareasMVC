package com.Tareas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Tareas.data.TareasRepository;
import com.Tareas.modelo.Tarea;

@Controller
public class TareasController {
	
	@Autowired
	private TareasRepository tareasRepo;

	public TareasController(TareasRepository unRepo) {
		this.tareasRepo = unRepo;
	}
	
	@GetMapping("/")
	public ModelAndView tareas() {
		ModelAndView modelo = new ModelAndView("listaForm");
		modelo.addObject("tareas", tareasRepo.findAll());
		modelo.addObject("prioridades", Tarea.Prioridad.values());
		return modelo;
	}
	
	@PostMapping("/")
	public String agregarTarea(Tarea nuevaTarea) {
		tareasRepo.save(nuevaTarea);
		return "redirect:/";
		
	}

}
