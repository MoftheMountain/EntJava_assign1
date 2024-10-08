package info.ejava_student.maryc.assignment2.api.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Slf4j
public class SupportingServicesNTest {
    private RestTemplate restTemplate = new RestTemplate();
    private URI baseUrl;

    @BeforeEach
    void init(@LocalServerPort int port) {
        baseUrl=UriComponentsBuilder.fromHttpUrl("http://localhost/api").port(port).build().toUri();
    }

    @ParameterizedTest
    @ValueSource(strings = {"/autos", "/renters"})
    void url_is_present(String path) {
        //given
        URI url = UriComponentsBuilder.fromUri(baseUrl).path(path).build().toUri();
        RequestEntity request = RequestEntity.get(url).build();
        log.info("GET {}", url);
        //when
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        //then
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
