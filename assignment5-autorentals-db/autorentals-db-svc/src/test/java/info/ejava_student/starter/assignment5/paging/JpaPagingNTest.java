package info.ejava_student.starter.assignment5.paging;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import info.ejava.assignments.api.autorenters.dto.autos.AutoDTO;
import info.ejava.assignments.security.autorenters.svc.ProvidedAuthorizationTestHelperConfiguration;
import info.ejava_student.starter.assignment5.db.AutoRentalsDbApp;
import info.ejava_student.starter.assignment5.db.rentals.TestProfileResolver;
import info.ejava_student.starter.assignment5.db.rentals.impl.DbClientTestConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest(classes={AutoRentalsDbApp.class,
        DbClientTestConfiguration.class,
        ProvidedAuthorizationTestHelperConfiguration.class,
}, properties = {"rentals.impl=jpa"},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles={"authorities","authorization","test"}, resolver = TestProfileResolver.class)
//@ActiveProfiles({"authorities","authorization","test", "postgres"})
@EnableAutoConfiguration(exclude = {
        MongoAutoConfiguration.class,
        EmbeddedMongoAutoConfiguration.class
})
@Slf4j
@Disabled
public class JpaPagingNTest {
    @BeforeEach
    void init(@Autowired RestTemplate authnUser, @Autowired RestTemplate adminUser) {
    }

    List<AutoDTO> given_many_autos() {
        return null;
    }

    List<?> given_rentals_for_different_autos(List<AutoDTO> autos) {
        return null;
    }
    List<?> given_many_rentals_for_common_auto(AutoDTO commonAuto) {
        return null;
    }


    @Test
    void can_select_autorental_for_specific_autoId() {
        //given
        List<AutoDTO> autos = given_many_autos();
        List<?> rentals = given_rentals_for_different_autos(autos);
        AutoDTO commonAuto = autos.get(0);
        List<?> commonAutoRentals = given_many_rentals_for_common_auto(commonAuto);
        Pageable pageable = null; //...
        //when
        //then
    }
}
