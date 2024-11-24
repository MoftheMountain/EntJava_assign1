package info.ejava_student.starter.assignment4.docker;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(classes={IntegrationTestConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles({"test","nosecurity","it"})
@Slf4j
class MyIntegrationNTest {
    @Autowired
    private URI rentalsUrl;

    private RestTemplate restTemplate= new RestTemplate();


    @Test
    void user_can_contact_server() {
        //given
        //Doesn't matter which id to request since "didn't find" is still OK response.
        URI url = UriComponentsBuilder.fromUri(rentalsUrl).queryParam("id","rental-007").build().toUri();
        log.debug("Requesting URL: {}",url);

        RequestEntity<Void> request = RequestEntity.get(url).build();
        //when
        ResponseEntity<String> response = restTemplate.exchange(request,String.class);
        //then
        log.debug("Received status {}",response.getStatusCode());
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
