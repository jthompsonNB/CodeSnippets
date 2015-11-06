package com.netbuilder.generic.repository.offline;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import com.netbuilder.generic.entities.Address;
import com.netbuilder.generic.repository.AddressRepository;
import com.netbuilder.generic.util.InitialData;

/**
 * This is the Offline implementation of the Address repository interface.
 * It is an alternative to the SQL implementation which is the default implementation.
 * 
 * @author jthompson
 */
@Alternative
public class AddressRepositoryOffline extends GenericRepositoryOffline<Address> implements AddressRepository {
	@Inject
	private InitialData initialData;
	
	/**
	 * This goes through and returns the Address with the provided ID.
	 */
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
}