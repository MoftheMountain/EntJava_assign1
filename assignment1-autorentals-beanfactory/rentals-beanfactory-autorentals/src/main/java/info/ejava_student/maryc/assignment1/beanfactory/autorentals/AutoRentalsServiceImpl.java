package info.ejava_student.maryc.assignment1.beanfactory.autorentals;

import info.ejava_student.maryc.assignment1.beanfactory.rentals.RentalsService;
import info.ejava_student.maryc.assignment1.beanfactory.rentals.RentalDTO;

public class AutoRentalsServiceImpl implements RentalsService{
    @Override
    public RentalDTO getRandomRental() {
        return new RentalDTO("autoRental0");
    }
}
