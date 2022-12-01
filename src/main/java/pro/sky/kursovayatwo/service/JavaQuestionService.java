package pro.sky.kursovayatwo.service;

import org.springframework.stereotype.Service;
import pro.sky.kursovayatwo.exception.NoQuestionFound;
import pro.sky.kursovayatwo.exception.NoQuestionsAdded;
import pro.sky.kursovayatwo.exception.QuestionAlreadyAdded;
import pro.sky.kursovayatwo.model.Question;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService{

    static HashSet<Question> questions = new LinkedHashSet<>();
    @Override
    public Question addQuestion(String questionText, String questionAnswer) {
        if(findQuestion(questionText)) {
            throw new QuestionAlreadyAdded();
        }
        Question question = new Question(questionText, questionAnswer);
        questions.add(question);
        return question;
    }
    @Override
    public void removeQuestion(String questionText, String questionAnswer) {
        Question question = new Question(questionText, questionAnswer);
        if(questions.contains(question)) {
            questions.remove(question);
        } else {
            throw new NoQuestionFound();
        }
    }
    @Override
    public HashSet<Question> getAllQuestions() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        if(questions.size() == 0) {
            throw new NoQuestionsAdded();
        }
        int a = 0;
        int b = questions.size();
        int rand = a + (int)(Math.random() * b);
        return (Question) questions.toArray()[rand];
    }

    @Override
    public boolean findQuestion(String questionText) {
        return questions.stream()
                .anyMatch(e -> e.getQuestion().equals(questionText));
    }

    @Override
    public boolean checkAnswer(String questionText, String questionAnswer) {
        if(!findQuestion(questionText)) {
            throw new NoQuestionFound();
        }
        Question question = questions.stream()
                .filter(e -> e.getQuestion().equals(questionText))
                .findAny()
                .get();
        return question.getAnswer().equals(questionAnswer);

    }
}
