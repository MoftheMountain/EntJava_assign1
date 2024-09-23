package info.ejava_student.maryc.assignment1.autoconfig.rentals;

import info.ejava_student.maryc.assignment1.beanfactory.rentals.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class RentalsConfiguration {

    @Bean
    public AppCommand appCommand(@Autowired(required = false) RentalsService rentalsService) {
        return new AppCommand();
    }
}
