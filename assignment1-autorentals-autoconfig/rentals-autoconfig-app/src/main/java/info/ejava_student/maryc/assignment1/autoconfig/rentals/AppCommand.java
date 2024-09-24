package info.ejava_student.maryc.assignment1.autoconfig.rentals;

import info.ejava_student.maryc.assignment1.beanfactory.rentals.RentalDTO;
import info.ejava_student.maryc.assignment1.beanfactory.rentals.RentalsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

@RequiredArgsConstructor
public class AppCommand implements CommandLineRunner {

    @Getter
    private final RentalsService rentalsService;
    @Value("${rentals.active:}")
    private String rentalsActive;
    @Value("${rentals.preference:}")
    private String rentalsPreference;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("rentals.active=" + rentalsActive);
        System.out.println("rentals.preference=" + rentalsPreference);

        RentalDTO rental = null== rentalsService ? null : rentalsService.getRandomRental();
        String msg = null==rental ?
                "Rentals is not active" :
                String.format("Rentals has started, rental:%s", rental);
        System.out.println(msg);
    }
}
