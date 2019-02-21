/**
 * 
 */
package com.brujula.brujula.negocio.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.brujula.brujula.modelo.Professor;


/**
 * @author GMD
 *
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, String> {
	
	@Modifying(clearAutomatically = true)
	@Query("update Professor p set p.asociateStartDate =:asociateStartDate, p.actingEndDate =:actingEndDate where p.id_Person =:id_Person")
	public int update(@Param("asociateStartDate") Timestamp asociateStartDate,@Param("actingEndDate") Timestamp actingEndDate, @Param("id_Person") String id_Person);
	
	
}
