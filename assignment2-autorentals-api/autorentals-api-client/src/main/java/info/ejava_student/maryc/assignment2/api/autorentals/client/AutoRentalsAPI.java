package info.ejava_student.maryc.assignment2.api.autorentals.client;

import info.ejava.assignments.api.autorenters.dto.rentals.SearchParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public interface AutoRentalsAPI {
    public static final String AUTORENTALS_PATH = "/api/autorentals";
    public static final String AUTORENTAL_PATH = "/api/autorentals/{id}";
    public static final String AUTORENTAL_QUERY_PATH = "/api/autorentals/query";

    AutoRentalsAPIClient withRestTemplate(RestTemplate restTemplate);

    ResponseEntity<AutoRentalDTO> createAutoRental(AutoRentalDTO autoRental);
    ResponseEntity<AutoRentalDTO>  getAutoRental(String id);
    ResponseEntity<Void> hasAutoRental(String id);
    ResponseEntity<Void> removeAutoRental(String id);
    ResponseEntity<Void> removeAllAutoRentals();

    ResponseEntity<AutoRentalDTO>  updateAutoRental(String id, AutoRentalDTO autoRental);
    ResponseEntity<AutoRentalListDTO> queryAutoRentals(AutoRentalDTO probe, Integer pageNumber, Integer pageSize);
    ResponseEntity<AutoRentalListDTO> searchAutoRentals(SearchParams searchParams);
}
