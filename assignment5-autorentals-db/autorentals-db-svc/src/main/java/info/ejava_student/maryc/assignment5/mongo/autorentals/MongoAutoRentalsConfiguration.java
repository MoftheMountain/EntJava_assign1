package info.ejava_student.maryc.assignment5.mongo.autorentals;

import info.ejava.assignments.api.autorenters.svc.autos.AutosService;
import info.ejava.assignments.api.autorenters.svc.renters.RentersService;
import info.ejava.assignments.db.autorenters.svc.rentals.RentalsMapper;
import info.ejava_student.maryc.assignment2.api.autorentals.AutoRentalsService;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalBO;
import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@ConditionalOnProperty(prefix = "rentals", name="impl", havingValue = "mongo", matchIfMissing = false)
public class MongoAutoRentalsConfiguration {
    public RentalsMapper<AutoRentalDTO, AutoRentalBO> mapper() {
        return new AutoRentalMapper();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public AutoRentalsService mongoAutoRentalsService(MongoAutoRentalsRepository repository,
                                                      AutosService autosService,
                                                      RentersService rentersService,
                                                      RentalsMapper mapper) {
        //return new MongoAutoRentalsServiceImpl(repository, autosService, rentersService, mapper);
        return null;
    }
}
