package com.qa.generic.repository.offline;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.qa.generic.repository.GenericRepository;
import com.qa.generic.util.InitialData;

/**
 * This is the Offline implementation of the Generic repository interface.
 * It is an alternative to the SQL implementation which is the default implementation.
 * 
 * @author jthompson
 *
 * @param <E> The Entity the specific instance of the object will use.
 */
 abstract class GenericRepositoryOffline<E> implements GenericRepository<E> {
	@Inject
	private InitialData initialData;
	
	/**
	 * This retrieves the list of entities from the Initial data list, adds the entity to it and then returns it to the initial data object.
	 */
	@Override
	public void persistEntity(E entity) {
		ArrayList<E> es = initialData.getEntityList(entity);
		es.add(entity);
		initialData.setEntityList(es);
	}

	/**
	 * This retrieves the list of entities from the Initial data list, adds the entities to it and then returns it to the initial data object.
	 */
	@Override
	public void persistEntities(List<E> entities) {
		ArrayList<E> es = initialData.getEntityList(entities.get(0));
		es.addAll(entities);
		initialData.setEntityList(es);
	}

	/**
	 * This gets all the entities of the type of entity passed in.
	 */
	@Override
	public List<E> listAllEntities(E entity) {
		return initialData.getEntityList(entity);
	}

	/**
	 * This loops through, finds the entry with the same ID as the passed in object and then replaces it with the passed in object.
	 */
	@Override
	public void updateEntity(E entity) {
		ArrayList<E> es = initialData.getEntityList(entity);
		for(int i = 0; i <es.size(); i++) {
			if(es.get(i).equals(entity))
				es.set(i, entity);
		}
		initialData.setEntityList(es);
	}

	/**
	 * This loops through, finds the entry with the same ID as the passed in object and then removes it. 
	 */
	@Override
	public void removeEntity(E entity) {
		ArrayList<E> es = initialData.getEntityList(entity);
		for(int i = 0; i <es.size(); i++) {
			if(es.get(i).equals(entity))
				es.remove(i);
		}
		initialData.setEntityList(es);
	}
}