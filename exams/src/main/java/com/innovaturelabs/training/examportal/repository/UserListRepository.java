package com.innovaturelabs.training.examportal.repository;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.innovaturelabs.training.examportal.entity.Question;

public interface UserListRepository extends Repository<Question, Integer> {

	@Query(value = "select * from question  where exam_id=:examId  ORDER BY RAND()", nativeQuery = true)
	Collection<Question> findByExamExamId(Integer examId);

	Question save(Question question);

}