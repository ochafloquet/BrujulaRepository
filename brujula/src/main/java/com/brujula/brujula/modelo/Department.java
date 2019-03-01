/**
 * 
 */
package com.brujula.brujula.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author GMD
 *
 */
@Data
@Entity
@Table(name = "Department")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Department {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid2")
	private String id_Department;	
	private String DepartmentName;
	
	@OneToOne(mappedBy = "department",fetch = FetchType.LAZY)
	@JsonIgnore
	private Professor professor;
	
	public Department() {
    }
	public Department(String DepartmentName) {
	        this.DepartmentName = DepartmentName;
	}

}
