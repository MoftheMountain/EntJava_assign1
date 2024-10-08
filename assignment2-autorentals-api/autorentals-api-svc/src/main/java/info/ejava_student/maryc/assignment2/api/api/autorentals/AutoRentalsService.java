package info.ejava_student.maryc.assignment2.api.api.autorentals;


import info.ejava_student.maryc.assignment2.api.autorentals.client.client.AutoRentalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AutoRentalsService {
    AutoRentalDTO createAutoRental(AutoRentalDTO newAutoRental);

    AutoRentalDTO getAutoRental(String id);
    /*AutoRentalDTO getAutoRentalByAutoID(String id);
    AutoRentalDTO getAutoRentalByRenterID(String id);
    boolean existsAutoRental(String id);

    AutoRentalDTO removeAllAutoRentals();
    AutoRentalDTO removeAutoRental(String id);

    Page<AutoRentalDTO> queryAutoRentals(AutoRentalDTO probe, Pageable pageable);
    Page<AutoRentalDTO> searchAutoRentals(AutoRentalDTO probe, Pageable pageable);*/
}
