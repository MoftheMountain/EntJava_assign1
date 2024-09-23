package info.ejava_student.maryc.assignment1.testing.autorentals;

import info.ejava.assignments.testing.rentals.renters.InvalidInputException;
import info.ejava.assignments.testing.rentals.renters.RenterDTO;
import info.ejava.assignments.testing.rentals.renters.RentersConfiguration;
import info.ejava.assignments.testing.rentals.renters.RentersService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@SpringBootApplication(
        scanBasePackageClasses = {RentersConfiguration.class}
)
public class AutoRentalsTestingApp {
    public static void main(String...args) {
        SpringApplication.run(AutoRentalsTestingApp.class, args);
    }

    @Component
    @RequiredArgsConstructor
    static class Init implements CommandLineRunner {
        private final RentersService rentersService;
        @Override
        public void run(String... args) {
            RenterDTO validRenter = RenterDTO.builder()
                    .firstName("warren")
                    .lastName("buffet")
                    .dob(LocalDate.of(1930,8, 30))
                    .build();
            rentersService.createRenter(validRenter);

            RenterDTO invalidRenter = RenterDTO.builder()
                    .firstName("future")
                    .lastName("buffet")
                    .dob(LocalDate.now())
                    .build();
            try {
                rentersService.createRenter(invalidRenter);
            } catch (InvalidInputException expected) {
            }
        }
    }

    //example CommandLineRunner as a Lambda function
    @Bean
    CommandLineRunner lambdaDemo(RentersService rentersService) {
        return (args)->{
            RenterDTO validRenter = RenterDTO.builder()
                    .firstName("donald")
                    .lastName("bren")
                    .dob(LocalDate.of(1932,5, 11))
                    .build();
            rentersService.createRenter(validRenter);

            RenterDTO invalidRenter = RenterDTO.builder()
                    .firstName("future")
                    .lastName("buffet")
                    .dob(LocalDate.now())
                    .build();
            try {
                rentersService.createRenter(invalidRenter);
            } catch (InvalidInputException expected) {
            }
        };
    }
}
