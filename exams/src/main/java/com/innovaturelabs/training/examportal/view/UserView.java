/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaturelabs.training.examportal.view;

import com.innovaturelabs.training.examportal.entity.User;
import com.innovaturelabs.training.examportal.json.Json;

import java.util.Date;


public class UserView {

    private final int userId;
    private final String name;
    private final String email;
    private final short status;
    private final short role;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;

    public UserView(User user){
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.role=user.getRole();
        this.createDate = user.getCreateDate();
        this.updateDate = user.getUpdateDate();
    }

    public int getUserId() {
        return userId;
    }

    public short getRole() {
		return role;
	}

	public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
   
    public short getStatus() {
        return status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}
