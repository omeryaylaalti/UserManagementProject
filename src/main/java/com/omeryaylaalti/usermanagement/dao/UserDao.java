package com.omeryaylaalti.usermanagement.dao;

import java.util.List;

import com.omeryaylaalti.usermanagement.dao.exception.UserNotFoundException;
import com.omeryaylaalti.usermanagement.model.User;


public interface UserDao {

	public Boolean add(User user) throws UserNotFoundException;
	
	public List<User> getAllUsers();

	public boolean update(User user) throws UserNotFoundException;

	public boolean delete(String id) ;

}
