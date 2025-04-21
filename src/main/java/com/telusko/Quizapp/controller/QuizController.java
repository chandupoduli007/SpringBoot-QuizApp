package com.telusko.Quizapp.controller;

import com.telusko.Quizapp.Model.QuestionWrapper;
import com.telusko.Quizapp.Model.Response;
import com.telusko.Quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizservice;

    @PostMapping("create")
    public ResponseEntity <String> CreateQuiz(@RequestParam String category,@RequestParam int qnum, @RequestParam String title){
        return quizservice.CreateQuiz(category,qnum,title);
    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
        return quizservice.getQuiz(id);

     }

   @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submit(@PathVariable Integer id,@RequestBody List<Response> responses){
       return quizservice.calculate(id, responses);
   }
}
