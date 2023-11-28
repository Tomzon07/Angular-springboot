package com.innovaturelabs.training.examportal.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.innovaturelabs.training.examportal.entity.Question;
import com.innovaturelabs.training.examportal.view.QuestionListView;

public interface QuestionRepository extends Repository<Question,Integer>{
	
	Collection<QuestionListView> findAllByUserUserId(Integer userId);
	
	@Query(value = "select * from question  where exam_id=:examId  ORDER BY RAND()", nativeQuery = true)
	Collection<Question> findByExamExamId(Integer examId);
	
	Collection<QuestionListView> findByExamExamIdAndUserUserId(Integer examId,Integer userId);
	
	Optional<Question> findByQuestionIdAndExamExamId(Integer questionId,Integer examId);
	
	Optional<Question> findByQuestionIdAndUserUserId(Integer questionId,Integer userId);
	
	
	
    Question save(Question question);
    
    void delete(Question question);
    
    void deleteByUserUserId(Integer userId); 
    
    Question findById(Integer questionId);
    
    Integer countByExamExamId(Integer examId);
}