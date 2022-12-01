package pro.sky.kursovayatwo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.kursovayatwo.exception.NoQuestionFound;
import pro.sky.kursovayatwo.exception.NoQuestionsAdded;
import pro.sky.kursovayatwo.exception.QuestionAlreadyAdded;
import pro.sky.kursovayatwo.model.Question;
import pro.sky.kursovayatwo.service.QuestionService;

import java.util.HashSet;

@RestController
@RequestMapping("/java")
public class JavaController {
    private final QuestionService questionService;

    public JavaController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public String add(@RequestParam("QuestionText") String questionText, @RequestParam("QuestionAnswer") String questionAnswer) {
        try {
            return questionService.addQuestion(questionText, questionAnswer).toString() + " успешно добавлен!";
        } catch (QuestionAlreadyAdded e) {
            return "Вопрос уже есть в базе!";
        }

    }

    @GetMapping
    public HashSet<Question> getQuestions() {

        return questionService.getAllQuestions();
    }

    @GetMapping("/remove")
    public String removeQuestion(@RequestParam("QuestionText") String questionText, @RequestParam("QuestionAnswer") String questionAnswer) {
        try {
            questionService.removeQuestion(questionText, questionAnswer);
        } catch (NoQuestionFound e) {
            return "Вопрос не обнаружен!";
        }
        return "Вопрос успешно удален!";
    }

    @GetMapping("/random")
    public String getRandomQuestion() {
        try {
            return questionService.getRandomQuestion().toString();
        } catch (NoQuestionsAdded e) {
            return "Вопросы еще не добавлены!";
        }

    }
    @GetMapping("/check")
    public String findQuestion(@RequestParam("QuestionText") String questionText, @RequestParam("QuestionAnswer") String questionAnswer) {
        boolean answer;
        try {
            answer = questionService.checkAnswer(questionText, questionAnswer);
        } catch (NoQuestionFound e) {
            return "Вопрос не обнаружен!";
        }
        if (answer) {
            return "Правильный ответ!";
        }
        return "Неправильный ответ!";

    }

}
