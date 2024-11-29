package info.ejava_student.maryc.assignment5.pageable;

import info.ejava_student.maryc.assignment2.api.autorentals.AutoRentalsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class PageableConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public AutoRentalsPageableController autoRentalsPageableController(AutoRentalsService autoRentalsService) {
        return null;
    }

    @Bean
    @ConditionalOnMissingBean
    public ExceptionAdvice pagedExceptionAdvice() {
        return null;
    }
}
