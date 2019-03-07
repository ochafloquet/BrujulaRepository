/**
 * 
 */
package modelo;

/**
 * @author GMD
 *
 */
public class Persona {
	
	protected Integer id_Person;
	protected String name;
	protected String surname;
	protected Integer yearOfBirth;
	
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
	
	
}
