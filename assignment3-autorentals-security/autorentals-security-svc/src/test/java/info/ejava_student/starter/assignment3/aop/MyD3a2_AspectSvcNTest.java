package info.ejava_student.starter.assignment3.aop;

import info.ejava.assignments.aop.autorenters.util.D3a_AspectSvcNTest;
import info.ejava.assignments.api.autorenters.svc.ProvidedApiAutoRenterTestConfiguration;
import info.ejava_student.maryc.assignment3.assignment3.security.AutoRentalsSecurityApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes={AutoRentalsSecurityApp.class,
        ProvidedApiAutoRenterTestConfiguration.class})
@Slf4j
@ActiveProfiles({"test", "authorizations", "authentication", "aop"})//aop profile!
@DisplayName("Part D3a2: Aspect (Service)")
@Disabled
public class MyD3a2_AspectSvcNTest extends D3a_AspectSvcNTest {
}