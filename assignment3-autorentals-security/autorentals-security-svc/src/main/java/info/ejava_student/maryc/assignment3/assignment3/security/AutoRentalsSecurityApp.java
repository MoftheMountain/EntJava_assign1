package info.ejava_student.maryc.assignment3.assignment3.security;

import info.ejava_student.maryc.assignment3.assignment3.aop.autorentals.AOPConfiguration;
import info.ejava_student.maryc.assignment2.api.autorentals.AutoRentalsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {
        AutoRentalsService.class, //scan the API solution
        AutoRentalsSecurityApp.class, //scan here
        AOPConfiguration.class     //scan AOP
})
public class AutoRentalsSecurityApp {
    public static void main(String...args) {
        SpringApplication.run(AutoRentalsSecurityApp.class, args);
    }
}