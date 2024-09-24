package info.ejava_student.maryc.assignment1.autoconfig.rentalsstarter;

import info.ejava_student.maryc.assignment1.beanfactory.rentals.RentalsService;
import info.ejava_student.maryc.assignment1.beanfactory.autorentals.AutoRentalsServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@AutoConfigureOrder(2)
@ConditionalOnClass(AutoRentalsServiceImpl.class)
@ConditionalOnProperty(prefix ="rentals", name="active", matchIfMissing =true)
@AutoConfiguration
public class AutoRentalsAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean()
    public RentalsService autoRentalsService(){
        return new AutoRentalsServiceImpl();
    }
}