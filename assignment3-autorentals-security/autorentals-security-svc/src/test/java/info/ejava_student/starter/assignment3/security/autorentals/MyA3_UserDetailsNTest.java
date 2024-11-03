package info.ejava_student.starter.assignment3.security.autorentals;

import info.ejava.assignments.security.autorenters.svc.rentals.A3_UserDetailsNTest;
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
@ActiveProfiles({"test","userdetails"})
@Slf4j
@DisplayName("Part A3: User Details")
@Disabled
public class MyA3_UserDetailsNTest extends A3_UserDetailsNTest {
}
