package info.ejava_student.starter.assignment3.security.https;

import info.ejava.assignments.security.autorenters.svc.AccountProperties;
import info.ejava_student.maryc.assignment3.assignment3.security.AutoRentalsSecurityApp;
import info.ejava_student.starter.assignment3.security.autorentals.impl.SecurityTestConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.assertj.core.api.BDDAssertions.then;

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

    @Autowired
    private RestTemplate authnUser;

    @Autowired
    AccountProperties anAccount;

    @Autowired
    private URI whoAmIUrl;

    @Test
    public void user_can_call_authenticated() {
        //given a request to an endpoint that accepts only authenticated calls
        URI url = UriComponentsBuilder.fromUri(whoAmIUrl)
                .queryParam("username", anAccount.getUsername()).build().toUri();
        //when called with an authenticated identity
        ResponseEntity<String> response = authnUser.getForEntity(url, String.class);
        //then expected results returned
            then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            then(response.getBody()).isEqualTo(anAccount.getUsername());
        //status success
            //verified username/identity
    }
}
