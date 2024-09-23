package info.ejava_student.maryc.assignment1.autoconfig.rentals;

import info.ejava_student.maryc.assignment1.beanfactory.rentals.RentalDTO;
import info.ejava_student.maryc.assignment1.beanfactory.rentals.RentalsService;
import lombok.Getter;
import org.springframework.boot.CommandLineRunner;

public class AppCommand implements CommandLineRunner {

    @Getter
    private RentalsService rentalsService;
    private String rentalsActive;
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
