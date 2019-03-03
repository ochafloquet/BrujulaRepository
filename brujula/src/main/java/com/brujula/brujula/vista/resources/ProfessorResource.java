/**
 * 
 */
package com.brujula.brujula.vista.resources;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brujula.brujula.modelo.Person;
import com.brujula.brujula.modelo.Professor;
import com.brujula.brujula.negocio.service.ProfessorService;
import com.brujula.brujula.util.Util;
import com.brujula.brujula.vista.resources.vo.Professor_vo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @author GMD
 *
 */
@RestController
@RequestMapping("/professor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProfessorResource {
	
	private final ProfessorService professorService;
	
	public ProfessorResource( ProfessorService professorService) {
		this.professorService = professorService;
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<Professor> createProfesor(@RequestBody Professor_vo Professor_vo) {
		Util util = new Util(); 
		Professor professor = new Professor();
		professor.setId_Person(Professor_vo.getId_Person());
		Timestamp asociateStartDate = util.convertStringToTimestamp(Professor_vo.getAsociateStartDate());
		Timestamp actingEndDate = util.convertStringToTimestamp(Professor_vo.getActingEndDate());
		professor.setAsociateStartDate(asociateStartDate);
		professor.setActingEndDate(actingEndDate);
		professor.setDepartment(Professor_vo.getDepartment());
		return new ResponseEntity<>(this.professorService.addProfessor(professor), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)	
	public ResponseEntity<List<Professor>> getAllProffesors() {
		return ResponseEntity.ok(this.professorService.findAll());
	}
}
