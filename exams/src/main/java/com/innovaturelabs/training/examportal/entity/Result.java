package com.innovaturelabs.training.examportal.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Result {

	public static enum Status {
		INACTIVE((byte) 0), ACTIVE((byte) 1);

		public final byte value;

		private Status(byte value) {
			this.value = value;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resultId;

	private Float mark;

	private byte status;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Exam exam;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private User user;

	public Result() {

	}

	public Result(Integer resultId) {
		this.resultId = resultId;
	}

	public Integer getResultId() {
		return resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Float getMark() {
		return mark;
	}

	public void setMark(Float mark) {
		this.mark = mark;
	}

}