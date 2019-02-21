/**
 * 
 */
package com.brujula.brujula.negocio.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brujula.brujula.modelo.Department;
import com.brujula.brujula.modelo.Person;
import com.brujula.brujula.modelo.Professor;
import com.brujula.brujula.negocio.repository.DepartmentRepository;
import com.brujula.brujula.negocio.repository.PersonRepository;
import com.brujula.brujula.negocio.repository.ProfessorRepository;

/**
 * @author GMD
 *
 */
@Service
@Transactional(readOnly = true)
public class ProfessorService {
	
	private final ProfessorRepository professorRepository;
	private final PersonRepository personRepository;
	private final DepartmentRepository departmentRepository;
	public ProfessorService(ProfessorRepository professorRepository,PersonRepository personRepository,DepartmentRepository departmentRepository) {
		this.professorRepository = professorRepository;
		this.personRepository = personRepository;
		this.departmentRepository = departmentRepository;
	}
	

	@Transactional
	public Professor addProfessor(Professor professorin) {
		
		Person person=personRepository.findByIdentificacion(professorin.getId_Person());
		Department Department=departmentRepository.findByIdentificacion(professorin.getDepartment().getId_Department());
		Professor professor = new Professor();
		professor.setPerson(person);
		professor.setAsociateStartDate(professorin.getAsociateStartDate());
		professor.setActingEndDate(professorin.getActingEndDate());
		professor.setDepartment(Department);
        return this.professorRepository.save(professor);
    }


	public List<Professor> findAll(){
		return this.professorRepository.findAll();
	}
	
	
}
