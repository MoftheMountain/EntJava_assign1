package info.ejava_student.starter.assignment1.configprops.rentals;

import info.ejava_student.starter.assignment1.configprops.rentals.properties.RentalProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * There will be at least 3 separate modifications in this class to
 * get ConfigurationProperties wired into the PropertyPrinter component.
 */
@SpringBootApplication
public class ConfigPropertiesApp {
    public static void main(String[] args) {
        SpringApplication.run(ConfigPropertiesApp.class, args);
    }

    //@Validated
    public List<RentalProperties> autos() {
        return new ArrayList<>();
    }

    public List<RentalProperties> tools() {
        return new ArrayList<>();
    }
}
