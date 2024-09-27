package info.ejava_student.maryc.assignment1.testing.autorentals.renters;

import info.ejava.assignments.testing.rentals.renters.RenterDTO;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@TestConfiguration(proxyBeanMethods = false)
@ConfigurationPropertiesScan
public class RentersTestConfiguration {
    @Bean
    //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) - optional here
    public RenterDTO validRenter() {
        return RenterDTO.builder()
                .firstName("manny")
                .lastName("pep")
                .dob(LocalDate.ofYearDay(1923,1))
                .build();
    }
}
