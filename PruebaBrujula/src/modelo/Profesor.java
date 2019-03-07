/**
 * 
 */
package modelo;

import java.sql.Timestamp;
import java.util.Comparator;

/**
 * @author GMD
 *
 */
public class Profesor extends Persona implements Comparator<Profesor> {
	
	private Timestamp  asociateStartDate;
	private Timestamp actingEndDate;
	private Departamento departamento;
	
	public Timestamp getAsociateStartDate() {
		return asociateStartDate;
	}
	public void setAsociateStartDate(Timestamp asociateStartDate) {
		this.asociateStartDate = asociateStartDate;
	}
	public Timestamp getActingEndDate() {
		return actingEndDate;
	}
	public void setActingEndDate(Timestamp actingEndDate) {
		this.actingEndDate = actingEndDate;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public Profesor () {};
	public Profesor (		
		 Integer id_Person,
		 String name,
		 String surname,
		 Integer yearOfBirth,
		 Timestamp asociateStartDate,
		 Timestamp actingEndDate) {
		
		this.id_Person=id_Person;
		this.name=name;
		this.surname=surname;
		this.yearOfBirth=yearOfBirth;
		this.asociateStartDate=asociateStartDate;
		this.actingEndDate=actingEndDate;
		
	}
	
	public String toString() 
    { 
        return this.id_Person + " " + 
        		this.name + " " + 
        		this.surname + " " + 
        		this.yearOfBirth + " " + 
        		this.asociateStartDate + " " + 
        		this.actingEndDate
        		; 
    } 
	
	@Override
	public int compare(Profesor o1, Profesor o2) {
		// TODO Auto-generated method stub
		return o1.getSurname().compareTo(o2.getSurname());
	}
	

	
	
	
	

}
