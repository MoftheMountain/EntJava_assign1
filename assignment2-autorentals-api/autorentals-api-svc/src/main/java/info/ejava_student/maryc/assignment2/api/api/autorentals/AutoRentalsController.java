package info.ejava_student.maryc.assignment2.api.api.autorentals;

import info.ejava.assignments.api.autorenters.client.autos.AutosAPI;
import info.ejava.assignments.api.autorenters.dto.autos.AutoDTO;
import info.ejava_student.maryc.assignment2.api.autorentals.client.client.AutoRentalDTO;
import info.ejava_student.maryc.assignment2.api.autorentals.client.client.AutoRentalsAPI;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AutoRentalsController {
    private final AutoRentalsService autoRentalsService;
    @PostConstruct
    public void init() {
        log.info("Autos initialized, URI={}", AutoRentalsAPI.AUTORENTALS_PATH);
    }

    @RequestMapping(path= AutoRentalsAPI.AUTORENTALS_PATH,
            method= RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AutoRentalDTO> createAutoRental(
            @RequestBody AutoRentalDTO autoRental) {
        AutoRentalDTO addedAutoRental = autoRentalsService.createAutoRental(autoRental);

        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .replacePath(AutoRentalsAPI.AUTORENTALS_PATH)
                .build(addedAutoRental.getId());
        ResponseEntity<AutoRentalDTO> response = ResponseEntity.created(location).body(addedAutoRental);
        return response;
    }

}
