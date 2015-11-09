package com.qa.generic.repository.sql;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ValidationException;
import com.qa.generic.repository.GenericRepository;
import com.qa.generic.util.DatabaseConnector;

/**
 * This is the Offline implementation of the Generic repository interface.
 * It is an alternative to the SQL implementation which is the default implementation.
 * 
 * @author jthompson
 *
 * @param <E> The Entity the specific instance of the object will use.
 */
@Alternative
public class GenericRepositorySQL<E> implements GenericRepository<E> {
	@Inject
	private DatabaseConnector databaseConnector;
	
	/**
	 * This retrieves the list of entities from the Initial data list, adds the entity to it and then returns it to the initial data object.
	 */
	@Override
	public void persistEntity(E entity) {
		EntityManager entityManager = databaseConnector.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		databaseConnector.closeEntityManager(entityManager);
	}

	/**
	 * This retrieves the list of entities from the Initial data list, adds the entities to it and then returns it to the initial data object.
	 */
	@Override
	public void persistEntities(List<E> entities) {
		EntityManager entityManager = databaseConnector.getEntityManager();
		entityManager.getTransaction().begin();
		for(E e : entities)
			entityManager.persist(e);
		entityManager.getTransaction().commit();
		databaseConnector.closeEntityManager(entityManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> listAllEntities(E entity) {
		List<E> results = new ArrayList<E>();
		EntityManager em = databaseConnector.getEntityManager();
		try {
			Query query = em.createNamedQuery("SELECT e FROM " + entity.getClass().getName().toUpperCase().substring(entity.getClass().getName().lastIndexOf(".") + 1) + " e");
			results = query.getResultList();
		} catch (Exception e) { e.printStackTrace(); }
		finally {
			databaseConnector.closeEntityManager(em);
		}
		return results;
	}
	
	/**
	 * This method updates an entry in the database throwing a ValidationException if a null value is passed.
	 */
	@Override
	public void updateEntity(E entity){
		if (entity == null)
			throw new ValidationException("null value passed");
		EntityManager entityManager = databaseConnector.getEntityManager();
		entityManager.merge(entity);
		databaseConnector.closeEntityManager(entityManager);
	}

	/**
	 * This removes the provided entity from the database throwing a ValidationException if a null value is passed in.
	 */
	@Override
	public void removeEntity(E entity) {
		if (entity == null)
			throw new ValidationException("null value passed");
		EntityManager entityManager = databaseConnector.getEntityManager();
		entityManager.merge(entity);
		databaseConnector.closeEntityManager(entityManager);
	}
}