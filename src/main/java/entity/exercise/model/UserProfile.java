package entity.exercise.model;

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
public class UserProfile {
    @Id
    @GeneratedValue
    long id;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String description;
    @OneToMany
    List<UserProfile> friends;
    @OneToOne
    UserCredentials userCredentials;

    public UserProfile(String firstName, String lastName, LocalDate dateOfBirth, String description, List<UserProfile> friends, UserCredentials userCredentials) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.description = description;
        this.friends = friends;
        this.userCredentials = userCredentials;
    }
}
