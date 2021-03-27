package entity.exercise;

import entity.exercise.model.UserProfile;
import entity.exercise.repo.ProfileRepository;
import entity.exercise.services.ProfileService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {DemoApplication.class, ProfileService.class})
class ProfileStreamsTest {

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	ProfileService profileService;

	@Test
	void contextLoads() {
	}

	@Test
	void getProfilesWithFriends() {
		var users = profileRepository.findAll();
		Map<UserProfile, List<UserProfile>> groupedByFriends = users.stream()
				.collect(toMap(identity(), UserProfile::getFriends));
	}

	@Test
	void getFriendsCounts() {
		var users = profileService.getFriendsCounts();
		System.out.println(users);
	}
}
