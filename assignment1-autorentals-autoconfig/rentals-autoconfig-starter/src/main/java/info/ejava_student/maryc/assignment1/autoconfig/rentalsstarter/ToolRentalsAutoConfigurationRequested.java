package info.ejava_student.maryc.assignment1.autoconfig.rentalsstarter;

import info.ejava_student.maryc.assignment1.beanfactory.rentals.RentalsService;
import info.ejava_student.maryc.assignment1.autoconfig.toolrentals.ToolRentalsServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@AutoConfigureOrder(1)
@ConditionalOnClass(RentalsService.class)
@ConditionalOnProperty(prefix="rentals", name="preference", havingValue="tools")
@AutoConfiguration
public class ToolRentalsAutoConfigurationRequested {
    @Bean
    @ConditionalOnProperty(prefix ="rentals", name="active", matchIfMissing =true)
    public RentalsService rentalsService(){
        return new ToolRentalsServiceImpl();
    }
}