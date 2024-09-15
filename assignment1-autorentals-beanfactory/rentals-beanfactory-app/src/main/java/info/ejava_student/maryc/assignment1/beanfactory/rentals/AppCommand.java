package info.ejava_student.maryc.assignment1.beanfactory.rentals;

import org.springframework.boot.CommandLineRunner;

public class AppCommand implements CommandLineRunner {
    @Override
    public void run(String...args) throws Exception {
        RentalDTO rental = null;
        String msg = String.format("Rentals has started, rental:%s", rental);
        System.out.println(msg);
    }
}


