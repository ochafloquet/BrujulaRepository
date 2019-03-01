/**
 * 
 */
package com.brujula.brujula.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
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
@Table(name = "Person", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "surname" }) })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Person {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id_Person;
	private String name;
	private String surname;
	private Integer yearOfBirth;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
	private Professor professor;

	public Person() {
	}

	public Person(String name, String surname, Integer yearOfBirth) {
		this.name = name;
		this.surname = surname;
		this.yearOfBirth = yearOfBirth;
	}

}
