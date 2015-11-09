package com.qa.generic.repository.sql;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import com.qa.generic.entities.Person;
import com.qa.generic.repository.PersonRepository;
import com.qa.generic.util.DatabaseConnector;

@Alternative
public class PersonRepositorySQL implements PersonRepository {
	@Inject
	private DatabaseConnector databaseConnector;
	
	@Override
	public Person findById(long pid) {
		EntityManager em = databaseConnector.getEntityManager();
		try {
			TypedQuery<Person> tq = em.createNamedQuery(Person.FIND_BY_PID, Person.class);
			databaseConnector.closeEntityManager(em);
			return tq.getSingleResult();
		} catch (NoResultException e) {
			databaseConnector.closeEntityManager(em);
			e.printStackTrace();
		}
		return new Person();
	}
	
	@Override
	public List<Person> findByFullName(String fname, String sname) {
		EntityManager em = databaseConnector.getEntityManager();
		try {
			TypedQuery<Person> tq = em.createNamedQuery(Person.FIND_BY_FULL_NAME, Person.class);
			databaseConnector.closeEntityManager(em);
			return tq.getResultList();
		} catch (NoResultException e) {
			databaseConnector.closeEntityManager(em);
			e.printStackTrace();
		}
		return new ArrayList<Person>();
	}

	@Override
	public Person findByEmail(String email) {
		EntityManager em = databaseConnector.getEntityManager();
		try {
			TypedQuery<Person> tq = em.createNamedQuery(Person.FIND_BY_EMAIL, Person.class);
			databaseConnector.closeEntityManager(em);
			return tq.getSingleResult();
		} catch (NoResultException e) {
			databaseConnector.closeEntityManager(em);
			e.printStackTrace();
		}
		return new Person();
	}
}