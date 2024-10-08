package info.ejava_student.maryc.assignment2.api.api;

import info.ejava.assignments.api.autorenters.svc.autos.AutosService;
import info.ejava.assignments.api.autorenters.svc.renters.RentersService;
import info.ejava_student.maryc.assignment2.api.api.autorentals.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoRentalsAPIConfiguration {
    @Bean
    public AutoRentalsService autoRentalsService(AutoRentalsDTORepository repo, AutosService autoService, RentersService rentersService){
        return new AutoRentalsServiceImpl(repo, autoService, rentersService);
    }

    @Bean
    @ConditionalOnMissingBean
    public AutoRentalsDTORepository repo() {return new AutoRentalsDTORepositoryImpl();}

    @Bean
    @ConditionalOnMissingBean
    public AutoRentalsController autoRentalsController(AutoRentalsService autoRentalsService){
        return new AutoRentalsController(autoRentalsService);
    }
}
