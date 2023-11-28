package com.innovaturelabs.training.examportal.repository;

import com.innovaturelabs.training.examportal.entity.Exam;
import com.innovaturelabs.training.examportal.view.ExamDetailView;
import com.innovaturelabs.training.examportal.view.ExamListView;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface ExamRepository extends Repository<Exam, Integer>{
	
	Collection<ExamListView> findAllByUserUserId(Integer userId);
	
	Collection<Exam> findAllByStatus(byte status);
	
	Optional<Exam>findByExamIdAndUserUserId(Integer examId, Integer userId);
	Optional<Exam>findByExamId(Integer examId);

	
	Exam save(Exam exam);
	
	void delete(Exam exam);
	
	void deleteByUserUserId(Integer userID);

	Optional<Exam> findByExamIdAndStatus(Integer examId, byte status);

	Collection<ExamListView> findAllByUserUserIdAndStatus(Integer currentUserId, byte status);


}