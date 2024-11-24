package info.ejava_student.starter.assignment5.db.rentals.impl;

import info.ejava.assignments.api.autorenters.dto.autos.AutoDTOFactory;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTOFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * This configuration class is provided as an alternative to using the
 * full application that may be wired for many technologies.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@Profile("assignment-tests")
public class DbAssignmentTestConfiguration {
    @Bean
    public AutoDTOFactory autoDTOFactory() {
        return new AutoDTOFactory();
    }
    @Bean
    public RenterDTOFactory renterDTOFactory() {
        return new RenterDTOFactory();
    }
}
