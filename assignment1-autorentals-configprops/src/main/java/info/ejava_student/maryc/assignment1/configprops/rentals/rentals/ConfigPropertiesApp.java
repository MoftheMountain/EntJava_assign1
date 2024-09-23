package info.ejava_student.maryc.assignment1.configprops.rentals.rentals;

import info.ejava_student.maryc.assignment1.configprops.rentals.rentals.properties.RentalProperties;
import info.ejava_student.maryc.assignment1.configprops.rentals.rentals.properties.BoatRentalProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(BoatRentalProperties.class)
public class ConfigPropertiesApp {
    public static void main(String[] args) {
        SpringApplication.run(ConfigPropertiesApp.class, args);
    }

    //@Validated
    @Bean
    @ConfigurationProperties("rentals.autos")
    public List<RentalProperties> autos() {
        return new ArrayList<>();
    }

    @Bean
    @ConfigurationProperties("rentals.tools")
    public List<RentalProperties> tools() {
        return new ArrayList<>();
    }
}
