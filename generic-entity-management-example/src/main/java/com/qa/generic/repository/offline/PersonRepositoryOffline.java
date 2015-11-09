package com.qa.generic.repository.offline;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import com.qa.generic.entities.Person;
import com.qa.generic.repository.PersonRepository;
import com.qa.generic.util.InitialData;

@Alternative
public class PersonRepositoryOffline implements PersonRepository {
	@Inject
	private InitialData initialData;
	
	@Override
	public List<Person> findByFullName(String fname, String sname) {
		ArrayList<Person> as = initialData.getEntityList(new Person());
		for(int i=0; i<as.size(); i++) {
			if(!as.get(i).getFname().equalsIgnoreCase(fname) && !as.get(i).getSname().equalsIgnoreCase(sname)) {
				as.remove(i);
				i--;
			}
		}
		return as;
	}

	@Override
	public Person findByEmail(String email) {
		ArrayList<Person> as = initialData.getEntityList(new Person());
		for(Person p : as) {
			if(p.getEmail().equalsIgnoreCase(email))
				return p;
		}
		return null;
	}

	@Override
	public Person findById(long pid) {
		ArrayList<Person> as = initialData.getEntityList(new Person());
		for(Person p : as) {
			if(p.getPid() == pid)
				return p;
		}
		return null;
	}
}