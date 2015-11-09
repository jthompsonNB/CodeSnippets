package com.qa.generic.util;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class DatabaseConnector {
	private EntityManagerFactory factory;

	public EntityManager getEntityManager() {
		factory = Persistence.createEntityManagerFactory("NBGardensAccountsPU");
		EntityManager entityManager = factory.createEntityManager();
		return entityManager;
	}

	public void closeEntityManager(EntityManager entityManager) {
		entityManager.close();
		factory.close();
	}
}