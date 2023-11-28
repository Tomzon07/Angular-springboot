
package com.innovaturelabs.training.examportal.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.innovaturelabs.training.examportal.form.UserForm;


@Entity
public class User {

    public static enum Status {
        INACTIVE((byte) 0),
        ACTIVE((byte) 1);
    	

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }   
    }
    
    public static enum Role {
    	 ADMIN((byte) 0),
         USER((byte) 1);
     	

         public final byte value;

         private Role(byte value) {
             this.value = value;
         }   
		
	}
    
    public static enum Add{
		ADDING((byte) 0),
		ADDED((byte) 1);
		 
	 public final byte value;
	 
	 private Add(byte value) {
		 this.value = value;
	 }
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private byte status;
    private byte role;
    private byte profileStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    
    private String ResetPasswordToken;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(String name,String email,String password,byte role) {
 
    	this.name=name;
        this.email = email;
        this.password = password;
        this.role=role;
        this.status = Status.ACTIVE.value;
        this.profileStatus=Add.ADDING.value;
        

        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }
    
    
    
    public byte getRole() {
		return role;
	}

	public void setRole(byte role) {
		this.role = role;
	}

	public User update(UserForm form) {
    	 this.name = form.getName();
         this.email = form.getEmail();
         this.password = form.getPassword();

         this.status = Status.ACTIVE.value;
         
         Date dt = new Date();
         this.updateDate=dt;
         
         return this;
    	
    }

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return Objects.equals(this.userId, other.userId);
    }

    @Override
    public String toString() {
        return "com.innovaturelabs.training.contacts.entity.User[ userId=" + userId + " ]";
    }

	public byte getProfileStatus() {
		return profileStatus;
	}

	public void setProfileStatus(byte profileStatus) {
		this.profileStatus = profileStatus;
	}

	public String getResetPasswordToken() {
		return ResetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		ResetPasswordToken = resetPasswordToken;
	}
    
}
