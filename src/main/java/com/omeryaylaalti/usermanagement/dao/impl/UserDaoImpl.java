package com.omeryaylaalti.usermanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.omeryaylaalti.usermanagement.dao.UserDao;
import com.omeryaylaalti.usermanagement.model.User;
import com.omeryaylaalti.usermanagement.util.MongoDBFactory;

@Service("usersService")
@Transactional
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	public UserDaoImpl() {
		// Initialize our database
		init();
	}
	
	private void init() {
		// Populate our MongoDB database

		logger.info("Init MongoDB users");

		// Drop existing collection
		MongoDBFactory.getCollection("userdb", "mycollection").drop();
		// Retrieve collection. If not existing, create a new one
		DBCollection coll = MongoDBFactory.getCollection("userdb", "mycollection");

		// Create new object
		BasicDBObject doc = new BasicDBObject();
		// Generate random id using UUID type 4
		// See http://en.wikipedia.org/wiki/Universally_unique_identifier
		doc.put("id", UUID.randomUUID().toString());
		doc.put("firstname", "Omer");
		doc.put("lastname", "Yaylaalti");
		doc.put("phonenumber", "0536 331 29 00");
		coll.insert(doc);

	}
	
	/**
	 * Retrieves all users
	 */
	public List<User> getAllUsers() {
		logger.info("Retrieving all users");

		// Retrieve collection
		DBCollection coll = MongoDBFactory.getCollection("userdb", "mycollection");
		// Retrieve cursor for iterating records
		DBCursor cur = coll.find();
		// Create new list
		List<User> items = new ArrayList<User>();
		// Iterate cursor
		while (cur.hasNext()) {
			// Map DBOject to User
			DBObject dbObject = cur.next();
			User user = new User();

			user.setId(dbObject.get("id").toString());
			user.setFirstname(dbObject.get("firstname").toString());
			user.setLastname(dbObject.get("lastname").toString());
			user.setPhonenumber(dbObject.get("phonenumber").toString());

			// Add to new list
			items.add(user);
		}

		// Return list
		return items;
	}

	/**
	 * Adds a new user
	 */
	@Override
	public Boolean add(User user) {
		logger.debug("Adding a new user");

		try {
			// Retrieve collection
			DBCollection coll = MongoDBFactory.getCollection("userdb", "mycollection");
			// Create a new object
			BasicDBObject doc = new BasicDBObject();
			// Generate random id using UUID type 4
			// See http://en.wikipedia.org/wiki/Universally_unique_identifier
			doc.put("id", UUID.randomUUID().toString());
			doc.put("firstname", user.getFirstname());
			doc.put("lastname", user.getLastname());
			doc.put("phonenumber", user.getPhonenumber());
			// Save new user
			coll.insert(doc);

			return true;

		} catch (Exception e) {
			logger.error("An error has occurred while trying to add new user", e);
			return false;
		}
	}

	/**
	 * Retrieves a single mongo object
	 */
	private DBObject getDBObject(String id) {
		logger.debug("Retrieving an existing mongo object");

		// Retrieve collection
		DBCollection coll = MongoDBFactory.getCollection("userdb", "mycollection");
		// Create a new object
		DBObject doc = new BasicDBObject();
		// Put id to search
		doc.put("id", id);

		// Find and return the mongo with the given id
		return coll.findOne(doc);
	}
	
	/**
	 * Deletes an existing user
	 */

	@Override
	public boolean delete(String id) {
		logger.debug("Deleting existing user");

		try {
			// Retrieve user to delete
			BasicDBObject item = (BasicDBObject) getDBObject(id);
			// Retrieve collection
			DBCollection coll = MongoDBFactory.getCollection("userdb", "mycollection");
			// Delete retrieved user
			coll.remove(item);

			return true;

		} catch (Exception e) {
			logger.error("An error has occurred while trying to delete new user", e);
			return false;
		}
	}
	
	/**
	 * Edits an existing user
	 */
	@Override
	public boolean update(User user) {
		logger.debug("Editing existing user");

		try {
			// Retrieve user to edit
			BasicDBObject existing = (BasicDBObject) getDBObject(user.getId());

			DBCollection coll = MongoDBFactory.getCollection("userdb", "mycollection");

			// Create new object
			BasicDBObject edited = new BasicDBObject();
			// Assign existing details
			edited.put("id", user.getId());
			edited.put("firstname", user.getFirstname());
			edited.put("lastname", user.getLastname());
			edited.put("phonenumber", user.getPhonenumber());
			// Update existing user
			coll.update(existing, edited);

			return true;

		} catch (Exception e) {
			logger.error("An error has occurred while trying to edit existing user", e);
			return false;
		}
	}


}
