package entity.exercise.repo;

import entity.exercise.model.UserProfile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<UserProfile, Long> {
    List<UserProfile> findAll();
    // List<UserProfile> findFriendsByFirstNameAndLastName(String firstName, String lastName);
    UserProfile findUserProfileByFirstNameAndLastName(String firstName, String lastName);
    UserProfile findUserProfileByFirstName(String firstName);
    void deleteByFirstName(String firstName);
    long countByLastName(String lastName);
}
