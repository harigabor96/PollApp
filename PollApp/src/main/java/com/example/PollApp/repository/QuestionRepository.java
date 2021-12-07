package com.example.PollApp.repository;

import com.example.PollApp.model.AppUser;
import com.example.PollApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findQuestionByQuestionId(Integer questionId);
}