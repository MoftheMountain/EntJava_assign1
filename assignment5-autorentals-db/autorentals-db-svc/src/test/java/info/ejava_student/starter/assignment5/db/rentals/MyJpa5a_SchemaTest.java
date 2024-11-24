package info.ejava_student.starter.assignment5.db.rentals;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import info.ejava.assignments.db.autorentals.Jpa5a_SchemaTest;
import info.ejava_student.starter.assignment5.db.rentals.impl.DbAssignmentTestConfiguration;
import info.ejava_student.starter.assignment5.db.rentals.impl.DbClientTestConfiguration;
import info.ejava_student.starter.assignment5.jpa.assignment.JpaAssignmentDBConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.Ordered;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes={DbAssignmentTestConfiguration.class,
        JpaAssignmentDBConfiguration.class,
        DbClientTestConfiguration.class})
@ActiveProfiles(profiles={"assignment-tests","test"}, resolver = TestProfileResolver.class)
//@ActiveProfiles(profiles={"assignment-tests","test", "postgres"})
//turn off MongoDB for this test
@EnableAutoConfiguration(exclude = {
        MongoAutoConfiguration.class,
        EmbeddedMongoAutoConfiguration.class
})
@Disabled
@Order(Ordered.HIGHEST_PRECEDENCE) //we need this test to run first, immediately after migration
public class MyJpa5a_SchemaTest extends Jpa5a_SchemaTest {
}
