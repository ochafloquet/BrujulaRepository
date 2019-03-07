/**
 * 
 */
package modelo;

import java.util.Comparator;

/**
 * @author GMD
 *
 */
public class Profesor_vo implements Comparator<Profesor_vo>{
	
	private Integer id_Person;
	private String name;
	private String surname;
	private Integer yearOfBirth;
	private String  completeName;
	private Integer age;
	private String asociateStartDate;
	private String actingEndDate;
	private Departamento departamento;
	
	public Integer getId_Person() {
		return id_Person;
	}
	public void setId_Person(Integer id_Person) {
		this.id_Person = id_Person;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Integer getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(Integer yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public String getCompleteName() {
		return completeName;
	}
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAsociateStartDate() {
		return asociateStartDate;
	}
	public void setAsociateStartDate(String asociateStartDate) {
		this.asociateStartDate = asociateStartDate;
	}
	public String getActingEndDate() {
		return actingEndDate;
	}
	public void setActingEndDate(String actingEndDate) {
		this.actingEndDate = actingEndDate;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public Profesor_vo () {};
	public Profesor_vo (		
		 Integer id_Person,
		 String name,
		 String surname,
		 Integer yearOfBirth,
		 String  completeName,
		 Integer age,
		 String asociateStartDate,
		 String actingEndDate) {
		
		this.id_Person=id_Person;
		this.name=name;
		this.surname=surname;
		this.yearOfBirth=yearOfBirth;
		this.completeName=completeName;
		this.age=age;
		this.asociateStartDate=asociateStartDate;
		this.actingEndDate=actingEndDate;
		
	}
	public String toString() 
    { 
        return this.id_Person + " " + 
        		this.name + " " + 
        		this.surname + " " + 
        		this.yearOfBirth + " " + 
        		this.completeName + " " + 
        		this.age + " " + 
        		this.asociateStartDate + " " + 
        		this.actingEndDate
        		; 
    } 
	@Override
	public int compare(Profesor_vo o1, Profesor_vo o2) {
		// TODO Auto-generated method stub
		return o1.getCompleteName().compareTo(o2.getCompleteName());
	}
	
	

}
