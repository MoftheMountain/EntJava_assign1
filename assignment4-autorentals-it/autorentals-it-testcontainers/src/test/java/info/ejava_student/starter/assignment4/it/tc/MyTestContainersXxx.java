package info.ejava_student.starter.assignment4.it.tc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.net.URI;

@SpringBootTest(classes={IntegrationTestConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
//@...
class MyTestContainersXxx {
    //@...
    static GenericContainer appContainer = new GenericContainer(
            new ImageFromDockerfile()
//                    .withFileFromPath()
//                    .withFileFromPath()
            )
//            .withExposedPorts(8080)
//            .withEnv("","")
            ;
    //@...
    static void addLateSpringContextProperties(DynamicPropertyRegistry registry) {
    }
    @Autowired
    private URI rentalsUrl;

    @Test
    void user_can_() {
        //given
        //when
        //then
    }
}