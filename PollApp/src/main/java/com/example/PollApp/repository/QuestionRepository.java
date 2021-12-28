package com.example.PollApp.repository;

import com.example.PollApp.DTO.PollListDTO;
import com.example.PollApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Question findQuestionByQuestionId(Integer questionId);

    @Transactional
    void deleteByQuestionId(Integer questionId);

    @Query("SELECT q.questionId AS questionId , q.question AS question, u.username AS creator, COUNT(v) AS voteCount " +
           "FROM Question q " +
           "LEFT JOIN Vote v ON q.questionId = v.questionId " +
           "LEFT JOIN AppUser u ON q.creatorId = u.userId " +
           "GROUP BY q.questionId ,q.question, u.username")
    List<PollListDTO> getPollList();
}