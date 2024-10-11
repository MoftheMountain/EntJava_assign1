package info.ejava_student.maryc.assignment2.api.autorentals;

import info.ejava.examples.common.exceptions.ClientErrorException;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalsAPI;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AutoRentalsController {
    private final AutoRentalsService autoRentalsService;
    @PostConstruct
    public void init() {
        log.info("AutoRentals initialized, URI={}", AutoRentalsAPI.AUTORENTALS_PATH);
    }

    @RequestMapping(path= AutoRentalsAPI.AUTORENTALS_PATH,
            method= RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AutoRentalDTO> createAutoRental(
            @RequestBody AutoRentalDTO autoRental) {
        AutoRentalDTO addedAutoRental = autoRentalsService.createAutoRental(autoRental);

        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .replacePath(AutoRentalsAPI.AUTORENTAL_PATH)
                .build(addedAutoRental.getId());
        ResponseEntity<AutoRentalDTO> response = ResponseEntity.created(location).body(addedAutoRental);
        return response;
    }


    @RequestMapping(path= AutoRentalsAPI.AUTORENTAL_PATH,
            method= RequestMethod.GET,
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<AutoRentalDTO>> getAutoRental(@PathVariable("id") String id) {
        Optional<AutoRentalDTO> autoRental = autoRentalsService.getAutoRental(id);

        ResponseEntity<Optional<AutoRentalDTO>> response = ResponseEntity.ok(autoRental);
        return response;
    }



    @RequestMapping(path= AutoRentalsAPI.AUTORENTAL_PATH,
            method= RequestMethod.HEAD,
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> hasAutoRental(@PathVariable("id") String id) {
        boolean exists = autoRentalsService.hasAutoRental(id);
        if (!exists) {
            throw new ClientErrorException.NotFoundException("Auto Rental [%s] does not exist", id);
        }

        ResponseEntity<Void> response = ResponseEntity.ok().build();
        return response;
    }

    @DeleteMapping(path= AutoRentalsAPI.AUTORENTALS_PATH)
    public ResponseEntity<Void> removeAllAutoRentals() {
        autoRentalsService.removeAllAutoRentals();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path= AutoRentalsAPI.AUTORENTAL_PATH)
    public ResponseEntity<Void> removeAutoRental(@PathVariable("id") String id) {
        autoRentalsService.removeAutoRental(id);
        return ResponseEntity.noContent().build();
    }



}
