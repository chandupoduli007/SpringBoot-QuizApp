package com.telusko.Quizapp.Repo;

import com.telusko.Quizapp.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz,Integer> {

}
