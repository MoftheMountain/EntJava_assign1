package info.ejava_student.maryc.assignment2.api.autorentals;


import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;

import java.util.Optional;

public interface AutoRentalsService {
    AutoRentalDTO createAutoRental(AutoRentalDTO newAutoRental);
    boolean hasAutoRental(String id);
    Optional<AutoRentalDTO> getAutoRental(String id);
    void removeAllAutoRentals();
    void removeAutoRental(String id);
    /*AutoRentalDTO getAutoRentalByAutoID(String id);
    AutoRentalDTO getAutoRentalByRenterID(String id);




    Page<AutoRentalDTO> queryAutoRentals(AutoRentalDTO probe, Pageable pageable);
    Page<AutoRentalDTO> searchAutoRentals(AutoRentalDTO probe, Pageable pageable);*/
}
