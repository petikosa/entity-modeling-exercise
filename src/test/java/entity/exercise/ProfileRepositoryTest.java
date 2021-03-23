package entity.exercise;

import entity.exercise.repo.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

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
        assertEquals(profileRepository.count(), 4);
    }

/*    @Test
    void findFriends() {
        var friends = profileRepository.findFriendsByFirstNameAndLastName("Stephane", "Boisson");
        assertNotNull(friends);
        assertEquals(friends.size(), 2);
    }*/

    @Test
    void findProfileByName() {
        var profile = profileRepository.findUserProfileByFirstNameAndLastName("Stephane", "Boisson");
        assertEquals(profile.getFirstName(), "Stephane");
    }

    @Test
    @Transactional
    void deleteByFirstName() {
        var peter = profileRepository.findUserProfileByFirstName("Peter");
        profileRepository.deleteByFirstName("Peter");
        var allLeft = profileRepository.findAll();
        assertNotNull(allLeft);
        assertFalse(allLeft.contains(peter));
    }

    @Test
    void deleteAll() {
        profileRepository.deleteAll();
        assertTrue(profileRepository.findAll().isEmpty());
    }

    @Test
    void countByLastName() {
        assertEquals(profileRepository.countByLastName("Lipovsky"), 1);
    }
}
