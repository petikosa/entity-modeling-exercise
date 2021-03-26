package entity.exercise.repo;

import entity.exercise.model.UserProfile;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ProfileRepository extends CrudRepository<UserProfile, Long> {
    List<UserProfile> findAll();
    UserProfile findUserProfileByFirstNameAndLastName(String firstName, String lastName);
    UserProfile findUserProfileByFirstName(String firstName);
    void deleteByFirstName(String firstName);
    long countByLastName(String lastName);
    boolean existsByLastName(String lastName);
    Stream<UserProfile> streamTop2ByLastName(String lastName, Sort sort);

    @Query("select p from UserProfile as p")
    List<UserProfile> findAllQ();

    @Query("select f from UserProfile p join p.friends f where p.firstName = :firstName and p.lastName = :lastName")
    List<UserProfile> getAllFriendsOf(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("select p from UserProfile p join p.friends f group by p order by count(f), p.lastName")
    List<UserProfile> getUserWithMostFriends();

    @Query("select p from UserProfile p where p.dateOfBirth > :date")
    List<UserProfile> getUsersYoungerThan(@Param("date") LocalDate date);
}
