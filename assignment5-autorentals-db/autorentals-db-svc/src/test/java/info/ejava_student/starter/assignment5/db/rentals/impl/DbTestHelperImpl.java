package info.ejava_student.starter.assignment5.db.rentals.impl;

import info.ejava.assignments.api.autorenters.dto.rentals.TimePeriod;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTO;
import info.ejava.assignments.api.autorenters.dto.autos.AutoDTO;
import info.ejava.assignments.api.autorenters.svc.rentals.ApiTestHelper;
import info.ejava.assignments.db.autorentals.DbTestHelper;
import info.ejava.examples.common.web.ServerConfig;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalsAPI;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalsAPIClient;
import info.ejava_student.maryc.assignment2.api.autorentals.impl.ApiTestHelperImpl;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import info.ejava.assignments.api.autorenters.dto.autos.AutoDTOFactory;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTOFactory;


import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalBO;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;

public class DbTestHelperImpl extends ApiTestHelperImpl implements DbTestHelper<AutoRentalDTO, AutoRentalBO> {


    //you may need a reusable mechanism to construct DTO instances


    @Override
    public String getRentalsTableName() {
        return "rentals_autorental";
    }

    @Override
    public String getRentalsEntityName() {
        return "AutoRentalsBO";
    }

    @Override
    public String getRentalsCollectionName() {
        return "...";
    }

    /**
     * Builds a fully-populated, completed AutoRental DTO based on the information
     * that is provided by the supplied DTOs. This mapping is a server-side representation
     * and should include all fields, including derived data, and username.
     * RentalDate can be now() since there is no mapping to incoming state.
     * @param autoDTO - auto properties, including username
     * @param renterDTO - renter properties
     * @return RentalDTO instance fully populated from auto and renter info.
     */
    @Override
    public AutoRentalDTO makeFullRental(AutoDTO autoDTO, RenterDTO renterDTO, TimePeriod timePeriod, String username) {

        return null;
    }

    /**
     * @param autoDTO
     * @return AutoRentalDTO with only the autoId set
     */
    @Override
    public AutoRentalDTO makeAutoIdProbe(AutoDTO autoDTO) {
        return AutoRentalDTO.builder()
                .autoID(autoDTO.getId())
                .build();
    }

    @Override
    public String getUsername(AutoRentalDTO dto) {
        return null;
    }

    @Override
    public String getRentalId(AutoRentalBO bo) {
        return null;
    }

    @Override
    public String getAutoId(AutoRentalBO bo) {
        return null;
    }

    @Override
    public LocalDate getStartDate(AutoRentalBO bo) {
        return null;
    }

    @Override
    public LocalDate getEndDate(AutoRentalBO bo) {
        return null;
    }

    @Override
    public Integer getRenterAge(AutoRentalBO bo) {
        return null;
    }

    @Override
    public BigDecimal getAmount(AutoRentalBO bo) {
        return null;
    }

    @Override
    public String getRenterId(AutoRentalBO bo) {
        return null;
    }

    @Override
    public String getRenterName(AutoRentalBO bo) {
        return null;
    }

    @Override
    public String getMakeModel(AutoRentalBO bo) {
        return null;
    }

    @Override
    public String getUsername(AutoRentalBO bo) {
        return null;
    }

    @Override
    public String getStreet(AutoRentalBO bo) {
        return null;
    }

    @Override
    public String getCity(AutoRentalBO bo) {
        return null;
    }

    @Override
    public String getState(AutoRentalBO bo) {
        return null;
    }

    @Override
    public String getZip(AutoRentalBO bo) {
        return null;
    }
}
