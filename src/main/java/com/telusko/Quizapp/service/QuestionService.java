package com.telusko.Quizapp.service;

import com.telusko.Quizapp.Model.Question;
import com.telusko.Quizapp.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo repo;



    public ResponseEntity<List<Question> >getallQuestions() {
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question> >getByCategory(String category){
        try{
            return new ResponseEntity<>(repo.findByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<String>  addQuestion(Question question){
        try{
            repo.save(question);
           return new ResponseEntity<>("Success",HttpStatus.CREATED);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<String>  updateQuestion(Integer id, Question question) {
        try {
            if (repo.findById(id).isPresent()) {
                Question existingQuestion = repo.findById(id).get();
                existingQuestion.setQuestionTitle(question.getQuestionTitle());
                existingQuestion.setOption1(question.getOption1());
                existingQuestion.setOption2(question.getOption2());
                existingQuestion.setOption3(question.getOption3());
                existingQuestion.setOption4(question.getOption4());
                existingQuestion.setRightAnswer(question.getRightAnswer());
                existingQuestion.setDifficultyLevel(question.getDifficultyLevel());
                existingQuestion.setCategory(question.getCategory());
                repo.save(existingQuestion);
                return new ResponseEntity<>("updated", HttpStatus.ACCEPTED);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>("not updated id not found",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String>  deleteQuestion(Integer id, Question question) {
        try {
        if (repo.findById(id).isPresent()) {
            repo.delete(question);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
    }catch (Exception e)
        {
            e.printStackTrace();

        }
        return new ResponseEntity<>("id not found",HttpStatus.BAD_REQUEST);
    }
}
