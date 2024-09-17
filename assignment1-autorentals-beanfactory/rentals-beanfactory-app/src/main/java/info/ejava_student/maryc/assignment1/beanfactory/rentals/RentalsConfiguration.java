package info.ejava_student.maryc.assignment1.beanfactory.rentals;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import info.ejava_student.maryc.assignment1.beanfactory.autorentals.AutoRentalsServiceImpl;

@Configuration(proxyBeanMethods = false)
public class RentalsConfiguration {
    @Bean
    public RentalsService rentalsService(){
        return new AutoRentalsServiceImpl();
    }
    @Bean
    public AppCommand run(RentalsService rentalsService) throws Exception{
        return new AppCommand(rentalsService);
    }
 }
