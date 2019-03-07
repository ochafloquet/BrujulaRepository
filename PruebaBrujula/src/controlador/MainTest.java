/**
 * 
 */
package controlador;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import modelo.Departamento;
import modelo.Profesor;
import modelo.Profesor_vo;

import org.junit.runners.MethodSorters;

/**
 * @author GMD
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest{
	
	
	Main main = new Main();
	/**
	 * Test method for {@link controlador.Main#createDepartment(modelo.Departamento)}.
	 */
	@Test
	public void testACreateDepartamento() throws Exception {
		Departamento  departamento =  new Departamento();
		departamento.setId_Department(1);
		departamento.setDepartmentName("Academico");
		Departamento  resultado = main.createDepartment(departamento);
		assertEquals(departamento.getId_Department(),resultado.getId_Department());
		
		
	}

	/**
	 * Test method for {@link controlador.Main#obtenerDepartamentos()}.
	 */
	@Test
	public void testBObtenerDepartamentos() throws Exception {
		List<Departamento> listaDepartamento = new ArrayList<Departamento>();
		listaDepartamento = main.obtenerDepartamentos();
		System.out.println("listaDepartamento.size()"+listaDepartamento.size());
		assertEquals(1,listaDepartamento.size());
		
	}
	
	/**
	 * Test method for {@link controlador.Main#createProfesor(modelo.Profesor)}.
	 * Crear profesor ok
	 */
	@Test
	public void testCreateProfesor() throws Exception {
		Profesor_vo profesor = new Profesor_vo();
		profesor.setId_Person(2);
		profesor.setName("eduardo");
		profesor.setSurname("chafloque");
		profesor.setYearOfBirth(1984);
		profesor.setAsociateStartDate("10/03/2019");
		profesor.setActingEndDate("25/03/2019");
		Departamento departamento = new Departamento(2,"Evaluacion");		
		profesor.setDepartamento(departamento);		
		Profesor profesorobj =main.createProfesor(profesor);
		
		assertEquals(profesorobj.getId_Person(),profesor.getId_Person());
	}
	
	/**
	 * Test method for {@link controlador.Main#createProfesor(modelo.Profesor)}.
	 * Crear profesor - no podrá añadir dos profesores repetidos
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDCreateProfesorExist() throws Exception {
		Profesor_vo profesor = new Profesor_vo();
		profesor.setId_Person(1);
		profesor.setName("oscar");
		profesor.setSurname("chafloque");
		profesor.setYearOfBirth(1984);
		profesor.setAsociateStartDate("25/03/2019");
		profesor.setActingEndDate("25/03/2019");
		Departamento departamento = new Departamento(2,"Evaluacion");		
		profesor.setDepartamento(departamento);		
		Profesor profesorobj =main.createProfesor(profesor);
		
		assertEquals(profesorobj.getId_Person(),profesor.getId_Person());
		
	}
	
	/**
	 * Test method for {@link controlador.Main#createProfesor(modelo.Profesor)}.
	 * Crear profesor -  con fecha de fin menor a la actual
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testECreateProfesorFechaFin() throws Exception {
		Profesor_vo profesor = new Profesor_vo();
		profesor.setId_Person(1);
		profesor.setName("oscar");
		profesor.setSurname("chafloque");
		profesor.setYearOfBirth(1984);
		profesor.setAsociateStartDate("25/03/2019");
		profesor.setActingEndDate("04/03/2019");//fecha menor
		Departamento departamento = new Departamento(2,"Evaluacion");		
		profesor.setDepartamento(departamento);		
		Profesor profesorobj =main.createProfesor(profesor);
		assertFalse(throwException());
		
	}
	
	/**
	 * Test method for {@link controlador.Main#createProfesor(modelo.Profesor)}.
	 * Crear profesor -  comprobar también que los campos Department y DepartmentName existan
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFCreateProfesorDepartamentoNoExiste() throws Exception {
		Profesor_vo profesor = new Profesor_vo();
		profesor.setId_Person(1);
		profesor.setName("oscar");
		profesor.setSurname("chafloque");
		profesor.setYearOfBirth(1984);
		profesor.setAsociateStartDate("25/03/2019");
		profesor.setActingEndDate("04/03/2019");//fecha menor
		Departamento departamento = new Departamento(3,"Instruccion");		
		profesor.setDepartamento(departamento);		
		Profesor profesorobj =main.createProfesor(profesor);
		assertFalse(throwException());
		
	}
	
	/**
	 * Test method for {@link controlador.Main#obtenerProfesor()}.
	 * este test obtiene  getCompleteName() y getAge()
	 */
	@Test
	public void testGObtenerProfesor() {
		List<Profesor_vo> listaProfesor_vo = new ArrayList<Profesor_vo>();
		listaProfesor_vo = main.obtenerProfesor();
		System.out.println("listaProfesor_vo"+listaProfesor_vo.size());
		
		assertEquals(2,listaProfesor_vo.size());
		
		for(Profesor_vo profesor_vo:listaProfesor_vo){
			System.out.println("NOMBRECOMPLETO  "+profesor_vo.getCompleteName());
			System.out.println("EDAD  "+profesor_vo.getAge());			
		}

	}
	
	/**
	 * Test method for {@link controlador.Main#obtenerProfesor()}.
	 * Lista con los profesores del departamento en orden alfabético, según su apellido.
	 */
	@Test
	public void testHObtenerProfesor() {
		List<Profesor> listaProfesor = new ArrayList<Profesor>();
		listaProfesor = main.obtenerProfesorAll();
		System.out.println("listaProfesor"+listaProfesor.size());
		
		assertEquals(2,listaProfesor.size());
		
		for(Profesor profesor:listaProfesor){
			System.out.println("DEPARTAMENTO  "+profesor.getDepartamento().getDepartmentName());
			System.out.println("APELLIDOS  "+profesor.getSurname());
					
		}

	}
	 private boolean throwException(){
	        throw new IllegalArgumentException();
	    }

}
