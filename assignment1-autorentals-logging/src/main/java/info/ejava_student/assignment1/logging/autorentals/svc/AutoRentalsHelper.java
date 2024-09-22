package info.ejava_student.assignment1.logging.autorentals.svc;

import info.ejava_student.assignment1.logging.autorentals.repo.AutoRentalDTO;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public interface AutoRentalsHelper {
    log.trace("Starting calcDelta in the helper");
    BigDecimal calcDelta(AutoRentalDTO leader, AutoRentalDTO targetResult);
    log.trace("Finished calcDelta in the helper");
    log.debug("This is a debug message from the end of the helper");
}
