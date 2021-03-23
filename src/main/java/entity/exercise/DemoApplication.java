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
        var p1 = new UserProfile("Peter", "Johnson",
                LocalDate.now(), "A very active person.", new ArrayList<>(), c1);
        var p2 = new UserProfile("Stephane", "Boisson",
                LocalDate.now(), "Just a French guy.", new ArrayList<>(), c2);
        var p3 = new UserProfile("Jon", "Mobamba",
                LocalDate.now(), "An African reggae star.", new ArrayList<>(), c3);
        var p4 = new UserProfile("Marek", "Lipovsky",
                LocalDate.now(), "The guy from the mountains.", new ArrayList<>(), c4);
		return (args) -> {
                p1.addFriends(List.of(p2, p3, p4));
                p2.addFriends(List.of(p1, p3));
                p3.addFriends(List.of(p1, p2, p4));
                p4.addFriends(List.of(p1, p3));
                repository.saveAll(List.of(p1));
		    };
        }
}
