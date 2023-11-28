package com.innovaturelabs.training.examportal.entity;


import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.innovaturelabs.training.examportal.entity.Exam.Status;
import com.innovaturelabs.training.examportal.form.QuestionForm;


@Entity
public class Question {
	
	
	 public static enum Status {
	       DELETED((byte) 0),
	       ACTIVE((byte) 1);

	    public final byte value;

	    private Status(byte value) {
	       this.value = value;
	    }
    }
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer questionId;
	private String question;
	private String  optionsA;
	private String  optionsB;
	private String  optionsC;
	private String  optionsD;
	private String  answer;
	private byte status;

	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Exam exam;
	
	
	public Question() {
			
	}
	public Question(Integer questionId) {
		this.questionId = questionId;
	}
	
	public Question(QuestionForm form,Integer examId,Integer userId) {
		this.user =new User(userId);
		this.exam= new Exam(examId);
		this.question = form.getQuestion();
		this.optionsA = form.getOptionsA();
		this.optionsB = form.getOptionsB();
		this.optionsC = form.getOptionsC();
		this.optionsD = form.getOptionsD();
		this.answer = form.getAnswer();
		
		this.status = Status.ACTIVE.value;
		
		Date dt = new Date();
		this.createDate = dt;
		this.updateDate = dt;
	}


	public Question update(QuestionForm form) {
		this.question = form.getQuestion();
		this.optionsA = form.getOptionsA();
		this.optionsB =form.getOptionsB();
		this.optionsC = form.getOptionsC();
		this.optionsD = form.getOptionsD();
		this.answer = form.getAnswer();
		
		Date dt = new Date();
		this.updateDate = dt;
		
		return this;
		
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOptionsA() {
		return optionsA;
	}
	public void setOptionsA(String optionsA) {
		this.optionsA = optionsA;
	}
	public String getOptionsB() {
		return optionsB;
	}
	public void setOptionsB(String optionsB) {
		this.optionsB = optionsB;
	}
	public String getOptionsC() {
		return optionsC;
	}
	public void setOptionsC(String optionsC) {
		this.optionsC = optionsC;
	}
	public String getOptionsD() {
		return optionsD;
	}
	public void setOptionsD(String optionsD) {
		this.optionsD = optionsD;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash  += (questionId !=null ? questionId.hashCode() : 0);
		return hash;
	}
	@Override
	public  boolean equals(Object object) {
		if (!(object instanceof Question)) {
			return false;
		}
		return Objects.equals(questionId, ((Question) object).questionId);
	}
	
	@Override
	public String toString() {
		return "Job [createDate =" + createDate + ", questionId=" +  questionId + " , question=" + question + ", optionsA=" + optionsA 
				+ ", optionsB =" + optionsB + " , optionsC=" + optionsC + ", optionsD=" + optionsD + ", answer=" + answer + ",status="+ status +",updateDate=" + updateDate + "]";
	}
	
	
		

}