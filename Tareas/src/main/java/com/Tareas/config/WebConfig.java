package com.Tareas.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.Tareas.data.TareasRepository;
import com.Tareas.modelo.Tarea;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public CommandLineRunner dataLoader(TareasRepository tareasRepo) {
		return args -> {
			Tarea ejemploTarea = new Tarea();
			ejemploTarea.setDescripcion("una tarea de ejemplo");
			ejemploTarea.setPrioridad(Tarea.Prioridad.ALTA);

			Tarea otroEjemploTarea = new Tarea();
			otroEjemploTarea.setDescripcion("otra tarea de ejemplo");
			otroEjemploTarea.setPrioridad(Tarea.Prioridad.BAJA);

			tareasRepo.save(ejemploTarea);
			tareasRepo.save(otroEjemploTarea);
		};
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login");
	}

}
