package info.ejava_student.maryc.assignment2.api.autorentals;


import info.ejava.assignments.api.autorenters.dto.rentals.SearchParams;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AutoRentalsService {
    AutoRentalDTO createAutoRental(AutoRentalDTO newAutoRental);
    boolean hasAutoRental(String id);
    Optional<AutoRentalDTO> getAutoRental(String id);
    void removeAllAutoRentals();
    void removeAutoRental(String id);

    AutoRentalDTO updateAutoRental(String id, AutoRentalDTO newAutoRental);

    Page<AutoRentalDTO> searchAutoRentals(SearchParams searchParams, Pageable pageable);
    Page<AutoRentalDTO> queryAutoRentals(AutoRentalDTO probe, Pageable pageable);
}
