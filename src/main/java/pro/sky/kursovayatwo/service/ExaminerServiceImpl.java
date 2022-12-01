package pro.sky.kursovayatwo.service;

import org.springframework.stereotype.Service;
import pro.sky.kursovayatwo.exception.NotEnoughQuestions;
import pro.sky.kursovayatwo.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    QuestionService questionService = new JavaQuestionService();

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> examQuestions = new HashSet<>();
        Set<Question> questions = questionService.getAllQuestions();
        if(amount > questions.size() || amount < 1) {
            throw new NotEnoughQuestions();
        }

        for (int i = 0; i < amount; i++) {
            Question question = questionService.getRandomQuestion();
            if(examQuestions.contains(question)) {
                i--;
            } else {
                examQuestions.add(question);
            }

        }
        return examQuestions;
    }
}
