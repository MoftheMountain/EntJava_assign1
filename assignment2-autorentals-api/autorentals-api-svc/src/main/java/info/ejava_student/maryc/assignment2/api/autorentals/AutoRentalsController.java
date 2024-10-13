package info.ejava_student.maryc.assignment2.api.autorentals;

import info.ejava.assignments.api.autorenters.client.autos.AutosAPI;
import info.ejava.assignments.api.autorenters.dto.autos.AutoDTO;
import info.ejava.assignments.api.autorenters.dto.autos.AutoListDTO;
import info.ejava.assignments.api.autorenters.dto.rentals.SearchParams;
import info.ejava.assignments.api.autorenters.dto.rentals.TimePeriod;
import info.ejava.examples.common.exceptions.ClientErrorException;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalListDTO;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalsAPI;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
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
            method= RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AutoRentalDTO> updateAuto(@PathVariable("id") String id,
                                              @RequestBody AutoRentalDTO autoRentalUpdate) {
        AutoRentalDTO updatedAutoRental = autoRentalsService.updateAutoRental(id, autoRentalUpdate);

        ResponseEntity<AutoRentalDTO> response = ResponseEntity.ok(updatedAutoRental);
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


    @RequestMapping(path= AutoRentalsAPI.AUTORENTALS_PATH,
            method= RequestMethod.GET,
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AutoRentalListDTO> searchAutoRentals(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,

            @RequestParam(value = "autoId", required = false) String autoId,
            @RequestParam(value = "renterId", required = false) String renterId,
 
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        Pageable pageable = (null!=pageSize && null!=pageNumber && pageSize>0 && pageNumber>=0) ?
                PageRequest.of(pageNumber, pageSize) : Pageable.unpaged();
        SearchParams searchParams = SearchParams.builder()
                .timePeriod(new TimePeriod(startDate, endDate))
                .autoId(autoId)
                .renterId(renterId)
                .build();
        Page<AutoRentalDTO> autoRentalsPage = autoRentalsService.searchAutoRentals(searchParams, pageable);

        AutoRentalListDTO autoRentalsList = new AutoRentalListDTO(autoRentalsPage.toList());
        ResponseEntity<AutoRentalListDTO> response = ResponseEntity.ok(autoRentalsList);
        return response;
    }

    @RequestMapping(path= AutoRentalsAPI.AUTORENTAL_QUERY_PATH,
            method= RequestMethod.POST,
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AutoRentalListDTO> queryAutoRentals(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestBody AutoRentalDTO probe) {
        Pageable pageable = (null!=pageSize && null!=pageNumber && pageSize>0 && pageNumber>=0) ?
                PageRequest.of(pageNumber, pageSize) : Pageable.unpaged();
        Page<AutoRentalDTO> autoRentalsPage = autoRentalsService.queryAutoRentals(probe, pageable);

        AutoRentalListDTO autoRentalsList = new AutoRentalListDTO(autoRentalsPage.toList());
        ResponseEntity<AutoRentalListDTO> response = ResponseEntity.ok(autoRentalsList);
        return response;
    }

}
