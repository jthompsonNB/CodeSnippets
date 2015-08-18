package com.netbuilder.generic.repository;

import java.util.List;

/**
 * This is the generic interface for handling the basic CRUD functions of entities.
 * 
 * @author jthompson
 *
 * @param <E> The Entity that the instance will be used for
 */
public interface GenericRepository<E>{
	
	/**
	 * This method persists one generic entity.
	 * 
	 * @param entity
	 */
	public void persistEntity(E entity);
	/**
	 * This method persists a list of generic entities.
	 * 
	 * @param entities
	 */
	public void persistEntities(List<E> entities);
	
	/**
	 * This method returns all the specified entities. The parameter is needed to define which type of entity to retrieve from the data store.
	 * 
	 * @param entity 
	 * @return
	 */
	public List<E> listEntities(E entity);
	
	/**
	 * This method updates a specific entity in its respective data store.
	 * 
	 * @param entity
	 */
	public void updateEntity(E entity);
	
	/**
	 * This method removes the entity from its respective data store.
	 * 
	 * @param entity
	 */
	public void removeEntity(E entity);
}