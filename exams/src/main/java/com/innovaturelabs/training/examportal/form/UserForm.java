
package com.innovaturelabs.training.examportal.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.innovaturelabs.training.examportal.form.validaton.Password;


public class UserForm {

    @NotBlank
    @Size(max = 12)
    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$",
    message = "Username must be of 6 to 12 length with no special characters")
    private String name;
    @NotBlank
    @Size(max=255)
    @Email
    private String email;
    @Password
    @Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,12}$",
    message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private String password;
    private byte role;
	public byte getRole() {
		return role;
	}
	public void setRole(byte role) {
		this.role = role;
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
    
   
}
