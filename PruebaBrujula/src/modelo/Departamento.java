/**
 * 
 */
package modelo;
/**
 * @author GMD
 *
 */
public class Departamento {
	
	private Integer id_Department;	
	private String DepartmentName;
	
	public Integer getId_Department() {
		return id_Department;
	}
	public void setId_Department(Integer id_Department) {
		this.id_Department = id_Department;
	}
	public String getDepartmentName() {
		return DepartmentName;
	}
	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	
	public Departamento(){};
	public Departamento(Integer id_Department,String DepartmentName) {
		this.id_Department = id_Department;
        this.DepartmentName = DepartmentName;
}
	
	

}
