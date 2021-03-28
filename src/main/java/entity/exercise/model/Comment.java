package entity.exercise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue
    long id;
    String text;
    LocalDate timestamp;
    @ManyToOne
    UserProfile user;
    @ManyToMany
    List<UserProfile> likes;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Comment> comments;
    boolean isPost = false;

    public Comment(String text, LocalDate timestamp, UserProfile user, List<UserProfile> likes, List<Comment> comments) {
        this.text = text;
        this.timestamp = timestamp;
        this.user = user;
        this.user.addComment(this);
        this.likes = likes;
        this.comments = comments;
    }

    public Comment(String text, LocalDate timestamp, UserProfile user, List<UserProfile> likes, List<Comment> comments, boolean isPost) {
        this.text = text;
        this.timestamp = timestamp;
        this.user = user;
        this.user.addComment(this);
        this.likes = likes;
        this.comments = comments;
        this.isPost = isPost;
    }
}
