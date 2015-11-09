package com.qa.generic.entities;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This Entity holds all of the relevant information relating to a person.
 * 
 * @author jthompson
 */
@Entity
@Table(name="person")
@NamedQueries({
	@NamedQuery(name=Person.FIND_BY_PID, query="SELECT p FROM p WHERE p.pid =: pid"),
	@NamedQuery(name=Person.FIND_BY_FULL_NAME, query="SELECT p FROM p WHERE p.fname =: fname AND p.sname := sname"),
	@NamedQuery(name=Person.FIND_BY_EMAIL, query="SELECT p FROM p WHERE p.email =: email")
})
public class Person {
	public static final String FIND_BY_PID = "Person.findById";
	public static final String FIND_BY_FULL_NAME = "Person.findByFullName";
	public static final String FIND_BY_EMAIL = "Person.findByEmail";
	@Id
	@Column(name="pid", nullable=false, unique=true)
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pid;
	@Column(name="fname", nullable=false, length=225)
	@NotNull
	@Size(max=225)
	private String fname;
	@Column(name="sname", nullable=false, length=225)
	@NotNull
	@Size(max=225)
	private String sname;
	@Column(name="phone", nullable=false, length=11)
	@NotNull
	@Size(max=11)
	private String phone;
	@Column(name="email", nullable=false, length=225)
	@NotNull
	@Size(max=225)
	private String email;
	@Column(name="dateadded", nullable=false)
	@NotNull
	private Calendar dateadded;
	@ManyToOne
	@JoinColumn(name="aid", nullable=false)
	@NotNull
	private Address address;
	
	/**
	 * The default constructor.
	 * This will be used when we just want an empty entity to use for setting the type of entity we want the implementation to work with.
	 */
	public Person() {}
	
	/**
	 * This constructor is used when we want to set the time stamp at the time of creation.
	 * 
	 * @param fname
	 * @param sname
	 * @param phone
	 * @param email
	 * @param address
	 */
	public Person(String fname, String sname, String phone, String email, Address address) {
		this.fname = fname;
		this.sname = sname;
		this.phone = phone;
		this.email = email;
		this.address = address;
		dateadded = Calendar.getInstance();
	}
	
	/**
	 * This is the constructor that should be used when we want to set the time stamp ourselves.
	 * 
	 * @param fname
	 * @param sname
	 * @param phone
	 * @param email
	 * @param dateadded
	 * @param address
	 */
	public Person(String fname, String sname, String phone, String email, Calendar dateadded, Address address) {
		this.fname = fname;
		this.sname = sname;
		this.phone = phone;
		this.email = email;
		this.dateadded = dateadded;
		this.address = address;
	}

	public long getPid() { return pid; }
	public String getFname() { return fname; }
	public String getSname() { return sname; }
	public String getPhone() { return phone; }
	public String getEmail() { return email; }
	public Calendar getDateadded() { return dateadded; }
	public Address getAddress() { return address; }

	public void setFname(String fname) { this.fname = fname; }
	public void setSname(String sname) { this.sname = sname; }
	public void setPhone(String phone) { this.phone = phone; }
	public void setEmail(String email) { this.email = email; }
	public void setDateadded(Calendar dateadded) { this.dateadded = dateadded; }
	public void setAddress(Address address) { this.address = address; }
	
	/**
	 * This override for the equals method first attempts to cast the object as a person, returning false if it fails.
	 * If the cast succeeds then the pid values will be checked returning true if they are the same, false if not.
	 */
	@Override
	public boolean equals(Object obj) {
		try {
			Person p = (Person) obj;
			return pid == p.getPid();
		} catch (ClassCastException cce) {
			return false;
		}
	}
}