package com.qa.generic.repository.sql;

import java.util.List;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import com.qa.generic.entities.Address;
import com.qa.generic.repository.AddressRepository;
import com.qa.generic.util.DatabaseConnector;

/**
 * This is the Offline implementation of the Address repository interface.
 * It is an alternative to the SQL implementation which is the default implementation.
 * 
 * @author jthompson
 */
@Alternative
public class AddressRepositorySQL implements AddressRepository {
	@Inject
	private DatabaseConnector databaseConnector;
	
	/**
	 * This goes through and returns the Address with the provided ID.
	 */
	@Override
	public Address findByID(long aid) {
		EntityManager em = databaseConnector.getEntityManager();
		try{
			TypedQuery<Address> tq = em.createNamedQuery(Address.FIND_BY_AID, Address.class);
			databaseConnector.closeEntityManager(em);
			return tq.getSingleResult();
		} catch (NoResultException e) {
			databaseConnector.closeEntityManager(em);
			e.printStackTrace(); 
		}
		return null;
	}

	/**
	 * This goes through, and returns all entities with the same postcode.
	 */
	@Override
	public List<Address> findByPostcode(String postCode) {
		EntityManager em = databaseConnector.getEntityManager();
		try{
			TypedQuery<Address> tq = em.createNamedQuery(Address.FIND_BY_POSTCODE, Address.class);
			tq.setParameter("postCode", postCode);
			databaseConnector.closeEntityManager(em);
			return tq.getResultList();
		} catch (NoResultException e) { 
			databaseConnector.closeEntityManager(em);
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public Address findByLine1AndPostcode(String line1, String postcode) {
		EntityManager em = databaseConnector.getEntityManager();
		try{
			TypedQuery<Address> tq = em.createNamedQuery(Address.FIND_BY_LINE1_AND_POSTCODE, Address.class);
			tq.setParameter("line1", line1);
			tq.setParameter("postCode", postcode);
			databaseConnector.closeEntityManager(em);
			return tq.getSingleResult();
		} catch (NoResultException e) { 
			databaseConnector.closeEntityManager(em);
			e.printStackTrace(); 
		}
		return null;
	}
}