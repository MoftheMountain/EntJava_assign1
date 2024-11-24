package info.ejava_student.starter.assignment5.mongo.assignment;

import info.ejava.assignments.db.autorenters.rentals.MongoAssignmentService;
import info.ejava.assignments.db.autorenters.svc.rentals.RentalsMapper;
import info.ejava_student.starter.assignment2.api.autorentals.client.AutoRentalDTO;
import info.ejava_student.starter.assignment5.db.autorentals.AutoRentalBO;
import info.ejava_student.starter.assignment5.db.autorentals.AutoRentalMapper;
import info.ejava_student.starter.assignment5.mongo.autorentals.MongoAutoRentalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * This configuration class is used by the "assignment" exercises. It instantiates
 * a pseudo-service, implementing an interface to support the test exercises.
 * Everything created here will get replaced by the end-to-end configuration
 * during the end-to-end tests and the running application.
 */
@Profile("assignment-tests")
public class MongoAssignmentDBConfiguration {
    public RentalsMapper<AutoRentalDTO, AutoRentalBO> mapper() {
        return new AutoRentalMapper();
    }

    public MongoAssignmentService<AutoRentalDTO,AutoRentalBO> mongoAssignmentService(RentalsMapper<AutoRentalDTO, AutoRentalBO> mapper,
                                                         MongoOperations mongoOps,
                                                         //make required when available/used
                                                         @Autowired(required = false) MongoAutoRentalsRepository repo) {
        return new MongoAssignmentServiceImpl(/*mapper, mongoOps, repo*/);
    }
}
