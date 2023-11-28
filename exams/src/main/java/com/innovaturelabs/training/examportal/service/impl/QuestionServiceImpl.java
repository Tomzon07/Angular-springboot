package com.innovaturelabs.training.examportal.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.innovaturelabs.training.examportal.entity.Exam;
import com.innovaturelabs.training.examportal.entity.Question;
import com.innovaturelabs.training.examportal.entity.Result;
import com.innovaturelabs.training.examportal.entity.User;
import com.innovaturelabs.training.examportal.exception.NotFoundException;
import com.innovaturelabs.training.examportal.form.QuestionForm;
import com.innovaturelabs.training.examportal.repository.QuestionRepository;
import com.innovaturelabs.training.examportal.repository.ResultRepository;
import com.innovaturelabs.training.examportal.security.util.SecurityUtil;
import com.innovaturelabs.training.examportal.service.QuestionService;
import com.innovaturelabs.training.examportal.view.QuestionDetailView;
import com.innovaturelabs.training.examportal.view.QuestionListView;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ResultRepository repository;

	@Override
	public Collection<QuestionListView> list() {
		return questionRepository.findAllByUserUserId(SecurityUtil.getCurrentUserId());
	}

	@Override
	public QuestionDetailView add(QuestionForm form, Integer examId) {
		return new QuestionDetailView(
				questionRepository.save(new Question(form, examId, SecurityUtil.getCurrentUserId())));
	}

	@Override
	public Collection<QuestionListView> get(Integer examId) throws NotFoundException {
		return questionRepository.findByExamExamId(examId).stream().map(QuestionListView::new)
				.collect(Collectors.toList());
	}

	@Override
	public Question getQuestion(Integer questionId) {
		return questionRepository.findById(questionId);
	}

	@Override
	@Transactional
	public QuestionDetailView update(Integer questionId, QuestionForm form) throws NotFoundException {
		return questionRepository.findByQuestionIdAndUserUserId(questionId, SecurityUtil.getCurrentUserId())
				.map((question) -> {
					return new QuestionDetailView(questionRepository.save(question.update(form)));
				}).orElseThrow(NotFoundException::new);
	}

	@Override
	@Transactional
	public void delete(Integer questionId) throws NotFoundException {
		questionRepository
				.delete(questionRepository.findByQuestionIdAndUserUserId(questionId, SecurityUtil.getCurrentUserId())
						.orElseThrow(NotFoundException::new));
	}

	@Override
	public Map<String, Float> evalexam(List<Map> useranswer, Integer examId) {

		Map<String, Float> status = new HashMap<>();

		byte results;

		Iterator<Map> a = useranswer.iterator();

		float count = 0;
		Integer total = 0;
		while (a.hasNext()) {
			System.out.println();
			Map temp = a.next();
			Integer questionId = (Integer) temp.get("id");
			Optional<Question> qs = questionRepository.findByQuestionIdAndExamExamId(questionId, examId);
			String answer = qs.get().getAnswer();
			String userAnswer = (String) temp.get("answer");
			System.out.println(userAnswer);
			System.out.println(answer);
			if (answer.equals(userAnswer)) {
				count++;

			}
		}
		total = questionRepository.countByExamExamId(examId);

		status.put("total", (float) total);
		if (((count / total) * 100) >= 50) {

			System.out.println("passed");

			status.put("mark", count);
			
			status.put("percent", count*100/total);

			status.put("status", (float) 1);

			results = 1;
		} else {
			status.put("mark", count);
			System.out.println("failed");
			status.put("percent", count*100/total);
			status.put("status", (float) 0);
			results = 0;
		}

		Result result = new Result();
		result.setExam(new Exam(examId));
		result.setUser(new User(SecurityUtil.getCurrentUserId()));
		result.setStatus(results);
		result.setMark(count*100/total);
		repository.save(result);

		return status;
	}

}