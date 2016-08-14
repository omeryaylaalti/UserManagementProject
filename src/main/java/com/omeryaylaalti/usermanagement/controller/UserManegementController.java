package com.omeryaylaalti.usermanagement.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omeryaylaalti.usermanagement.dao.UserDao;
import com.omeryaylaalti.usermanagement.model.User;

@Controller
public class UserManegementController {

	private static final Logger logger = LoggerFactory.getLogger(UserManegementController.class);

	@Resource(name="usersService")
	private UserDao userDao;
	

	// Show all users method
	@RequestMapping(value = {"/","/AddUser.htm" }, method = RequestMethod.GET)
	public String showForm(Model model) {
		
		logger.info("Received request to show all users");

		// Retrieve all users by delegating the call to PersonService
    	List<User> users = userDao.getAllUsers();
    	
    	// Attach users to the Model
    	model.addAttribute("Users", users);

		return "HomePage";
	}

	// Add a new User
	@RequestMapping(value = "/AddUser", method = RequestMethod.POST)
	public @ResponseBody String addUser(@ModelAttribute(value = "user") User user, BindingResult result) {

		logger.info("Received request to add new user");
		String returnText;
		if (!result.hasErrors()) {
			userDao.add(user);
			returnText = "User has been added to the Mongodb.<br>" + user.toString();
		} else {
			returnText = "Sorry, an error has occur. User has not been added to list.";
		}
		return returnText;
	}

	// Update User Method
	@RequestMapping(value = "/UpdateUser", method = RequestMethod.POST)
	public @ResponseBody String editUserPage(@ModelAttribute(value = "user") User user, BindingResult result) {
		logger.info(user.toString());
		
		userDao.update(user);

		return "User has been updated ";
	}

	// Delete User Method
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") String id) {
		if(id != null){
			userDao.delete(id);
		}
		return "redirect:/";
	}

}
