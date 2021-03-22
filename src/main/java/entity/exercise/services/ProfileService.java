package entity.exercise.services;

import entity.exercise.model.UserProfile;
import entity.exercise.repo.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<UserProfile> getUserProfiles() {
        return profileRepository.findAll();
    }
}
