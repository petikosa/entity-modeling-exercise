package entity.exercise.services;

import entity.exercise.model.UserProfile;
import entity.exercise.repo.ProfileRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Map<UserProfile, Integer> getFriendsCounts() {
        var users = profileRepository.findAll();
        return users.stream()
                .collect(toMap(identity(), x -> x.getFriends().size()))
                .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public List<UserProfile> getFriendsByUserId(long id) {
        return profileRepository.findAll().stream()
                .filter(user -> user.getId() == id)
                .flatMap(user -> user.getFriends().stream())
                .collect(toList());
    }

    public List<UserProfile> getFriendsByEmail(String email) {
        return profileRepository.findAll().stream()
                .filter(user -> user.getUserCredentials().getEmail().equals(email))
                .flatMap(user -> user.getFriends().stream())
                .collect(toList());
    }

    public List<UserProfile> getUsersOlderThan(LocalDate date) {
        return profileRepository.findAll().stream()
                .filter(user -> user.getDateOfBirth().isAfter(date))
                .sorted(Comparator.comparing(UserProfile::getDateOfBirth).reversed())
                .collect(Collectors.toList());
    }

    public Map<UserProfile, Integer> getMostCommentingUsers() {
        var userComments = profileRepository.findAll().stream()
                .collect(toMap(identity(), u -> u.getComments().size()));
        return userComments.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

    }
}
