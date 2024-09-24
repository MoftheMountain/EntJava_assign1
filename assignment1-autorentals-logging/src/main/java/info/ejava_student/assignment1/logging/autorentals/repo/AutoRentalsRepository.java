package info.ejava_student.assignment1.logging.autorentals.repo;

import lombok.extern.slf4j.Slf4j;

public interface AutoRentalsRepository {
    AutoRentalDTO getLeaderByAutoId(String autoId);
    AutoRentalDTO getByRenterId(String renterId);
}
