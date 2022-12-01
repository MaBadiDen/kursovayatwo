package pro.sky.kursovayatwo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.kursovayatwo.exception.NoQuestionFound;
import pro.sky.kursovayatwo.exception.QuestionAlreadyAdded;
import pro.sky.kursovayatwo.model.Question;
import pro.sky.kursovayatwo.service.JavaQuestionService;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {
    private final JavaQuestionService javaQuestionService = new JavaQuestionService();

    @AfterEach
    public void afterEach() {
        javaQuestionService.removeQuestion("2+2", "4");
    }

    @Test
    public void addTest() {
        javaQuestionService.addQuestion("2+2", "4");

        assertThatExceptionOfType(QuestionAlreadyAdded.class)
                .isThrownBy(() -> javaQuestionService.addQuestion("2+2", "4"));
        assertThatExceptionOfType(QuestionAlreadyAdded.class)
                .isThrownBy(() -> javaQuestionService.addQuestion("2+2", "5"));
        assertThat(javaQuestionService.getAllQuestions()).containsExactlyInAnyOrder(new Question("2+2", "4"));

    }

    @Test
    public void removeTest() {
        javaQuestionService.addQuestion("2+2", "4");
        assertThat(javaQuestionService.getAllQuestions()).contains(new Question("2+2", "4"));

        javaQuestionService.removeQuestion("2+2", "4");
        assertThatExceptionOfType(NoQuestionFound.class)
                .isThrownBy(() -> javaQuestionService.removeQuestion("2+2", "4"));
        javaQuestionService.addQuestion("2+2", "4");
    }

    @ParameterizedTest
    @MethodSource("questionsForTest")
    public void randomTest(Set<Question> questions) {
        questions
                .forEach(e -> javaQuestionService.addQuestion(e.getQuestion(), e.getAnswer()));


        assertThat(javaQuestionService.getRandomQuestion()).isIn(javaQuestionService.getAllQuestions());
    }

    public static Stream<Arguments> questionsForTest() {
        return Stream.of(
                Arguments.of(
                        Set.of(
                                new Question("2+2", "4"),
                                new Question("3+3", "6"),
                                new Question("4+4", "8")
                        )
                )
        );
    }
    @Test
    public void checkTest() {
        javaQuestionService.addQuestion("2+2", "4");

        assertThat(javaQuestionService.checkAnswer("2+2", "4")).isTrue();
        assertThat(javaQuestionService.checkAnswer("2+2", "5")).isFalse();
    }



}
