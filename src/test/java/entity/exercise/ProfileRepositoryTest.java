package entity.exercise;

import entity.exercise.model.UserProfile;
import entity.exercise.repo.ProfileRepository;
import entity.exercise.repo.UserLong;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {DemoApplication.class})
public class ProfileRepositoryTest {

    @Autowired
    ProfileRepository profileRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void numberOfProfiles() {
        assertEquals(profileRepository.count(), 5);
    }

    @Test
    void findProfileByName() {
        var profile = profileRepository.findUserProfileByFirstNameAndLastName("Stephane", "Boisson");
        assertEquals(profile.getFirstName(), "Stephane");
    }

    @Test
    @Transactional
    @Disabled("Test with side effect")
    void deleteByFirstName() {
        var peter = profileRepository.findUserProfileByFirstName("Peter");
        profileRepository.deleteByFirstName("Peter");
        var allLeft = profileRepository.findAll();
        assertNotNull(allLeft);
        assertFalse(allLeft.contains(peter));
    }

    @Test
    @Disabled("Test with side effect")
    void deleteAll() {
        profileRepository.deleteAll();
        assertTrue(profileRepository.findAll().isEmpty());
    }

    @Test
    void countByLastName() {
        assertEquals(profileRepository.countByLastName("Lipovsky"), 1);
    }

    @Test
    void existsByLastName() {
        assertTrue(profileRepository.existsByLastName("Lipovsky"));
    }

    @Test
    @Transactional
    void getTop2Users() {
        var stream = profileRepository.streamTop2ByLastName("Johnson", Sort.by("firstName").ascending());
        String name = stream.findFirst().map(UserProfile::getFirstName).orElse("");
        assertEquals(name, "Lucas");
    }

    @Test
    void selectAllQ() {
        assertEquals(profileRepository.findAllQ().size(), 5);
    }

    @Test
    void getAllFriendsOfUser() {
        var friends = profileRepository.getAllFriendsOf("Peter", "Johnson");
        assertEquals(friends.size(), 4);
    }

    @Test
    void userWithLeastFriends() {
        var friends = profileRepository.getUserWithMostFriends();
        friends.forEach(friend -> System.out.println(friend.getFirstName() + " - " + friend.getDateOfBirth()));
        assertEquals(friends.stream().findFirst().orElse(new UserProfile()).getFirstName(), "Lucas");
    }

    @Test
    void usersWithAtLeast2friends() {
        var users = profileRepository.getUsersWithAtLeastFriends(2);
        users.forEach((userLong) -> System.out.println(userLong.getFirstName() + " - " + userLong.getAmount()));
    }

    @Test
    void getYoungerUsers() {
        var users = profileRepository.getUsersYoungerThan(LocalDate.of(1990, 1, 1));
        users.forEach(user -> System.out.println(user.getFirstName()));
        assertEquals(users.size(), 3);
    }

    @Test
    void getUsersBySql() {
        var users = profileRepository.getUserWithMostFriendsSql();
        var name = users.map(UserLong::getFirstName).orElse("");
        assertEquals(name, "Peter");
    }
}
