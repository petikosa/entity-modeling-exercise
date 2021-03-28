package entity.exercise.services;

import entity.exercise.model.Comment;
import entity.exercise.repo.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Map<Comment, Integer> getMostCommentedPosts() {
        var posts = commentRepository.findAll().stream()
                .filter(Comment::isPost)
                .collect(Collectors.toMap(identity(), c -> c.getComments().size()));
        return posts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

}
