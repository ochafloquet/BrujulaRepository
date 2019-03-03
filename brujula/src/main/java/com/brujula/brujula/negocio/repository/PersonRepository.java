/**
 * 
 */
package com.brujula.brujula.negocio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brujula.brujula.modelo.Person;




/**
 * @author GMD
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
	
	@Query("Select c from Person c where c.id_Person =:identificacion")
	public  Person findByIdentificacion(@Param("identificacion") String identificacion) ;
	
	@Query("Select c from Person c where c.name =:name and c.surname =:surname")
	public  Person findByFullName(@Param("name") String name,@Param("surname") String surname) ;

}
