package com.innovaturelabs.training.examportal.form;




import com.innovaturelabs.training.examportal.json.Json;


import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;




public class UserProfileForm {
	
	
	
	@NotBlank
	@Size(max =50)
	@Pattern(regexp = "^[a-zA-Z0-9]{6,12}$",
    message = "firstname must be of 6 to 12 length with no special characters")
	private String firstname;
	@Size(max = 50)
	@Pattern(regexp = "^[a-zA-Z0-9]{6,12}$",
    message = "lastname must be of 6 to 12 length with no special characters")
	private String lastname;
	@Json.DateFormat
	private Date dob;
	@Size(max=10)
//	@Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
	private String phone;
	@NotBlank
	@Size(max=250)
	@Email
	private String email;
	@Size(max=250)
	private String address;
	@Size(max = 8)
    @Pattern(regexp = "^(\\d+[ -]?)*\\d$")
	private String zipcode;
	@Size(max=50)
	private String state;
	@Size(max=50)
	private String country;
//	private byte add;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
//	public byte getAdd() {
//		return add;
//	}
//	public void setAdd(byte add) {
//		this.add = add;
//	}
	
	

}