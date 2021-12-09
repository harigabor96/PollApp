package com.example.PollApp.repository;

import com.example.PollApp.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllByQuestionId(Integer questionId);

    @Transactional
    void deleteAllByQuestionId(Integer questionId);
}