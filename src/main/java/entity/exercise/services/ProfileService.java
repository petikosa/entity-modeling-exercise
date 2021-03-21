package entity.exercise.services;

import entity.exercise.repo.ProfileRepository;
import entity.exercise.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

/*    ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }*/

/*    public List<UserProfile> getUserProfiles() {
        return profileRepository.findAll();
    }*/
}
