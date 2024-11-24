package info.ejava_student.starter.assignment5.db.autorentals.client;

import info.ejava_student.starter.assignment2.api.autorentals.client.AutoRentalsAPI;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AutoRentalsPageableAPI extends AutoRentalsAPI {
    String AUTORENTAL_PAGED_PATH = "...";

    ResponseEntity<AutoRentalPageDTO> getAutoRentalsPaged(String autoId, String renterId, Pageable pageable);
}
