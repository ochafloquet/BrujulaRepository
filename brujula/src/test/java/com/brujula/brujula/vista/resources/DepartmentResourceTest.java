/**
 * 
 */
package com.brujula.brujula.vista.resources;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.brujula.brujula.BrujulaApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.runners.MethodSorters;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

/**
 * @author GMD
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BrujulaApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentResourceTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}
	/**
	 * Test method for {@link com.brujula.brujula.vista.resources.DepartmentResource#getAllDepartament()}.
	 */
	@Test
	public void testGetAllDepartament() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/department/all").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(3))).andDo(print());
	}

	/**
	 * Test method for {@link com.brujula.brujula.vista.resources.DepartmentResource#createDepartment(com.brujula.brujula.modelo.Department)}.
	 */
	@Test
	public void testCreateDepartment() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/department/addDepartment")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content("{\"departmentName\" : \"secretaria Academica 2\" }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id_Department").exists())
				.andExpect(jsonPath("$.departmentName").exists())
				.andExpect(jsonPath("$.departmentName").value("secretaria Academica 2"))
				.andDo(print());
	}

}
