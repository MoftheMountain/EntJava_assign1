package info.ejava_student.assignment1.logging.autorentals.svc;

import info.ejava_student.assignment1.logging.autorentals.repo.AutoRentalDTO;
import info.ejava_student.assignment1.logging.autorentals.svc.AutoRentalsHelper;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class AutoRentalsHelperImpl implements AutoRentalsHelper{
        public BigDecimal calcDelta(AutoRentalDTO leader, AutoRentalDTO targetResult){
            return leader.getAmount().subtract(targetResult.getAmount());
        }

}