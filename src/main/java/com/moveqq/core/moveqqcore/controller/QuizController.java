package com.moveqq.core.moveqqcore.controller;

import com.moveqq.core.moveqqcore.model.ResponseResult;
import com.moveqq.core.moveqqcore.model.dto.internal.Question;
import com.moveqq.core.moveqqcore.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "http://localhost:4200", maxAge = 3500)
@RestController
@RequestMapping(path = "/quiz")
public class QuizController {

    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PutMapping("/question")
    public ResponseEntity<ResponseResult> createQuestion(@RequestBody Question question) {
        boolean result = quizService.createQuestion(question);
        if (result)
            return new ResponseEntity<>(new ResponseResult(ResponseResult.ResultType.OK), HttpStatus.CREATED);
        return new ResponseEntity<>(new ResponseResult(ResponseResult.ResultType.FAILED), HttpStatus.EXPECTATION_FAILED);
    }

}
