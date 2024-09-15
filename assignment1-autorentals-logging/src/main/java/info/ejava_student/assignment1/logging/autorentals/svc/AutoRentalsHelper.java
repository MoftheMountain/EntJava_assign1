package info.ejava_student.assignment1.logging.autorentals.svc;

import info.ejava_student.assignment1.logging.autorentals.repo.AutoRentalDTO;

import java.math.BigDecimal;

public interface AutoRentalsHelper {
    BigDecimal calcDelta(AutoRentalDTO leader, AutoRentalDTO targetResult);
}
