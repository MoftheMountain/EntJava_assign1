package info.ejava_student.assignment1.logging.autorentals.svc;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public interface AutoRentalsService {
    log.trace("Starting AutoRentalService");
    BigDecimal calcDelta(String autoId, String renterId);
    log.info("I'm a helpful info message.");
    log.trace("Finished AutoRentalService");
}
