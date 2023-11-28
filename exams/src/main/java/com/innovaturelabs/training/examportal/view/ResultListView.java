package com.innovaturelabs.training.examportal.view;

import com.innovaturelabs.training.examportal.entity.Result;

public class ResultListView {

	private final int resultId;

	private final byte status;

	private final int userId;

	private final int examId;

	private final String examName;
	
	private final Float percentage;

	public ResultListView(Result result) {

		this.resultId = result.getResultId();
		this.status = result.getStatus();
		this.userId = result.getUser().getUserId();
		this.examId = result.getExam().getExamId();
		this.examName = result.getExam().getName();
		this.percentage=result.getMark();
	}

	public String getExamName() {
		return examName;
	}

	public int getResultId() {
		return resultId;
	}

	public byte getStatus() {
		return status;
	}

	public int getUserId() {
		return userId;
	}

	public int getExamId() {
		return examId;
	}

	public Float getPercentage() {
		return percentage;
	}

}