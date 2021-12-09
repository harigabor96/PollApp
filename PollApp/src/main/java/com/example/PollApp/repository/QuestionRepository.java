package com.example.PollApp.repository;

import com.example.PollApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findQuestionByQuestionId(Integer questionId);

    @Transactional
    void deleteByQuestionId(Integer questionId);
}