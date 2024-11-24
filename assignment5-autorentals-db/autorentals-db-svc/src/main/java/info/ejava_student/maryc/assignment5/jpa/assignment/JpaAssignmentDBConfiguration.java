package info.ejava_student.starter.assignment5.jpa.assignment;

import info.ejava.assignments.db.autorenters.rentals.JpaAssignmentService;
import info.ejava.assignments.db.autorenters.svc.rentals.RentalsMapper;
import info.ejava_student.starter.assignment2.api.autorentals.client.AutoRentalDTO;
import info.ejava_student.starter.assignment5.db.autorentals.AutoRentalBO;
import info.ejava_student.starter.assignment5.db.autorentals.AutoRentalMapper;
import info.ejava_student.starter.assignment5.jpa.autorentals.JpaAutoRentalsRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

/**
 * This configuration class is used by the "assignment" exercises. It instantiates
 * a pseudo-service, implementing an interface to support the test exercises.
 * Everything created here will get replaced by the end-to-end configuration
 * during the end-to-end tests and the running application.
 */
@Profile("assignment-tests")
public class JpaAssignmentDBConfiguration {
    public RentalsMapper<AutoRentalDTO, AutoRentalBO> mapper() {
        return new AutoRentalMapper();
    }

    public JpaAssignmentService<AutoRentalDTO,AutoRentalBO>
        jpaAssignmentService(RentalsMapper<AutoRentalDTO, AutoRentalBO> mapper,
                             EntityManager entityManager,
                             //make required when available/used
                             @Autowired(required = false) JpaAutoRentalsRepository repo) {
        return new JpaAssignmentServiceImpl(/*mapper, entityManager, repo*/);
    }
}
