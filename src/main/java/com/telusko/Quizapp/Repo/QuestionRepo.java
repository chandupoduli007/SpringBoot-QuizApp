package com.telusko.Quizapp.Repo;

import com.telusko.Quizapp.Model.Question;
import com.telusko.Quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepo  extends JpaRepository<Question,Integer>  {

    List<Question> findByCategory(String category);

    @Query(value ="SELECT * from question q Where q.category=:category ORDER BY RANDOM() LIMIT :qnum ",nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int qnum);
}
