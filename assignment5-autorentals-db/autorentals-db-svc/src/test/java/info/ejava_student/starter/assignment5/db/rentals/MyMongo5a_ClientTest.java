package info.ejava_student.starter.assignment5.db.rentals;

import info.ejava.assignments.db.autorentals.Mongo5a_ClientTest;
import info.ejava_student.starter.assignment5.db.DisableEmbeddedMongoConfiguration;
import info.ejava_student.starter.assignment5.db.rentals.impl.DbAssignmentTestConfiguration;
import info.ejava_student.starter.assignment5.db.rentals.impl.DbClientTestConfiguration;
import info.ejava_student.starter.assignment5.mongo.assignment.MongoAssignmentDBConfiguration;
import org.junit.jupiter.api.Disabled;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes={DbAssignmentTestConfiguration.class,
        MongoAssignmentDBConfiguration.class,
        DbClientTestConfiguration.class,
        DisableEmbeddedMongoConfiguration.class})
@ActiveProfiles(profiles={"assignment-tests","test"}, resolver = TestProfileResolver.class)
//@ActiveProfiles(profiles={"assignment-tests","test", "mongodb"})
//disable JPA/DataSource during MongoDB Tests
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class
})
@Disabled
public class MyMongo5a_ClientTest extends Mongo5a_ClientTest {
}