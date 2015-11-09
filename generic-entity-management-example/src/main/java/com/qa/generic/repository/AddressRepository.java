package com.qa.generic.repository;

import java.util.List;
import com.qa.generic.entities.Address;

/**
 * This is the Repository interface for the Address entity.
 * 
 * @author jthompson
 */
public interface AddressRepository {
	/**
	 * This method will return a single Address entity based on the aid passed in
	 * 
	 * @param aid
	 * @return
	 */
	public Address findByID(long aid);
	
	/**
	 * This method returns a list of all addresses with the provided postcode.
	 * 
	 * @param postCode
	 * @return
	 */
	public List<Address> findByPostcode(String postCode);
	
	/**
	 * This returns a single address based on the postcode and the 1st line of the address.
	 * 
	 * @param line1
	 * @param postcode
	 * @return
	 */
	public Address findByLine1AndPostcode(String line1, String postcode);
}