package com.brujula.brujula;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.brujula.brujula.modelo.Department;
import com.brujula.brujula.negocio.repository.DepartmentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SpringBootApplication
public class BrujulaApplication {
	private static final Logger logger = LoggerFactory.getLogger(BrujulaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BrujulaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner setup(DepartmentRepository departmentRepository) {
		return (args) -> {
			departmentRepository.save(new Department("Academico"));
			departmentRepository.save(new Department("Evaluacion"));
			departmentRepository.save(new Department("Admision"));
			logger.info("la data ha sido cargada");
		};
	}

}

