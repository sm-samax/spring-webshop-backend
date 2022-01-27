package com.samax.tech.webs.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Client {

	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private LocalDate dateOfBirth;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private String zip;
	
	public Client() {}
	
	public Client(String firstName, String lastName, LocalDate dateOfBirth, String address, String city, String state,
			String zip) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
