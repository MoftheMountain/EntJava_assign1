package info.ejava_student.assignment1.logging.autorentals.repo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public interface AutoRentalsRepository {
    log.trace("Calling getLeaderByAutoId");
    AutoRentalDTO getLeaderByAutoId(String autoId);
    log.trace("Finished getLeaderByAutoId, Calling getByRenterId");
    AutoRentalDTO getByRenterId(String renterId);
    log.trace("Finished getByRenterId");
}
