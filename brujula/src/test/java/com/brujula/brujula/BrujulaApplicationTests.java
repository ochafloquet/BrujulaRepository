package com.brujula.brujula;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.brujula.brujula.modelo.Department;
import com.brujula.brujula.modelo.Person;
import com.brujula.brujula.modelo.Professor;
import com.brujula.brujula.vista.resources.vo.Person_vo;
import com.brujula.brujula.vista.resources.vo.Professor_vo;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BrujulaApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BrujulaApplicationTests {
	
	private static final Logger logger = LoggerFactory.getLogger(BrujulaApplicationTests.class);
	Person person = new Person();
	Department department = new Department();
	Professor professor = new Professor();
	String Id_Department ="";
	String Id_Person ="";
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}
	
	/* Test para la Entidad Departament */

	/**
	 * Test method for {@link com.brujula.brujula.vista.resources.DepartmentResource#createDepartment(com.brujula.brujula.modelo.Department)}.
	 */
	@Test
	public void testCreateDepartment() throws Exception {
		Department DepartmentcD = new Department("secretaria Academica 2");
		mockMvc.perform(MockMvcRequestBuilders.post("/department/addDepartment")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(asJsonString(DepartmentcD))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id_Department").exists())
				.andExpect(jsonPath("$.departmentName").exists())
				.andExpect(jsonPath("$.departmentName").value("secretaria Academica 2"))
				.andDo(print());
		
	}
	
	/**
	 * Test method for {@link com.brujula.brujula.vista.resources.DepartmentResource#getAllDepartament()}.
	 */
	@Test
	public void testGetAllDepartament() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/department/all").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(4))).andDo(print());
	}
	//Test para la Entidad Persona
	/**
	 * Test method for
	 * {@link com.brujula.brujula.vista.resources.PersonaResource#createPerson(com.brujula.brujula.vista.resources.vo.Person_vo)}.
	 */
	@Test
	public void testCreatePerson() throws Exception {
		Person_vo person_vo = new Person_vo("Oscar Alfredo", "Chafloque tampeck", 1984);
		mockMvc.perform(MockMvcRequestBuilders.post("/persona/addperson").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(person_vo)).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id_Person").exists()).andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.surname").exists()).andExpect(jsonPath("$.yearOfBirth").exists())
				.andExpect(jsonPath("$.name").value("Oscar Alfredo"))
				.andExpect(jsonPath("$.surname").value("Chafloque tampeck"))
				.andExpect(jsonPath("$.yearOfBirth").value(1984))
				.andDo(print());
		
	}

	/**
	 * Test method for
	 * {@link com.brujula.brujula.vista.resources.PersonaResource#createPerson(com.brujula.brujula.vista.resources.vo.Person_vo)}.
	 * Verificar en consola la violacion del constraint "ERROR 10848 --- Unique
	 * index or primary key violation: "UK8TBCMGCJGWJRUI3E4Y0TS8X8K_INDEX_8 ON
	 * PUBLIC.PERSON(NAME, SURNAME) VALUES ('Oscar Alfredo', 'Chafloque tampeck',
	 * 1)"; SQL statement:"
	 */

	@Test
	public void testCreatePersonNoRepeat() throws Exception {
		Person_vo person_vo = new Person_vo("Oscar Alfredo", "Chafloque tampeck", 1984);
		MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post("/persona/addperson").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(person_vo)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andDo(print()).andReturn();
		
	}

	/**
	 * Test method for
	 * {@link com.brujula.brujula.vista.resources.PersonaResource#findPersonacompleteName()}.
	 */

	@Test
	public void testFindPersonacompleteName() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/persona/completeName").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1))).andDo(print());
	}

	/**
	 * Test method for
	 * {@link com.brujula.brujula.vista.resources.PersonaResource#findPersonAge()}.
	 */

	@Test
	public void testFindPersonAge() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/persona/getAge").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(1))).andDo(print());
	}

	/**
	 * Test method for
	 * {@link com.brujula.brujula.vista.resources.PersonaResource#findPersona()}.
	 */
	@Test
	public void testFindPersona() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/persona/all").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(1))).andDo(print());
	}
	
	//Test para la Entidad Profesor
	
	/**
	 * Test method for {@link com.brujula.brujula.vista.resources.ProfessorResource#createProfesor(com.brujula.brujula.vista.resources.vo.Professor_vo)}.
	 */
	@Test
	public void testCreateProfesor() throws Exception{
		//Obtenemos data 
		//persona
		Person_vo person_vo = new Person_vo("Oscar Alfredo", "Chafloque tampeck", 1984);
		MvcResult respersona = mockMvc.perform(MockMvcRequestBuilders.post("/persona/getPersonByFullName")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(person_vo))
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andReturn();
		String contentAsStringPersona = respersona.getResponse().getContentAsString();
		this.person = objectMapper.readValue(contentAsStringPersona, Person.class);
		Id_Person=this.person.getId_Person();
		logger.info("contentAsString"+contentAsStringPersona);
		logger.info("obteniendo datos+++++++++Id_Person"+this.person.getId_Person());
		//Departamento
		Department DepartmentcD = new Department("secretaria Academica 2");
		MvcResult resDepartment = mockMvc.perform(MockMvcRequestBuilders.post("/department/findByDepartmentName")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(asJsonString(DepartmentcD))
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andReturn();
		
		String contentAsStringDepartment = resDepartment.getResponse().getContentAsString();
		this.department = objectMapper.readValue(contentAsStringDepartment, Department.class);
		logger.info("contentAsString"+contentAsStringDepartment);
		Id_Department=this.department.getId_Department();
		logger.info("Id getId_Department"+this.department.getId_Department());
		//
		
		Professor_vo professor_vo = new Professor_vo();
		professor_vo.setId_Person(this.person.getId_Person());
		professor_vo.setAsociateStartDate("01/03/2019");
		professor_vo.setActingEndDate("28/03/2019");
		professor_vo.setDepartment(this.department);
		
		MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post("/professor/add").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(professor_vo)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id_Person").exists())
				.andDo(print())
				.andReturn();
		String contentAsString = res.getResponse().getContentAsString();
		this.professor = objectMapper.readValue(contentAsString, Professor.class);
		logger.info("contentAsString"+contentAsString);
		
	}
	

	/**
	 * Test method for {@link com.brujula.brujula.vista.resources.ProfessorResource#createProfesor(com.brujula.brujula.vista.resources.vo.Professor_vo)}.
	 */
	@Test
	public void testCreateProfesorActingEndDateBeforeNow() throws Exception{
		//Obtenemos data 
		//persona
		Person_vo person_vo = new Person_vo("Oscar Alfredo", "Chafloque tampeck", 1984);
		MvcResult respersona = mockMvc.perform(MockMvcRequestBuilders.post("/persona/getPersonByFullName")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(person_vo))
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andReturn();
		String contentAsStringPersona = respersona.getResponse().getContentAsString();
		this.person = objectMapper.readValue(contentAsStringPersona, Person.class);
		Id_Person=this.person.getId_Person();
		logger.info("contentAsString"+contentAsStringPersona);
		logger.info("obteniendo datos+++++++++Id_Person"+this.person.getId_Person());
		//Departamento
		Department DepartmentcD = new Department("secretaria Academica 2");
		MvcResult resDepartment = mockMvc.perform(MockMvcRequestBuilders.post("/department/findByDepartmentName")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(asJsonString(DepartmentcD))
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andReturn();
		
		String contentAsStringDepartment = resDepartment.getResponse().getContentAsString();
		this.department = objectMapper.readValue(contentAsStringDepartment, Department.class);
		logger.info("contentAsString"+contentAsStringDepartment);
		Id_Department=this.department.getId_Department();
		logger.info("Id getId_Department"+this.department.getId_Department());
		//
		
		Professor_vo professor_vo = new Professor_vo();
		professor_vo.setId_Person(this.person.getId_Person());
		professor_vo.setAsociateStartDate("01/02/2019");
		professor_vo.setActingEndDate("28/02/2019");//ojo fecha menor a la actual
		professor_vo.setDepartment(this.department);
		
		MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post("/professor/add").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(professor_vo)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(print())
				.andReturn();
		String contentAsString = res.getResponse().getContentAsString();
		this.professor = objectMapper.readValue(contentAsString, Professor.class);
		logger.info("contentAsString"+contentAsString);
				
	}

	/**
	 * Test method for {@link com.brujula.brujula.vista.resources.ProfessorResource#getAllProffesors()}.
	 */
	@Test
	public void testGetAllProffesors() throws Exception {
		MvcResult res =mockMvc.perform(MockMvcRequestBuilders.get("/professor/all").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print()).andReturn();
		String contentAsString = res.getResponse().getContentAsString();
		logger.info("Profesores"+contentAsString);
		
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}

