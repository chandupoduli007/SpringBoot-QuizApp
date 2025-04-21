package com.telusko.Quizapp.service;

import com.telusko.Quizapp.Model.Question;
import com.telusko.Quizapp.Model.QuestionWrapper;
import com.telusko.Quizapp.Model.Quiz;
import com.telusko.Quizapp.Model.Response;
import com.telusko.Quizapp.Repo.QuestionRepo;
import com.telusko.Quizapp.Repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
// to find the correct score
    public ResponseEntity<Integer> calculate(Integer id, List<Response> responses){
        Quiz q = quizrepo.findById(id).get();
        List<Question> questions = q.getQuestions();
        int right=0;
        int i=0;
        for(Response responsess:responses ){
            if(responsess.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
