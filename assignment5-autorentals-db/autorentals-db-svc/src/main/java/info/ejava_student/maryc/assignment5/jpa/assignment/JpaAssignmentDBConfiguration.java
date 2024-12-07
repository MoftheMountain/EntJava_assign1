package info.ejava_student.maryc.assignment5.jpa.assignment;

import info.ejava.assignments.db.autorenters.rentals.JpaAssignmentService;
import info.ejava.assignments.db.autorenters.svc.rentals.RentalsMapper;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalBO;
import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalMapper;
import info.ejava_student.maryc.assignment5.jpa.autorentals.JpaAutoRentalsRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This configuration class is used by the "assignment" exercises. It instantiates
 * a pseudo-service, implementing an interface to support the test exercises.
 * Everything created here will get replaced by the end-to-end configuration
 * during the end-to-end tests and the running application.
 */
@Profile("assignment-tests")
@Configuration
@EntityScan(basePackageClasses = AutoRentalBO.class)
@EnableJpaRepositories(basePackageClasses = {JpaAutoRentalsRepository.class})
public class JpaAssignmentDBConfiguration {

    @Bean
    public RentalsMapper<AutoRentalDTO, AutoRentalBO> mapper() {
        return new AutoRentalMapper();
    }

    @Bean
    public JpaAssignmentService<AutoRentalDTO,AutoRentalBO>
        jpaAssignmentService(RentalsMapper<AutoRentalDTO, AutoRentalBO> mapper,
                             EntityManager entityManager,
                             //make required when available/used
                             @Autowired(required = false) JpaAutoRentalsRepository repo) {
        return new JpaAssignmentServiceImpl(mapper, entityManager, repo);

    }
}
