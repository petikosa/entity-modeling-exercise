package entity.exercise.services;

import entity.exercise.model.UserProfile;
import entity.exercise.repo.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Map<UserProfile, Integer> getFriendsCounts() {
        var users = profileRepository.findAll();
        Map<UserProfile, Integer> groupedByFriendsCount = users.stream()
                .collect(toMap(identity(), x -> x.getFriends().size()));
        return groupedByFriendsCount;
    }
}
