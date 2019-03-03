/**
 * 
 */
package com.brujula.brujula.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brujula.brujula.modelo.Department;


/**
 * @author GMD
 *
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, String>{
	
	@Query("Select c from Department c where c.id_Department =:identificacion")
	public  Department findByIdentificacion(@Param("identificacion") String identificacion) ;

	@Query("Select c from Department c where c.DepartmentName =:departmentName")
	public  Department findByDepartmentName(@Param("departmentName") String departmentName) ;

}
