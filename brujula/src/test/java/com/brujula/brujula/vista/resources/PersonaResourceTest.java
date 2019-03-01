/**
 * 
 */
package com.brujula.brujula.vista.resources;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.brujula.brujula.BrujulaApplication;
import com.brujula.brujula.vista.resources.vo.Person_vo;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author GMD
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BrujulaApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonaResourceTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

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
				.andExpect(jsonPath("$.yearOfBirth").value(1984)).andDo(print());
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
		mockMvc.perform(MockMvcRequestBuilders.post("/persona/addperson").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(person_vo)).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id_Person").exists()).andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.surname").exists()).andExpect(jsonPath("$.yearOfBirth").exists())
				.andExpect(jsonPath("$.name").value("Oscar Alfredo"))
				.andExpect(jsonPath("$.surname").value("Chafloque tampeck"))
				.andExpect(jsonPath("$.yearOfBirth").value(1984)).andDo(print());
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

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
