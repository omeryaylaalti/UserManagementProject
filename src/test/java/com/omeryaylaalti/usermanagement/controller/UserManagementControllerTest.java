package com.omeryaylaalti.usermanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.omeryaylaalti.usermanagement.controller.UserManegementController;
import com.omeryaylaalti.usermanagement.dao.UserDao;
import com.omeryaylaalti.usermanagement.model.User;

@RunWith(JUnit4.class)
public class UserManagementControllerTest {

	@Mock
    private UserDao userDao;
 
    @InjectMocks
    private UserManegementController userManegementController;
 
    private MockMvc mockMvc;
 
    @Before
    public void setup() {
 
        // Process mock annotations
        MockitoAnnotations.initMocks(this);
 
        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(userManegementController).build();
 
    }

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userManegementController).build();
	}

	@Test
	public void testFindAll() throws Exception {
		final User user1 = new User();
		user1.setId("1");
		user1.setFirstname("Omer");
		user1.setLastname("Yaylaalti");
		user1.setPhonenumber("536 331 29 00");

		final User user2 = new User();
		user2.setId("2");
		user2.setFirstname("Test");
		user2.setLastname("Test");
		user2.setPhonenumber("Test");
		final List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		Mockito.when(userDao.getAllUsers()).thenReturn(users);

		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[*].lastname",Matchers.hasItems(Matchers.endsWith("Yaylaalti"), Matchers.endsWith("Test"))))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testCreateUser() throws Exception {
		final User user = new User();
		user.setId("1");
		user.setFirstname("Omer");
		user.setLastname("Yaylaalti");
		user.setPhonenumber("536 331 29 00");

		Mockito.when(userDao.add(Mockito.any(User.class))).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.put("/AddUser")
				.content("{\"fistname\":\"Omer\",\"lastname\":\"Yaylaalti\",\"phonenumber\":\"536 331 29 00\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastname", Matchers.is("Yaylaalti")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.phonenumber", Matchers.is("536 331 29 00")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstname", Matchers.is("Omer")))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void testUpdateUser() throws Exception {
		final User user = new User();
		user.setId("1");
		user.setFirstname("Omer");
		user.setLastname("Yaylaalti");
		user.setPhonenumber("536 331 29 00");

		Mockito.when(userDao.update(Mockito.any(User.class))).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.put("/UpdateUser")
				.content("{\"fistname\":\"Omer\",\"lastname\":\"Yaylaalti\",\"phonenumber\":\"536 331 29 00\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastname", Matchers.is("Yaylaalti")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.phonenumber", Matchers.is("536 331 29 00")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstname", Matchers.is("Omer")))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testDeleteUser() throws Exception {
		final User user = new User();
		user.setId("1");
		user.setFirstname("Omer");
		user.setLastname("Yaylaalti");
		user.setPhonenumber("536 331 29 00");

		Mockito.when(userDao.delete(Mockito.eq("1"))).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.put("/delete/{id}")
				.content("{\"fistname\":\"Omer\",\"lastname\":\"Yaylaalti\",\"phonenumber\":\"536 331 29 00\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastname", Matchers.is("Yaylaalti")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.phonenumber", Matchers.is("536 331 29 00")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstname", Matchers.is("Omer")))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	

}
