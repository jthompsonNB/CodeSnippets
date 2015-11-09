package com.qa.generic.util;

import java.util.ArrayList;
import javax.ejb.Singleton;
import com.qa.generic.entities.Address;
import com.qa.generic.entities.Person;

/**
 * This holds lists of all the initial data for the system. This is used by the Offline implementations of the repository objects.
 * 
 * @author jthompson
 */
@Singleton
public class InitialData {
	private ArrayList<Address> addresses = new ArrayList<Address>();
	private ArrayList<Person> persons = new ArrayList<Person>();
	
	/**
	 * The constructor adds all the test data to the arrays.
	 */
	public InitialData() {
		addresses.add(new Address("4 NB Road", "NB Academy", "Greater Manchester", "NB4 1GM"));
		persons.add(new Person("Jave", "Stanition", "0987654321", "some.one@email.internet", addresses.get(0)));
		addresses.add(new Address("3 NB Road", "NB Warehouse", "Worthing", "NB3 1WO"));
		persons.add(new Person("Ian", "Unicon", "0987654332", "some.one.else@email.internet", addresses.get(1)));
	}
	
	/**
	 * This is a generic get method. 
	 * If an array of the passed in entity exists it will return it.
	 * If no array of the passed in entity exists it will return an empty array.
	 * As we add entities we will need to add if statements for each one.
	 * 
	 * @param entityType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> ArrayList<E> getEntityList(E entityType) {
		if(entityType.getClass() == addresses.get(0).getClass())
			return (ArrayList<E>) addresses;
		if(entityType.getClass() == persons.get(0).getClass())
			return (ArrayList<E>) persons;
		return new ArrayList<E>();
	}

	/**
	 * This is a generic set method. 
	 * If an array of the same type as the passed in array exists it will replace the stored array.
	 * If no array of the same type exists then nothing will happen.
	 * As we add entities we will need to add if statements for each one.
	 * 
	 * @param entities
	 */
	@SuppressWarnings("unchecked")
	public <E> void setEntityList(ArrayList<E> entities) {
		if(entities.get(0).getClass() == addresses.get(0).getClass())
			addresses = (ArrayList<Address>) entities;
		if(entities.get(0).getClass() == persons.get(0).getClass())
			persons = (ArrayList<Person>) entities;
	}
}