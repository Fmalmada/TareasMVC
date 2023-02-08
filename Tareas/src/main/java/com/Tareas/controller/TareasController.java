package com.Tareas.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

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
	public String agregarTarea(@Valid @ModelAttribute("tarea") Tarea nuevaTarea, BindingResult resultados,
			Errors errores, Model unModelo) {
		if (errores.hasErrors()) {
			unModelo.addAttribute("tareas", tareasRepo.findAll());
			unModelo.addAttribute("prioridades", Tarea.Prioridad.values());
			return "listaForm";
		}
		tareasRepo.save(nuevaTarea);
		return "redirect:/";
	}

	@GetMapping("/editar/{id}")
	public String EditarTarea(@PathVariable("id") Long unaTareaId, Model unModelo) {
		Optional<Tarea> posibleTarea = tareasRepo.findById(unaTareaId);
		if (!posibleTarea.isPresent()) {
			throw new IllegalArgumentException("Tarea: " + unaTareaId + " no encontrada");
		}
		unModelo.addAttribute("tareaAEditar", posibleTarea.get());
		unModelo.addAttribute("tarea", new Tarea());
		unModelo.addAttribute("prioridades", Tarea.Prioridad.values());
		return "editar-tarea";
	}

	@PostMapping("/editar/{id}")
	public String AgregarTareaEditada(@PathVariable("id") Long unaTareaId,
			@Valid @ModelAttribute("tareaAEditar") Tarea tareaAEditar, BindingResult resultados, Model unModelo,
			Errors errores) {

		if (errores.hasErrors() || resultados.hasErrors()) {
			unModelo.addAttribute("tarea", new Tarea());
			unModelo.addAttribute("prioridades", Tarea.Prioridad.values());
			unModelo.addAttribute("tareas", tareasRepo.findAll());
			return "editar-tarea";
		}
		tareasRepo.save(tareaAEditar);
		return "redirect:/";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarTarea(@PathVariable("id") Long unaTareaId, Model unModelo) {
		Optional<Tarea> posibleTarea = tareasRepo.findById(unaTareaId);
		if (!posibleTarea.isPresent()) {
			throw new IllegalArgumentException("Tarea: " + unaTareaId + " no encontrada");
		}

		tareasRepo.deleteById(unaTareaId);
		return "redirect:/";

	}

	public ModelAndView cargarDatos(ModelAndView unModelo) {
		unModelo.addObject("tareas", tareasRepo.findAll());
		unModelo.addObject("prioridades", Tarea.Prioridad.values());
		return unModelo;
	}

}
