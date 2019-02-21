/**
 * 
 */
package com.brujula.brujula.vista.resources.vo;



import com.brujula.brujula.modelo.Department;

import lombok.Data;

/**
 * @author GMD
 *
 */
@Data
public class Professor_vo {
	private String id_Person;
	private String asociateStartDate;
	private String actingEndDate;
	private Department department;

}
