/**
 * 
 */
package com.brujula.brujula.vista.resources.vo;


import lombok.Data;

/**
 * @author GMD
 *
 */
@Data
public class Person_vo {
	private String id_Person;
	private String name;
	private String surname;
	private Integer yearOfBirth;
	private String  completeName;
	private Integer age;

}
