package info.ejava_student.maryc.assignment2.api.autorentals;

import info.ejava.assignments.api.autorenters.client.autos.AutosAPI;
import info.ejava.assignments.api.autorenters.client.renters.RentersAPI;
import info.ejava.assignments.api.autorenters.dto.StreetAddressDTO;
import info.ejava.assignments.api.autorenters.dto.autos.AutoDTO;
import info.ejava.assignments.api.autorenters.dto.autos.AutoDTOFactory;
import info.ejava.assignments.api.autorenters.dto.rentals.RentalDTO;
import info.ejava.assignments.api.autorenters.dto.rentals.TimePeriod;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTO;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTOFactory;
import info.ejava.assignments.api.autorenters.svc.ProvidedApiAutoRenterTestConfiguration;
import info.ejava.assignments.api.autorenters.svc.rentals.ApiTestHelper;
import info.ejava_student.maryc.assignment2.api.AutoRentalsApiApp;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalsAPIClient;
import info.ejava_student.maryc.assignment2.api.autorentals.impl.ApiImplNTestConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

//TODO: this satisfies the mandatory scenarios at the end of assignment 2
@SpringBootTest(classes= {
        AutoRentalsApiApp.class,
        ProvidedApiAutoRenterTestConfiguration.class,
        ApiImplNTestConfiguration.class
    },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Slf4j
public class AutoRentalsApiScenariosNTest {
    @Autowired
    AutosAPI autosClient;
    @Autowired
    RentersAPI rentersClient;
    @Autowired
    AutoRentalsAPIClient rentalClient;

    @BeforeEach
    void init(@Autowired AutoDTOFactory autoFactory,
              @Autowired RenterDTOFactory renterFactory) {
        Assumptions.assumeTrue(given_a_auto()!=null,"not started");
        autosClient.removeAllAutos();
        rentersClient.removeAllRenters();
    }
    private static final AtomicInteger ID = new AtomicInteger();
    //suggested helper methods
    AutoDTO given_a_auto() {
        AutoDTO newAuto = AutoDTO.builder()
                .id("auto-001")
                .make("AMGeneral")
                .model("HMMWV M113")
                .fuelType("JP8")
                .passengers(4)
                .dailyRate(new BigDecimal(78.00))
                .location(new StreetAddressDTO("911 Arnold Lane","LosAngeles","CA","90210"))
                .build();
        return newAuto;
    }
    RenterDTO given_a_renter() {
        RenterDTO newRenter = RenterDTO.builder()
                .dob(LocalDate.of(1977,6,17))
                .firstName("GI")
                .lastName("Joe")
                .id("renter-078")
                .build();
        return newRenter;
    }
    TimePeriod given_a_time_period() {
        TimePeriod newPeriod = TimePeriod.builder()
                .startDate(LocalDate.of(2024,12,24))
                .endDate(LocalDate.of(2025,1,2))
                .build();
        return newPeriod;
    }
    //return types are anything you want
    protected AutoRentalDTO given_a_proposal(AutoDTO auto, RenterDTO renter, TimePeriod timePeriod) {
        auto = Objects.requireNonNullElse(auto, given_a_auto());
        renter = Objects.requireNonNullElse(renter, given_a_renter());
        timePeriod = Objects.requireNonNullElse(timePeriod, given_a_time_period());
        return new AutoRentalDTO(auto, renter, timePeriod);
    }
    protected AutoRentalDTO given_a_proposal() {
        AutoDTO auto = given_a_auto();
        RenterDTO renter = given_a_renter();
        TimePeriod timePeriod = given_a_time_period();
        return new AutoRentalDTO(auto, renter, timePeriod);
    }
    protected AutoRentalDTO given_a_rental_contract(AutoDTO auto, RenterDTO renter, TimePeriod timePeriod) {
        AutoRentalDTO contract = given_a_proposal(auto, renter, timePeriod);
        contract.setId("rental-" + Integer.valueOf(ID.incrementAndGet()).toString());

        return contract;
    }
    protected AutoRentalDTO given_a_rental_contract() {
        return given_a_rental_contract(null, null, null);
    }

    AutoRentalDTO then_have_contract(ResponseEntity<AutoRentalDTO> response) {
        return response.getBody();
    }

    /* Create new AutoRental */

    @Test
    void can_create_autorental_success() {
        //given
        AutoDTO auto = given_a_auto();
        RenterDTO renter = given_a_renter();
        TimePeriod timePeriod = given_a_time_period();
        AutoRentalDTO newRental = given_a_proposal();
        //when
        ResponseEntity<AutoRentalDTO> response = rentalClient.createAutoRental(newRental);

        //then
        AutoRentalDTO contract = then_have_contract(response);

        BDDSoftAssertions softly = new BDDSoftAssertions();
        softly.then(response.getStatusCode()).as("statusCode").isEqualTo(HttpStatus.CREATED);
        softly.then(contract.getId()).as("rentalId").isNotEmpty();
        softly.then(contract.getAutoID()).as("autoId").isEqualTo(auto.getId());
        softly.then(contract.getRenterID()).as("renterId").isEqualTo(renter.getId());
        softly.then(contract.getMakeModel()).as("makeModel").containsPattern(Pattern.compile("^"+auto.getMake()+"?"+auto.getModel()+"$"));
        softly.assertAll();

    }
/*
    @Test
    void error_reports_auto_unknown() {
        //given
        AutoDTO auto = given_a_auto();

        //when
        //then
    }

    @Test
    void error_reports_auto_unavailable() {
        //given
        AutoDTO auto = given_a_auto();
        //when
        //then
    }

    @Test
    void error_reports_renter_unknown() {
        //given
        //when
        //then
    }

    @Test
    void error_reports_renter_too_young() {
        //given
        //when
        //then
    }
*/

    /* Update Existing AutoRental */
/*
    @Test
    void update_autorental_success() {
        //given
        RentalDTO existingContract = given_a_contract();
        //when
        //then
        RentalDTO updatedContract = then_have_contract(/*...?);
    }

    @Test
    void error_update_autorental_does_not_exist() {
        //given
        //when
        //then
    }

    @Test
    void error_update_autorental_invalid_change() {
        //given
        RentalDTO existingContract = given_a_contract();
        //when
        //then
        RentalDTO unchangedContract = then_have_contract(/*...?);
    }
    */
}
