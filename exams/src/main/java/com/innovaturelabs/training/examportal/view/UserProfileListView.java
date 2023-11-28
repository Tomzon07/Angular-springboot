package com.innovaturelabs.training.examportal.view;


import java.util.Date;

import com.innovaturelabs.training.examportal.entity.UserProfile;
import com.innovaturelabs.training.examportal.form.UserProfileForm;
import com.innovaturelabs.training.examportal.json.Json;

public class UserProfileListView {


	private final int userprofileId;
	private final String firstname;
	private final String lastname;
	private final Date dob;
	private final String phone;
	private final String adddress;
	private final String email;
	private final String zipcode;
	private final String state;
	private final String country;
	private final byte  status;
	
	

	@Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;
    
    
    
    public UserProfileListView( int userprofileId, String firstname, String lastname, Date dob, String phone,
			String adddress, String email, String zipcode, String state, String country, byte status,Date createDate,
			Date updateDate) {
		super();
		this.userprofileId = userprofileId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.phone = phone;
		this.adddress = adddress;
		this.email = email;
		this.zipcode = zipcode;
		this.state = state;
		this.country = country;
		this.status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}



	public UserProfileListView(UserProfile userProfile) {
		this.userprofileId = userProfile.getUserprofileId();
		this.firstname = userProfile.getFirstname();
		this.lastname = userProfile.getLastname();
		this.dob = userProfile.getDob();
		this.phone =userProfile. getPhone();
		this.adddress = userProfile.getAddress();
		this.email = userProfile.getEmail();
		this.zipcode =userProfile.getZipcode();
		this.state =userProfile.getState();
		this.country = userProfile.getCountry();
		this.status = userProfile.getStatus();
		this.createDate =userProfile.getCreateDate();
		this.updateDate = userProfile.getUpdateDate();
		
		
		 
	}


	public String getFirstname() {
		return firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public Date getDob() {
		return dob;
	}



	public String getPhone() {
		return phone;
	}



	public String getAdddress() {
		return adddress;
	}



	public String getEmail() {
		return email;
	}



	public String getZipcode() {
		return zipcode;
	}



	public String getState() {
		return state;
	}



	public String getCountry() {
		return country;
	}



	public byte getStatus() {
		return status;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public Date getUpdateDate() {
		return updateDate;
	}



	public int getUserprofileId() {
		return userprofileId;
	}
	
	
		
}