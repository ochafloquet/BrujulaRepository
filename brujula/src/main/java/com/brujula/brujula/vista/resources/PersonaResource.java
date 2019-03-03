/**
 * 
 */
package com.brujula.brujula.vista.resources;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brujula.brujula.modelo.Person;
import com.brujula.brujula.negocio.service.PersonaService;
import com.brujula.brujula.vista.resources.vo.Person_vo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;






/**
 * @author OSCAR ALFREDO CHAFLOQUE TAMPECK
 * Clase que representa el servicio web de persona
 */
@RestController
@RequestMapping("/persona")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PersonaResource {
	
	private final PersonaService personaService;
	
	public PersonaResource(PersonaService personaService) {
		this.personaService = personaService;
	}
	
	@RequestMapping(value = "/completeName", method = RequestMethod.GET)	
	public ResponseEntity<List<Person_vo>> findPersonacompleteName() {
		List<Person> listPerson=this.personaService.findAll();
		List<Person_vo> listPerson_vo= new ArrayList<Person_vo>();
		System.out.println("tamaño"+listPerson.size());
		Calendar cal= Calendar.getInstance();
		int currentYear= cal.get(Calendar.YEAR);
		//recorremos la lista
		for(Person person:listPerson){
			Person_vo person_vo = new Person_vo();	
			person_vo.setId_Person(person.getId_Person());
			person_vo.setCompleteName(person.getName()+" - "+person.getSurname());
			person_vo.setName(person.getName());
			person_vo.setSurname(person.getSurname());
			person_vo.setYearOfBirth(person.getYearOfBirth());
			person_vo.setAge(currentYear-person.getYearOfBirth());
			listPerson_vo.add(person_vo);
		}
		
		return ResponseEntity.ok(listPerson_vo);
	}
	
	@RequestMapping(value = "/getAge", method = RequestMethod.GET)	
	public ResponseEntity<List<Person_vo>> findPersonAge() {
		List<Person> listPerson=this.personaService.findAll();
		List<Person_vo> listPerson_vo= new ArrayList<Person_vo>();
		Calendar cal= Calendar.getInstance();
		int currentYear= cal.get(Calendar.YEAR);
		System.out.println("tamaño"+listPerson.size());
		//recorremos la lista
		for(Person person:listPerson){
			Person_vo person_vo = new Person_vo();
			person_vo.setId_Person(person.getId_Person());
			person_vo.setName(person.getName());
			person_vo.setSurname(person.getSurname());
			person_vo.setAge(currentYear-person.getYearOfBirth());
			listPerson_vo.add(person_vo);
		}
		
		return ResponseEntity.ok(listPerson_vo);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)	
	public ResponseEntity<List<Person>> findPersona() {
		return ResponseEntity.ok(this.personaService.findAll());
	}
	
	@PostMapping(value = "/getPersonByFullName")
	public ResponseEntity<Person> getPersonByFullName(@RequestBody Person_vo person_vo) {		
		return new ResponseEntity<>(this.personaService.findByFullName(person_vo.getName(), person_vo.getSurname()), HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/addperson")
	public ResponseEntity<Person> createPerson(@RequestBody Person_vo person_vo) {
		Person person = new Person();
		person.setName(person_vo.getName());
		person.setSurname(person_vo.getSurname());
		person.setYearOfBirth(person_vo.getYearOfBirth()); 
		return new ResponseEntity<>(this.personaService.addPerson(person), HttpStatus.CREATED);
		
	}

}
