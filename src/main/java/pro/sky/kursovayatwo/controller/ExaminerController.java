package pro.sky.kursovayatwo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.kursovayatwo.model.Question;
import pro.sky.kursovayatwo.service.ExaminerService;

import java.util.Collection;

@RestController
public class ExaminerController {
    private final ExaminerService examinerService;

    public ExaminerController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> getQuestions (@PathVariable("amount") int amount) {
            return examinerService.getQuestions(amount);
    }
}
