package entity.exercise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERPROFILE")
public class UserProfile {
    @Id
    @GeneratedValue
    long id;
    @Column(name = "FIRSTNAME")
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String description;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<UserProfile> friends;
    @OneToOne(cascade = CascadeType.ALL)
    UserCredentials userCredentials;
    @OneToMany
    List<Comment> comments;

    public void addFriends(List<UserProfile> friends) {
        this.friends.addAll(friends);
    }

    public void addFriend(UserProfile friend) {
        this.friends.add(friend);
    }

    public UserProfile(String firstName, String lastName, LocalDate dateOfBirth, String description, List<UserProfile> friends, UserCredentials userCredentials) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.description = description;
        this.friends = friends;
        this.userCredentials = userCredentials;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", description='" + description + '\'' +
                ", userCredentials=" + userCredentials +
                '}';
    }
}
