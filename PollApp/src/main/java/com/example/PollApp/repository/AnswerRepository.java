package com.example.PollApp.repository;

import com.example.PollApp.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}