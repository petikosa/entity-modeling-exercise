package entity.exercise;

import entity.exercise.model.Comment;
import entity.exercise.services.CommentService;
import entity.exercise.services.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {DemoApplication.class, CommentService.class})
public class CommentStreamsTest {

    @Autowired
    CommentService commentService;

    @Test
    void ContextLoads() {}

    @Test
    void getPostWithMostComments() {
        var max = 9999;
        for (Map.Entry<Comment, Integer> e : commentService.getMostCommentedPosts().entrySet()) {
            System.out.println(e.getValue() + " - " + e.getKey().getText());
            assertTrue(max >= e.getValue());
            max = e.getValue();
        }
    }

    @Test
    void getMostLikedComments() {
        var max = 9999;
        for (Map.Entry<Comment, Integer> e : commentService.getMostLikedComments().entrySet()) {
            System.out.println(e.getValue() + " - " + e.getKey().getText());
            assertTrue(max >= e.getValue());
            max = e.getValue();
        }
    }
}
