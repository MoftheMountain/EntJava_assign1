package info.ejava_student.starter.assignment3.security.autorentals;

import info.ejava.assignments.security.autorenters.svc.rentals.B1_AuthoritiesNTest;
import info.ejava_student.maryc.assignment3.assignment3.security.AutoRentalsSecurityApp;
import info.ejava_student.starter.assignment3.security.autorentals.impl.SecurityTestConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes= {
        AutoRentalsSecurityApp.class,
        SecurityTestConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test", "authorities"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Part B1: Authorities")
//@Disabled
public class MyB1_AuthoritiesNTest extends B1_AuthoritiesNTest {
}
