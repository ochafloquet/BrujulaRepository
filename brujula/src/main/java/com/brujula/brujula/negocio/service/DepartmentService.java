/**
 * 
 */
package com.brujula.brujula.negocio.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.brujula.brujula.modelo.Department;
import com.brujula.brujula.negocio.repository.DepartmentRepository;

/**
 * @author GMD
 *
 */
@Service
@Transactional(readOnly = true)
public class DepartmentService {
	
	private final DepartmentRepository departmentRepository;
	
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	public List<Department> findAll(){
		return this.departmentRepository.findAll();
	}
	@Transactional
	public Department addDepartment(Department department) {
        return this.departmentRepository.save(department);
    }
}
