package pro.sky.kursovayatwo.service;

import pro.sky.kursovayatwo.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
