package info.ejava_student.maryc.assignment1.beanfactory.rentals;

import org.springframework.boot.CommandLineRunner;


public class AppCommand implements CommandLineRunner {
    private final RentalsService rentalsService;

    public AppCommand(RentalsService rentalsService){
        this.rentalsService = rentalsService;
    }
    @Override
    public void run(String...args) throws Exception {
        RentalDTO rental = rentalsService.getRandomRental();
        String msg = String.format("Rentals has started, rental:%s", rental.toString());
        System.out.println(msg);
    }
}
