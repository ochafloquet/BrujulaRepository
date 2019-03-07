/**
 * 
 */
package controlador;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import util.Util;

import modelo.Departamento;
import modelo.Profesor;
import modelo.Profesor_vo;

/**
 * @author GMD
 *
 */
public class Main {
	private Departamento departamento = null;
	List<Departamento> listaDepartamento  = null;
	private Profesor profesor = null;
	List<Profesor> listaProfesor= null;
	List<Profesor_vo> listaProfesor_vo= null;
	Util util = new Util(); 
	
	
	public Main(){
		this.departamento = new Departamento();
		Departamento departamento = new Departamento(2,"Evaluacion");
		this.listaDepartamento = new ArrayList<Departamento>();
		this.listaDepartamento.add(departamento);
		//Datos Persona - Profesor
		this.profesor = new Profesor();
		this.listaProfesor= new ArrayList<Profesor>();
		this.listaProfesor_vo = new ArrayList<Profesor_vo>();
		//carga inicial
		Profesor profesor1 = new Profesor();
		profesor1.setId_Person(1);
		profesor1.setName("oscar");
		profesor1.setSurname("chafloque");
		profesor1.setYearOfBirth(1984);
		Timestamp asociateStartDate = util.convertStringToTimestamp("20/03/2019");
		Timestamp actingEndDate = util.convertStringToTimestamp("20/03/2019");
		profesor1.setAsociateStartDate(asociateStartDate);
		profesor1.setActingEndDate(actingEndDate);	
		Departamento departamentop = new Departamento(2,"Evaluacion");		
		profesor1.setDepartamento(departamentop);
		this.listaProfesor.add(profesor1);
		//profesor2
		Profesor profesor2 = new Profesor();
		profesor2.setId_Person(4);
		profesor2.setName("xavier");
		profesor2.setSurname("salazar");
		profesor2.setYearOfBirth(1986);
		profesor2.setAsociateStartDate(asociateStartDate);
		profesor2.setActingEndDate(actingEndDate);	
		profesor2.setDepartamento(departamentop);
		this.listaProfesor.add(profesor2);
	}

	
	public Departamento createDepartment(Departamento departamentoIN){
		this.departamento.setId_Department(departamentoIN.getId_Department());
		this.departamento.setDepartmentName(departamentoIN.getDepartmentName());
		this.listaDepartamento.add(this.departamento);
		return this.departamento;
	}
	
	public List<Departamento> obtenerDepartamentos(){		
		return this.listaDepartamento;
	}
	
	public Profesor createProfesor (Profesor_vo profesorin) {
		
		this.profesor.setId_Person(profesorin.getId_Person());
		this.profesor.setName(profesorin.getName());
		this.profesor.setSurname(profesorin.getSurname());
		this.profesor.setYearOfBirth(profesorin.getYearOfBirth());
		Timestamp asociateStartDate = util.convertStringToTimestamp(profesorin.getAsociateStartDate());
		Timestamp actingEndDate = util.convertStringToTimestamp(profesorin.getActingEndDate());
		this.profesor.setAsociateStartDate(asociateStartDate);
		this.profesor.setActingEndDate(actingEndDate);		
		this.profesor.setDepartamento(profesorin.getDepartamento());
		//validaciones
		Profesor profesorExist = new Profesor();
		profesorExist  = findByNameSurname(this.profesor.getName(),this.profesor.getSurname());
		Departamento departamentoExist = new Departamento();
		departamentoExist=findDepartamento(this.profesor.getDepartamento().getId_Department(),this.profesor.getDepartamento().getDepartmentName());
		////
		 Date date = new Date();
		 Timestamp timestamp = new Timestamp(date.getTime());
		 if(profesorExist!=null) {
			 throw new IllegalArgumentException("Profesor Existe");	
		 }
		 else if(actingEndDate.before(timestamp)) {
			 throw new IllegalArgumentException("la fecha actingEndDate debe ser mayor a la actual");
		 }else if(departamentoExist==null) {
			 throw new IllegalArgumentException("Departamento no Existe");	
		 }else {
			 this.listaProfesor.add(this.profesor);
				return this.profesor; 
		 }
		
	}
	
	public List<Profesor_vo> obtenerProfesor(){	
		
		Calendar cal= Calendar.getInstance();
		int currentYear= cal.get(Calendar.YEAR);
		
		for(Profesor profesor:this.listaProfesor){
			Profesor_vo person_vo = new Profesor_vo();	
			person_vo.setId_Person(profesor.getId_Person());
			person_vo.setName(profesor.getName());
			person_vo.setSurname(profesor.getSurname());
			person_vo.setCompleteName(profesor.getSurname()+" - "+profesor.getName());
			person_vo.setYearOfBirth(profesor.getYearOfBirth());
			person_vo.setAge(currentYear-profesor.getYearOfBirth());
			listaProfesor_vo.add(person_vo);
		}
		Profesor_vo c = new Profesor_vo();
		Collections.sort(listaProfesor_vo, c);
		return this.listaProfesor_vo;
	}
	
public List<Profesor> obtenerProfesorAll(){	
		Profesor c = new Profesor();
		Collections.sort(listaProfesor, c);
		return this.listaProfesor;
	}
	
	public Profesor findByNameSurname(String name,String surname) {
        for (Profesor profesor : this.listaProfesor){
            if (profesor.getName().equals(name)&&profesor.getSurname().equals(surname)){
                return profesor;
            }
        }
        return null;
    }
	
	public Departamento findDepartamento(Integer id_Department,String departmentName) {
        for (Departamento departamento : this.listaDepartamento){
            if (departamento.getId_Department().equals(id_Department)&&departamento.getDepartmentName().equals(departmentName)){
                return departamento;
            }
        }
        return null;
    }
	
	 
}
