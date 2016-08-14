package com.omeryaylaalti.usermanagement.util;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDBFactory {
	
	private  static final Logger logger = LoggerFactory.getLogger(MongoDBFactory.class);

	private static Mongo m;

	// Make sure no one can instantiate our factory
	private MongoDBFactory() {
	}

	// Return an instance of Mongo
	public static Mongo getMongo() {
		logger.info("Retrieving MongoDB");
		if (m == null) {
			try {
				m = new Mongo("localhost", 27017);
			} catch (UnknownHostException e) {
				logger.error(e.getMessage());
			} catch (MongoException e) {
				logger.error(e.getMessage());
			}
		}

		return m;
	}

	// Retrieve a db
	public static DB getDB(String dbname) {
		logger.info("Retrieving db: " + dbname);
		return getMongo().getDB(dbname);
	}

	// Retrieve a collection
	public static DBCollection getCollection(String dbname, String collection) {
		logger.info("Retrieving collection: " + collection);
		return getDB(dbname).getCollection(collection);
	}
}
