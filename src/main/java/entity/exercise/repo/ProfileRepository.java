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

    @Query(value = "select u1.firstName, count(u3.id) as amount from UserProfile u1 " +
            "join USERPROFILE_FRIENDS u2 on u1.id = u2.USER_PROFILE_ID join UserProfile u3 on u3.id = u2.FRIENDS_ID " +
            "group by u1.firstName order by count(u3.id) desc limit 1",
            nativeQuery = true)
    Optional<UserLong> getUserWithMostFriendsSql();

    @Query("select p.firstName as firstName, count(f) as amount from UserProfile p join p.friends f group by firstName" +
            " having count(f) > :a order by amount desc")
    List<UserLong> getUsersWithAtLeastFriends(@Param("a") long a);

    @Query("select p from UserProfile p where p.dateOfBirth > :date")
    List<UserProfile> getUsersYoungerThan(@Param("date") LocalDate date);
}
