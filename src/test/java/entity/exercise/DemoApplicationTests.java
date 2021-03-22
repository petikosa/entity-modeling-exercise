package entity.exercise;

import entity.exercise.services.ProfileService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {DemoApplication.class, ProfileService.class})
class DemoApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

	@Autowired
	private ProfileService profileService;

	@Test
	void contextLoads() {
	}

	@Test
	void getAllProfiles() {
		assertNotNull(profileService);
	}
}
