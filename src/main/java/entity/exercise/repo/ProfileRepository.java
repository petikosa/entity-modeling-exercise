package entity.exercise.repo;

import entity.exercise.model.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<UserProfile, Long> {
    List<UserProfile> findAll();
}
