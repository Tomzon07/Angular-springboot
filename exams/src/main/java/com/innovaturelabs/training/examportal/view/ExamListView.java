package com.innovaturelabs.training.examportal.view;

import java.util.Date;
import com.innovaturelabs.training.examportal.json.Json;
import com.innovaturelabs.training.examportal.entity.Exam;

public class ExamListView {
	
	
	private final int examId;
	private final String name;
	private final String description;
	private final byte Status;
	
	@Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;
    
	public ExamListView(int examId, String name, String description, byte status, Date createDate, Date updateDate) {
		this.examId = examId;
		this.name = name;
		this.description = description;
		Status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	
	public ExamListView(Exam exam) {
		this.examId=exam.getExamId();
		this.name=exam.getName();
		this.description=exam.getDescription();
		this.Status=exam.getStatus();
		this.createDate=exam.getCreateDate();
		this.updateDate=exam.getUpdateDate();
	}

	public int getExamId() {
		return examId;
	}

	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public byte getStatus() {
		return Status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	
	
}