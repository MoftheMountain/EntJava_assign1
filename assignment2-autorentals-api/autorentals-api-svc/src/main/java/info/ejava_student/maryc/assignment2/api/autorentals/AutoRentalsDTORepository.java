package info.ejava_student.maryc.assignment2.api.autorentals;

import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;

import java.util.Optional;

public interface AutoRentalsDTORepository {
    AutoRentalDTO save(AutoRentalDTO autoRental);
    Optional<AutoRentalDTO> getAutoRental(String id);
    boolean hasById(String rentalId);
    void deleteById(String rentalId);
    void deleteAll();
/*    Page<AutoRentalDTO> findByAutoId(String autoId);
    Page<AutoRentalDTO> findByRenterId(String renterId);
    //need find by rentalid, findbyautoid, findbyrenterid


    Page<AutoDTO> findall(Pageable pageable);

    long count();
*/
}
