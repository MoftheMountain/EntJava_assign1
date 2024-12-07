package info.ejava_student.maryc.assignment5.jpa.autorentals;

import info.ejava.assignments.api.autorenters.svc.autos.AutosService;
import info.ejava.assignments.api.autorenters.svc.renters.RentersService;
import info.ejava.assignments.db.autorenters.svc.rentals.RentalsMapper;
import info.ejava_student.maryc.assignment2.api.autorentals.AutoRentalsService;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalBO;
import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import info.ejava_student.maryc.assignment5.jpa.autorentals.JpaAutoRentalsRepository;

@ConditionalOnProperty(prefix = "rentals", name="impl", havingValue = "jpa", matchIfMissing = true)
@Configuration
public class JpaAutoRentalsConfiguration {
    public RentalsMapper<AutoRentalDTO, AutoRentalBO> mapper() {
        return new AutoRentalMapper();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public AutoRentalsService jpaAutoRentalsService(JpaAutoRentalsRepository repository,
                                                    AutosService autosService,
                                                    RentersService rentersService,
                                                    RentalsMapper mapper) {
        //return new JpaAutoRentalsServiceImpl(repository, autosService, rentersService, mapper);
        return null;
    }
}
