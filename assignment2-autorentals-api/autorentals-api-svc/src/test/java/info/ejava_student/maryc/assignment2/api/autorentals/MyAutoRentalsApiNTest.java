package info.ejava_student.maryc.assignment2.api.autorentals;

import info.ejava.assignments.api.autorenters.svc.ProvidedApiAutoRenterTestConfiguration;
import info.ejava.assignments.api.autorenters.svc.rentals.AutoRentalsApiNTest;
import info.ejava_student.maryc.assignment2.api.AutoRentalsApiApp;
import info.ejava_student.maryc.assignment2.api.autorentals.impl.ApiImplNTestConfiguration;
import org.junit.jupiter.api.Nested;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//TODO: activate this test early to in
@SpringBootTest(classes={
        AutoRentalsApiApp.class,
        ProvidedApiAutoRenterTestConfiguration.class,
        ApiImplNTestConfiguration.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles({"test"})
//@Disabled("activate once ApiImplNTestConfiguration and ApiTestHelperImpl ready")
public class MyAutoRentalsApiNTest extends AutoRentalsApiNTest {
        //TODO: remove @Nested classes below to expose/enable categories of parent tests

        //remove this class declaration to enable creation tests
        //@Nested public class new_rental {}

        //remove this class declaration to enable get/modification tests
        //@Nested public class existing_rental {}

        //remove this class declaration to enable query tests
        //@Nested public class can_query_to { }

        //remove this class declaration to enable query tests
        @Nested public class date_query { }

        //remove this class declaration to enable tests for rules judged in future
        @Nested public class future_rentals {}
}
