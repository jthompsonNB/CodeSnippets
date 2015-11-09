package com.qa.generic.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This Entity holds all the information relating to an address.
 * 
 * @author jthompson
 */
@Entity
@Table(name="address")
@NamedQueries({
	@NamedQuery(name=Address.FIND_BY_AID, query="SELECT a FROM a WHERE a.aid =: aid"),
	@NamedQuery(name=Address.FIND_BY_POSTCODE, query="SELECT a FROM a WHERE a.postcode =: postcode"),
	@NamedQuery(name=Address.FIND_BY_LINE1_AND_POSTCODE, query="SELECT a FROM a WHERE a.line1 =: line1 AND a.postcode =: postcode")
})
public class Address {
	public static final String FIND_BY_AID = "Address.findByAid";
	public static final String FIND_BY_POSTCODE = "Address.findByPostcode";
	public static final String FIND_BY_LINE1_AND_POSTCODE = "Address.findByLine1AndPostcode";
	@Id
	@Column(name="aid", nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long aid;
	@Column(name="line1", nullable=false, length=225)
	@NotNull
	@Size(max=225)
	private String line1;
	@Column(name="line2", nullable=true, length=225)
	@Size(max=225)
	private String line2;
	@Column(name="city", nullable=false, length=225)
	@NotNull
	@Size(max=225)
	private String city;
	@Column(name="county", nullable=false, length=225)
	@NotNull
	@Size(max=225)
	private String county;
	@Column(name="postcode", nullable=false, length=6)
	@NotNull
	@Size(min=6, max=6)
	private String postcode;
	
	/**
	 *  This constructor creates an empty address.
	 */
	public Address() {}
	
	/**
	 * This constructor creates a 1 line address.
	 * 
	 * @param line1
	 * @param city
	 * @param county
	 * @param postcode
	 */
	public Address(String line1, String city, String county, String postcode) {
		this.line1 = line1;
		this.city = city;
		this.county = county;
		this.postcode = postcode;
	}
	
	/**
	 * This constructor creates a 2-line address for businesses and flats.
	 * 
	 * @param line1
	 * @param line2
	 * @param city
	 * @param county
	 * @param postcode
	 */
	public Address(String line1, String line2, String city, String county, String postcode) {
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.county = county;
		this.postcode = postcode;
	}

	public long getAid() { return aid; }
	public String getLine1() { return line1; }
	public String getLine2() { return line2; }
	public String getCity() { return city; }
	public String getCounty() { return county; }
	public String getPostcode() { return postcode; }

	public void setLine1(String line1) { this.line1 = line1; }
	public void setLine2(String line2) { this.line2 = line2; }
	public void setCity(String city) { this.city = city; }
	public void setCounty(String county) { this.county = county; }
	public void setPostcode(String postcode) { this.postcode = postcode; }
	
	/**
	 * This overridden equals method first tries to cast the passed in object to an Address, returning false if it fails,
	 * Then it checks to see if the aid's are the same returning true if so. 
	 */
	@Override
	public boolean equals(Object obj) {
		try {
			Address a = (Address) obj;
			return aid == a.getAid();
		} catch (ClassCastException cce) {
			return false;
		}
	}
}