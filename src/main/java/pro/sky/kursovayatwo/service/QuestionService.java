package pro.sky.kursovayatwo.service;

import pro.sky.kursovayatwo.model.Question;

import java.util.HashSet;
import java.util.Optional;

public interface QuestionService {
    Question addQuestion(String questionText, String questionAnswer);

    void removeQuestion(String questionText, String questionAnswer);

    HashSet<Question> getAllQuestions();

    Question getRandomQuestion();

    boolean findQuestion(String questionText);

    boolean checkAnswer(String questionText, String questionAnswer);
}
