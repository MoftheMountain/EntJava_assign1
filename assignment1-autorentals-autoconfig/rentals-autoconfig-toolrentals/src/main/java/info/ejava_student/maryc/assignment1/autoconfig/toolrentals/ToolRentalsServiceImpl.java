package info.ejava_student.maryc.assignment1.autoconfig.toolrentals;

import info.ejava_student.maryc.assignment1.beanfactory.rentals.RentalDTO;
import info.ejava_student.maryc.assignment1.beanfactory.rentals.RentalsService;

public class ToolRentalsServiceImpl implements RentalsService {
    @Override
    public RentalDTO getRandomRental() {
        return new RentalDTO("toolRental0");
    }

}

