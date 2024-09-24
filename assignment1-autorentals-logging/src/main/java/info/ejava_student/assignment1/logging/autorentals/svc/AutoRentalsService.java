package info.ejava_student.assignment1.logging.autorentals.svc;

import java.math.BigDecimal;


public interface AutoRentalsService {
    BigDecimal calcDelta(String autoId, String renterId);

}
