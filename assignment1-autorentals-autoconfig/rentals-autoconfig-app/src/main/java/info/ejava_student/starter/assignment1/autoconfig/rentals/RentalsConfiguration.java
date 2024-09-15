package info.ejava_student.starter.assignment1.autoconfig.rentals;

import info.ejava_student.starter.assignment1.autoconfig.rentals.AppCommand;
import info.ejava_student.starter.assignment1.beanfactory.rentals.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;

public class RentalsConfiguration {
    public AppCommand appCommand(@Autowired(required = false) RentalsService rentalsService) {
        return null;
    }
}
