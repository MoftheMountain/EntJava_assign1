package info.ejava_student.maryc.assignment5.db.autorentals.client;

import info.ejava_student.starter.assignment2.api.autorentals.client.AutoRentalsAPIClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class AutoRentalsPageableAPIClient extends AutoRentalsAPIClient implements AutoRentalsPageableAPI {
    @Override
    public ResponseEntity<AutoRentalPageDTO> getAutoRentalsPaged(String autoId, String renterId, Pageable pageable) {
        return null;
    }
}
