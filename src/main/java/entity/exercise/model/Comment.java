package entity.exercise.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue
    long id;
    String title;
    String text;
    LocalDate timestamp;
    @OneToOne(cascade = {CascadeType.ALL})
    UserProfile user;
    @OneToMany(cascade = {CascadeType.ALL})
    List<UserProfile> likes;

    public Comment(String title, String text, LocalDate timestamp, UserProfile user, List<UserProfile> likes) {
        this.title = title;
        this.text = text;
        this.timestamp = timestamp;
        this.user = user;
        this.likes = likes;
    }
}
