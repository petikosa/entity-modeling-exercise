package entity.exercise;

import entity.exercise.services.CommentService;
import entity.exercise.services.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {DemoApplication.class, CommentService.class})
public class CommentStreamsTest {

    @Autowired
    CommentService commentService;

    @Test
    void ContextLoads() {}

    @Test
    void getPostWithMostComments() {
        commentService.getMostCommentedPosts().forEach((k, v) -> System.out.println(v + " - " + k.getText()));
    }
}
