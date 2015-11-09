package com.qa.generic.repository;

import java.util.List;
import com.qa.generic.entities.Person;

public interface PersonRepository {
	public List<Person> findByFullName(String fname, String sname);
	public Person findByEmail(String email);
	public Person findById(long pid);
}