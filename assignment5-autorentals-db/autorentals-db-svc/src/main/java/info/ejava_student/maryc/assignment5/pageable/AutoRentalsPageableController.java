package info.ejava_student.maryc.assignment5.pageable;

import info.ejava_student.maryc.assignment5.db.autorentals.client.AutoRentalPageDTO;
import info.ejava_student.maryc.assignment5.db.autorentals.client.AutoRentalsPageableAPI;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This controller adds an additional endpoint not included in the
 * original API.
 */
public class AutoRentalsPageableController {
    @GetMapping(path = AutoRentalsPageableAPI.AUTORENTAL_PAGED_PATH,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AutoRentalPageDTO> findAutosBy(
            //...
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value="sort", required = false) String sortString) {
        return null;
    }
}
