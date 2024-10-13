package info.ejava_student.maryc.assignment2.api.autorentals;

import info.ejava.assignments.api.autorenters.dto.rentals.TimePeriod;
import info.ejava.assignments.api.autorenters.svc.POJORepository;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AutoRentalsDTORepository {
    AutoRentalDTO save(AutoRentalDTO autoRental);
    Optional<AutoRentalDTO> getAutoRental(String id);
    boolean hasById(String rentalId);
    void deleteById(String rentalId);
    void deleteAll();
    Page<AutoRentalDTO> findAll(Pageable pageable);
    Page<AutoRentalDTO> findAll(Example<AutoRentalDTO> example, Pageable pageable);
    Page<AutoRentalDTO> findConflicts(String autoId, String renterId, TimePeriod timePeriod, Pageable pageable);
}