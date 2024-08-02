package com.flowerorder.model;

import java.sql.Timestamp;

public class Users {
	private int user_id; // Previous is id!!
	private String username;
	private String passwordHash;
	private String email;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String address;
	private Role role;
	private Timestamp created_at;

	public Users(String username, String passwordHash, String email, Role role) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.role = role;
	}

	public Users(String username, String passwordHash, String email, String phoneNumber, String firstName,
			String lastName, String address) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}

	
	public Users(String username, String passwordHash,Role role) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public int getId() {
		return user_id;
	}

	public void setId(int id) {
		this.user_id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
}
