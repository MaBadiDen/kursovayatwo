package pro.sky.kursovayatwo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.kursovayatwo.exception.NotEnoughQuestions;
import pro.sky.kursovayatwo.model.Question;
import pro.sky.kursovayatwo.service.ExaminerServiceImpl;
import pro.sky.kursovayatwo.service.JavaQuestionService;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExamTest {
    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final LinkedHashSet<Question> questions = new LinkedHashSet<>();


    @BeforeEach
    public void beforeEach() {
        questions.clear();

        questions.addAll(
                Stream.of(
                        new Question("2+2", "4"),
                        new Question("3+3", "6"),
                        new Question("4+4", "8")
                ).collect(Collectors.toSet())
        );
        when(javaQuestionService.getAllQuestions()).thenReturn(questions);
    }

    @Test
    public void getQuestionsTest() {
        assertThatExceptionOfType(NotEnoughQuestions.class)
                .isThrownBy(() -> examinerService.getQuestions(4));
        assertThatExceptionOfType(NotEnoughQuestions.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));
    }

    @Test
    public void randomTest() {
        when(javaQuestionService.getRandomQuestion()).thenReturn(
                new Question("2+2", "4"),
                new Question("2+2", "4"),
                new Question("2+2", "4"),
                new Question("3+3", "6"),
                new Question("3+3", "6"),
                new Question("4+4", "8")
        );

        assertThat(examinerService.getQuestions(3))
                .containsExactlyInAnyOrder(new Question("2+2", "4"), new Question("3+3", "6"), new Question("4+4", "8"))
                .hasSize(3);
    }

}
