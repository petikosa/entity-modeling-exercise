package entity.exercise;

import entity.exercise.repo.ProfileRepository;
import entity.exercise.model.UserCredentials;
import entity.exercise.model.UserProfile;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"entity.exercise.repo"})
@ComponentScan(basePackages = { "entity.exercise.repo" })
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProfileRepository repository) {
        UserProfile p1 = new UserProfile();
        UserProfile p2 = new UserProfile();
        UserProfile p3 = new UserProfile();
        UserProfile p4 = new UserProfile();
        UserCredentials credentials = new UserCredentials(1, "email", "pass");
        p1 = new UserProfile(1, "Peter", "Johnson",
                LocalDate.now(), "A very active person.", List.of(p2, p3, p4), credentials);
        p2 = new UserProfile(2, "Stephane", "Boisson",
                LocalDate.now(), "Just a French guy.", List.of(p1, p3), credentials);
        p3 = new UserProfile(3, "Jon", "Mobamba",
                LocalDate.now(), "An African reggae star.", List.of(p1, p2), credentials);
        p4 = new UserProfile(4, "Marek", "Lipovsky",
                LocalDate.now(), "The guy from the mountains.", List.of(p1), credentials);
        UserProfile finalP = p2;
        UserProfile finalP1 = p1;
        UserProfile finalP2 = p3;
		UserProfile finalP3 = p4;
		return (args) -> repository.saveAll(List.of(finalP1));
        }
}
