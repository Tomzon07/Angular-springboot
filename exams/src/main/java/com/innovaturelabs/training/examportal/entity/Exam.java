package com.innovaturelabs.training.examportal.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import com.innovaturelabs.training.examportal.entity.User.Status;
import com.innovaturelabs.training.examportal.form.ExamForm;


@Entity
public class Exam {
	
	public static enum Status{
		DELETED((byte) 0),
		ACTIVE((byte) 1);
		
		public final byte value;
		
		private Status(byte value) {
			this.value = value;
		}
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer examId;
	private String name;
	private String description;
	private byte status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	private User user;
	
	public Exam() {
}
	public Exam(Integer examId) {
		this.examId = examId;
	}
	
	public Exam(ExamForm form, Integer userId) {
		this.user = new User(userId);
		
		this.name = form.getName();
		this.description = form.getDescription();
		
		this.status = Status.ACTIVE.value;
		
		Date dt = new Date();
		this.createDate = dt;
		this.updateDate = dt;
		
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Exam update(ExamForm form) {
		this.name = form.getName();
		this.description = form.getDescription();
		
		Date dt= new Date();
		this.updateDate = dt;
		 return this;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public void setCreatDate(Date creatDate) {
		this.createDate = creatDate;
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
		hash  += (examId !=null ? examId.hashCode() : 0);
		return hash;
	}
	@Override
	public  boolean equals(Object object) {
		if (!(object instanceof Exam)) {
			return false;
		}
		return Objects.equals(examId, ((Exam) object).examId);
	}
	
	@Override
	public String toString() {
		return "Job [createDate =" + createDate + ", examId=" +  examId + " , nam=" + name + ", description=" + description 
				+ ", status="+ status +",updateDate=" + updateDate + "]";
	}
	
	
}


	