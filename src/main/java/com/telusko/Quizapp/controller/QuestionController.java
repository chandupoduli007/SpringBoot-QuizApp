package com.telusko.Quizapp.controller;

import com.telusko.Quizapp.Model.Question;
import com.telusko.Quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getallQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getByCategory(@PathVariable String category){
            return questionService.getByCategory(category);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
       return questionService.addQuestion(question);
    }

    @PutMapping("updateQuestion/{id}")
    public ResponseEntity<String>  updateQuestion(@PathVariable Integer id, @RequestBody  Question question){
        return questionService.updateQuestion(id,question);
    }

    @DeleteMapping("deleteQuestion/{id}")
    public ResponseEntity<String>  deleteQuestion(@PathVariable Integer id,Question question){
        return questionService.deleteQuestion(id,question);
    }
}
