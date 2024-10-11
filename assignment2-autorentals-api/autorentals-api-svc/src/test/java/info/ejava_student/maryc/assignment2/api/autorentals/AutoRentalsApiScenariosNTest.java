package info.ejava_student.maryc.assignment2.api.autorentals;

import info.ejava.assignments.api.autorenters.client.autos.AutosAPI;
import info.ejava.assignments.api.autorenters.client.renters.RentersAPI;
import info.ejava.assignments.api.autorenters.dto.autos.AutoDTO;
import info.ejava.assignments.api.autorenters.dto.autos.AutoDTOFactory;
import info.ejava.assignments.api.autorenters.dto.rentals.RentalDTO;
import info.ejava.assignments.api.autorenters.dto.rentals.TimePeriod;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTO;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTOFactory;
import info.ejava.assignments.api.autorenters.svc.ProvidedApiAutoRenterTestConfiguration;
import info.ejava_student.maryc.assignment2.api.AutoRentalsApiApp;
import info.ejava_student.maryc.assignment2.api.autorentals.impl.ApiImplNTestConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @BeforeEach
    void init(@Autowired AutoDTOFactory autoFactory,
              @Autowired RenterDTOFactory renterFactory) {
        Assumptions.assumeTrue(given_a_auto()!=null,"not started");
        autosClient.removeAllAutos();
        rentersClient.removeAllRenters();
    }

    //suggested helper methods
    AutoDTO given_a_auto() {
        return null;
    }
    RenterDTO given_a_renter() {
        return null;
    }
    TimePeriod given_a_time_period() { return null; }
    //return types are anything you want
    RentalDTO given_a_proposal(/*...?*/) { return null; }
    RentalDTO given_a_contract(/*...?*/) { return null; }
    RentalDTO then_have_contract(/*...?*/) { return null; }

    /* Create new AutoRental */

    @Test
    void can_create_autorental_success() {
        //given
        AutoDTO auto = given_a_auto();

        //when
        //then
        RentalDTO contract = then_have_contract(/*...?*/);
    }

    @Test
    void error_reports_auto_unknown() {
        //given
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


    /* Update Existing AutoRental */

    @Test
    void update_autorental_success() {
        //given
        RentalDTO existingContract = given_a_contract();
        //when
        //then
        RentalDTO updatedContract = then_have_contract(/*...?*/);
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
        RentalDTO unchangedContract = then_have_contract(/*...?*/);
    }
}
