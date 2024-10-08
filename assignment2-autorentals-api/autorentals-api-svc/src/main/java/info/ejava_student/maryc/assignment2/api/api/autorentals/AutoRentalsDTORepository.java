package info.ejava_student.maryc.assignment2.api.api.autorentals;

import info.ejava.assignments.api.autorenters.dto.autos.AutoDTO;
import info.ejava_student.maryc.assignment2.api.autorentals.client.client.AutoRentalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AutoRentalsDTORepository {
    AutoRentalDTO save(AutoRentalDTO autoRental);
    Optional<AutoRentalDTO> getAutoRental(String id);

/*    Page<AutoRentalDTO> findByAutoId(String autoId);
    Page<AutoRentalDTO> findByRenterId(String renterId);
    //need find by rentalid, findbyautoid, findbyrenterid
    boolean existsById(String rentalId);

    Page<AutoDTO> findall(Pageable pageable);

    long count();

    void deleteById(String rentalId);
    void deleteAll();*/
}
