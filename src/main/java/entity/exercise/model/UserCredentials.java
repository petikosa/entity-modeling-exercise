package entity.exercise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserCredentials {
    @Id
    @GeneratedValue
    long id;
    String email;
    String password;

    public UserCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
