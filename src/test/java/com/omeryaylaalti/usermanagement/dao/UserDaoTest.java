package com.omeryaylaalti.usermanagement.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.omeryaylaalti.usermanagement.dao.UserDao;
import com.omeryaylaalti.usermanagement.model.User;

/**
 * Created by Omer Yaylaalti on 02/08/16.
 */

@RunWith(JUnit4.class)
@ContextConfiguration(value = { "classpath*:springDataContext-test.xml" })
public class UserDaoTest {

	@Autowired
	UserDao userDao;

	static User user;

	@Before
	public void init() {
		if (user != null) {
			return;
		}
		user = new User();
		user.setId("1");
		user.setFirstname("Omer");
		user.setLastname("Yaylaalti");
		user.setPhonenumber("536 331 29 00");
		userDao.add(user);
	}

	@Test
	public void testGetAllUsers() {
		List<User> user = userDao.getAllUsers();
		if (!user.isEmpty()) {
			Assert.assertNotNull(user);

		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAdd(){
		user.setId("2");
		user.setFirstname("test");
		user.setLastname("xxxxxxxx");
		user.setPhonenumber("535 311 29 00");
		userDao.add(user);
		
		 List<User> users = userDao.getAllUsers();
	     Assert.assertEquals(user.getFirstname(), users.get(1).getFirstname());
		
	}
	
	@Test
	public void testUpdate(){
		user.setId("2");
		user.setFirstname("newtest");
		user.setLastname("xxxxxxxx");
		user.setPhonenumber("535 311 29 00");
		userDao.update(user);
		
		List<User> users = userDao.getAllUsers();
	    Assert.assertEquals(user.getFirstname(), users.get(1).getFirstname());
	    Assert.assertEquals(2, users.size());
	}
	
	@Test
	public void testDelete(){
		userDao.delete("2");
		List<User> users = userDao.getAllUsers();
	    Assert.assertEquals(1, users.size());
		
	}
	
	
}
