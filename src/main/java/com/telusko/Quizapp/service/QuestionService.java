package com.telusko.Quizapp.service;

import com.telusko.Quizapp.Model.Question;
import com.telusko.Quizapp.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo repo;



    public List<Question> getallQuestions() {
      return  repo.findAll();
    }

    public List<Question> getByCategory(String category){
        return repo.findByCategory(category);
    }

    public String addQuestion(Question question){
       repo.save(question);
       return "Success";
    }

    public String updateQuestion(Integer id, Question question) {


        if(repo.findById(id).isPresent()){
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

        }
        else{
            throw new RuntimeException("id not found");
        }
        return "updated";
    }

    public String deleteQuestion(Integer id, Question question) {
        if(repo.findById(id).isPresent()){
            repo.delete(question);
        }
        else{
            throw new RuntimeException("id is not found");
        }
        return "deleted";
    }
}
