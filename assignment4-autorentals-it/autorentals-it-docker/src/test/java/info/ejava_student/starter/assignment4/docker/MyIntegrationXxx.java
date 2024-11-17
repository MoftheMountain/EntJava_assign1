package info.ejava_student.starter.assignment4.docker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;

@SpringBootTest(classes={IntegrationTestConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class MyIntegrationXxx {
    @Autowired
    private URI rentalsUrl;

    @Test
    void user_can_() {
        //given
        //when
        //then
    }
}
