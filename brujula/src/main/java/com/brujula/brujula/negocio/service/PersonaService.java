/**
 * 
 */
package com.brujula.brujula.negocio.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brujula.brujula.modelo.Person;
import com.brujula.brujula.negocio.repository.PersonRepository;




/**
 * @author GMD
 *
 */
@Service
@Transactional(readOnly = true)
public class PersonaService {
	
	private final PersonRepository personRepository;
	
	public PersonaService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	/*
	 * public List<Person> getAll(){ return this.personRepository.getAll(); }
	 */
	public List<Person> findAll(){
		return this.personRepository.findAll();
	}
	@Transactional
	public Person addPerson(Person person) {
        return this.personRepository.save(person);
    }
	
	public Person findByIdentificacion(String identificacion) {
        return this.personRepository.findByIdentificacion(identificacion);
    }
	

}
