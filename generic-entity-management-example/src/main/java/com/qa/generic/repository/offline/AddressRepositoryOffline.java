package com.qa.generic.repository.offline;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import com.qa.generic.entities.Address;
import com.qa.generic.repository.AddressRepository;
import com.qa.generic.util.InitialData;

/**
 * This is the Offline implementation of the Address repository interface.
 * It is an alternative to the SQL implementation which is the default implementation.
 * 
 * @author jthompson
 */
@Alternative
public class AddressRepositoryOffline implements AddressRepository {
	@Inject
	private InitialData initialData;
	
	/**
	 * This goes through and returns the Address with the provided ID.
	 */
	@Override
	public Address findByID(long aid) {
		ArrayList<Address> as = initialData.getEntityList(new Address());
		for(Address a : as) {
			if(a.getAid() == aid)
				return a;
		}
		return null;
	}

	/**
	 * This goes through, and returns all entities with the same postcode.
	 */
	@Override
	public List<Address> findByPostcode(String postCode) {
		ArrayList<Address> as = initialData.getEntityList(new Address());
		for(int i = 0; i < as.size(); i--) {
			if(!as.get(i).getPostcode().equalsIgnoreCase(postCode)) {
				as.remove(i);
				i--;
			}
		}
		return as;
	}
	
	/**
	 * This goes through and finds the address with the same line 1 and postcode.
	 */
	@Override
	public Address findByLine1AndPostcode(String line1, String postcode) {
		ArrayList<Address> as = initialData.getEntityList(new Address());
		for(Address a : as) {
			if(a.getLine1().equalsIgnoreCase(line1) && a.getPostcode().equalsIgnoreCase(postcode))
				return a;
		}
		return null;
	}
}