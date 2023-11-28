package com.innovaturelabs.training.examportal.entity;


import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.innovaturelabs.training.examportal.form.UserProfileForm;

@Entity
public class UserProfile {

	public static enum Status {
		DELETED((byte) 0), ACTIVE((byte) 1);

		public final byte value;

		private Status(byte value) {
			this.value = value;
		}

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userprofileId;
	private String firstname;
	private String lastname;
	private Date dob;
	private String email;
	private String phone;
	private String address;
	private String zipcode;
	private String state;
	private String country;
	private byte status;
	@Column(nullable = true, length = 64)
	private String photos;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private User user;

	public UserProfile() {

	}

	public UserProfile(Integer userprofileId) {
		this.userprofileId = userprofileId;
	}

	public UserProfile(UserProfileForm form, Integer userId) {
		this.firstname = form.getFirstname();
		this.lastname = form.getLastname();
		this.dob = form.getDob();
		this.email = form.getEmail();
		this.phone = form.getPhone();
		this.address = form.getAddress();
		this.zipcode = form.getZipcode();
		this.state = form.getState();
		this.country = form.getCountry();

		this.status = Status.ACTIVE.value;

		this.user = new User(userId);

		Date dt = new Date();
		this.createDate = dt;
		this.updateDate = dt;

	}

	public UserProfile update(UserProfileForm form) {
		this.firstname = form.getFirstname();
		this.lastname = form.getLastname();
		this.dob = form.getDob();
		this.email = form.getEmail();
		this.phone = form.getPhone();
		this.address = form.getAddress();
		this.zipcode = form.getZipcode();
		this.state = form.getState();
		this.country = form.getCountry();

		Date dt = new Date();
		this.updateDate = dt;

		return this;

	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public Integer getUserprofileId() {
		return userprofileId;
	}

	public void setUserprofileId(Integer userprofileId) {
		this.userprofileId = userprofileId;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (userprofileId != null ? userprofileId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserProfile)) {
			return false;
		}
		return Objects.equals(userprofileId, ((UserProfile) object).userprofileId);
	}

	@Override
	public String toString() {
		return "UserProfile [userprofileId=" + userprofileId + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", dob=" + dob + ", email=" + email + ", phone=" + phone + ", address=" + address + ", zipcode="
				+ zipcode + ", state=" + state + ", country=" + country + ", status=" + status + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", user=" + user + "]";
	}

	@Transient
	public String getPhotosImagePath() {
		if (photos == null || userprofileId == null)
			return null;
		return "/userprofile - photos/" + userprofileId + photos;
	}

}