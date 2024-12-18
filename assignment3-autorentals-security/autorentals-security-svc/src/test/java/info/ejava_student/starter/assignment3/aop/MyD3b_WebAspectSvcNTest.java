package info.ejava_student.starter.assignment3.aop;

import info.ejava.assignments.aop.autorenters.util.D3a_AspectSvcNTest;
import info.ejava.assignments.security.autorenters.svc.ProvidedAuthorizationTestHelperConfiguration;
import info.ejava_student.maryc.assignment3.assignment3.security.AutoRentalsSecurityApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes={AutoRentalsSecurityApp.class,
        ProvidedAuthorizationTestHelperConfiguration.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@ActiveProfiles({"test", "authorizations", "authentication", "aop"})//aop profile!
@DisplayName("Part D3b: Web Aspect (Service)")
@Disabled
public class MyD3b_WebAspectSvcNTest extends D3a_AspectSvcNTest {
}
