/**
 * 
 */
package com.brujula.brujula.vista.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brujula.brujula.modelo.Department;
import com.brujula.brujula.modelo.Person;
import com.brujula.brujula.negocio.service.DepartmentService;
import com.brujula.brujula.vista.resources.vo.Person_vo;

/**
 * @author GMD
 *
 */
@RestController
@RequestMapping("/department")
public class DepartmentResource {
	
	private final DepartmentService departmentService;
	
	public DepartmentResource(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)	
	public ResponseEntity<List<Department>> findPersona() {
		return ResponseEntity.ok(this.departmentService.findAll());
	}

	@PostMapping(value = "/addDepartment")
	public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
		return new ResponseEntity<>(this.departmentService.addDepartment(department), HttpStatus.CREATED);
	}
}
