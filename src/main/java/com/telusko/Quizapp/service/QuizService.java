package com.telusko.Quizapp.service;

import com.telusko.Quizapp.Model.Question;
import com.telusko.Quizapp.Model.QuestionWrapper;
import com.telusko.Quizapp.Model.Quiz;
import com.telusko.Quizapp.Model.response;
import com.telusko.Quizapp.Repo.QuestionRepo;
import com.telusko.Quizapp.Repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

@Service
public class QuizService {

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    QuizRepo quizrepo;


    public ResponseEntity<String> CreateQuiz(String category, int qnum, String title) {
        List<Question> questions = questionRepo.findRandomQuestionsByCategory(category, qnum);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizrepo.save(quiz);
        return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {

        Optional<Quiz> quiz = quizrepo.findById(id);
        List<Question> questionfromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionforuser = new ArrayList<>();
        for (Question q : questionfromDb) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionforuser.add(qw);
        }
        return new ResponseEntity<>(questionforuser, HttpStatus.OK);


    }
}
