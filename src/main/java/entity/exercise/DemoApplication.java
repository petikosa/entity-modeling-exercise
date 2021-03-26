package entity.exercise;

import entity.exercise.model.UserCredentials;
import entity.exercise.model.UserProfile;
import entity.exercise.repo.ProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProfileRepository repository) {
        UserCredentials c1 = new UserCredentials("peter@gmail.com", "pass");
        UserCredentials c2 = new UserCredentials("steph@mail.com", "pass");
        UserCredentials c3 = new UserCredentials("jon@yahoo.com", "pass");
        UserCredentials c4 = new UserCredentials("marek@yandex.ru", "pass");
        UserCredentials c5 = new UserCredentials("luke@yandex.ru", "pass");
        var p1 = new UserProfile("Peter", "Johnson",
                LocalDate.of(1984, 4, 15), "A very active person.", new ArrayList<>(), c1);
        var p5 = new UserProfile("Lucas", "Johnson",
                LocalDate.of(1993, 7, 15), "Minor brother.", new ArrayList<>(), c5);
        var p2 = new UserProfile("Stephane", "Boisson",
                LocalDate.of(1990, 1, 20), "Just a French guy.", new ArrayList<>(), c2);
        var p3 = new UserProfile("Jon", "Mobamba",
                LocalDate.of(2003, 3, 12), "An African reggae star.", new ArrayList<>(), c3);
        var p4 = new UserProfile("Marek", "Lipovsky",
                LocalDate.of(1970, 9, 2), "The guy from the mountains.", new ArrayList<>(), c4);
		return (args) -> {
                p1.addFriends(List.of(p2, p3, p4, p5));
                p2.addFriends(List.of(p1, p3));
                p3.addFriends(List.of(p1, p2, p4));
                p4.addFriends(List.of(p1, p3));
                p5.addFriends(List.of(p1));
                repository.saveAll(List.of(p1));
		    };
        }
}
