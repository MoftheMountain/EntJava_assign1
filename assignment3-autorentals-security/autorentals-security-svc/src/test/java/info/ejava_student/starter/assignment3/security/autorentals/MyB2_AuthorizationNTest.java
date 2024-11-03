package info.ejava_student.starter.assignment3.security.autorentals;

import info.ejava.assignments.security.autorenters.svc.rentals.B2_AuthorizationNTest;
import info.ejava_student.maryc.assignment3.assignment3.security.AutoRentalsSecurityApp;
import info.ejava_student.starter.assignment3.security.autorentals.impl.SecurityTestConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes= {
        AutoRentalsSecurityApp.class,
        SecurityTestConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test","authorities", "authorization"})
@Slf4j
@DisplayName("Part B2: Authorization")
//@Disabled
public class MyB2_AuthorizationNTest extends B2_AuthorizationNTest {
}
