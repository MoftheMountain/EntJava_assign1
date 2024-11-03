package info.ejava_student.starter.assignment3.security.https;

import info.ejava_student.maryc.assignment3.assignment3.security.AutoRentalsSecurityApp;
import info.ejava_student.starter.assignment3.security.autorentals.impl.SecurityTestConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootTest(classes= {
        AutoRentalsSecurityApp.class,
        SecurityTestConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({
        //turn on final SecurityFilterChain settings
        "authorization",
        //populate with users, credentials, and authorities
        "authorities",
        //activate server-side HTTPS properties
        "https",
        //activate any standard test settings, like logger
        "test",
        //get client-side HTTPS properties, including trustStore
        "its"})
@Disabled("complete and enable")
public class MyHttpsNTest {
    //these need injecting
    String username;
    private RestTemplate itAuthnUser;
    private URI whoAmIUrl;

    @Test
    public void user_can_call_authenticated() {
        //given a request to an endpoint that accepts only authenticated calls

        //when called with an authenticated identity

        //then expected results returned
            //status success
            //verified username/identity
    }
}
