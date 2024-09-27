package info.ejava_student.maryc.assignment1.logging.autorentals;

import info.ejava_student.maryc.assignment1.logging.autorentals.svc.AutoRentalsHelperImpl;
import info.ejava_student.maryc.assignment1.logging.autorentals.svc.AutoRentalsServiceImpl;
import info.ejava_student.maryc.assignment1.logging.autorentals.app.AppCommand;
import info.ejava_student.maryc.assignment1.logging.autorentals.repo.AutoRentalsRepository;
import info.ejava_student.maryc.assignment1.logging.autorentals.repo.AutoRentalsRepositoryImpl;
import info.ejava_student.maryc.assignment1.logging.autorentals.svc.AutoRentalsHelper;
import info.ejava_student.maryc.assignment1.logging.autorentals.svc.AutoRentalsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentalsConfig {
    @Bean
    public AutoRentalsRepository repo() {
        return new AutoRentalsRepositoryImpl();
    }
    @Bean
    public AutoRentalsHelper helper() {
        return new AutoRentalsHelperImpl();
    }

    @Bean
    public AutoRentalsService service(AutoRentalsRepository repo, AutoRentalsHelper helper) {
        return new AutoRentalsServiceImpl(repo, helper);
    }

    @Bean
    public AppCommand appCommand(AutoRentalsService service) {
        return new AppCommand(service);
    }
}
