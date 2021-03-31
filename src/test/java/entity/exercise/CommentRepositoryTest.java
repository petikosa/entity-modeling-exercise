package entity.exercise;

import entity.exercise.repo.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {DemoApplication.class})
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    void ContextLoads() {}

    @Test
    void findLatestComments() {
        commentRepository.findLatestComments()
                .forEach(c -> System.out.println(c.getTimestamp() + " - " + c.getText()));
    }
}
