package entity.exercise.repo;

import entity.exercise.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAll();

    @Query("select c from Comment c order by c.timestamp desc")
    List<Comment> findLatestComments();
}
