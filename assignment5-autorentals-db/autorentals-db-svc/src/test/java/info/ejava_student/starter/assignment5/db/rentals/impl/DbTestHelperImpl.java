package info.ejava_student.starter.assignment5.db.rentals.impl;

import info.ejava.assignments.api.autorenters.dto.rentals.SearchParams;
import info.ejava.assignments.api.autorenters.dto.rentals.TimePeriod;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTO;
import info.ejava.assignments.api.autorenters.dto.autos.AutoDTO;
import info.ejava.assignments.db.autorentals.DbTestHelper;
import info.ejava_student.maryc.assignment2.api.autorentals.impl.ApiTestHelperImpl;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import info.ejava.assignments.api.autorenters.dto.autos.AutoDTOFactory;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTOFactory;


import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalBO;

import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalMapper;
import info.ejava_student.maryc.assignment5.jpa.autorentals.JpaAutoRentalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.Period;

public class DbTestHelperImpl extends ApiTestHelperImpl implements DbTestHelper<AutoRentalDTO, AutoRentalBO> {


    //you may need a reusable mechanism to construct DTO instances

    @Override
    public String getRentalsTableName() {
        return "rentals_autorental";
    }

    @Override
    public String getRentalsEntityName() {
        return "AutoRentalBO";
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

        BigDecimal dailyRate = autoDTO.getDailyRate();
        AutoRentalDTO rentalDTO = AutoRentalDTO.builder()
                .autoID(autoDTO.getId())
                .renterID(renterDTO.getId())
                .startDate(timePeriod.getStartDate())
                .endDate(timePeriod.getEndDate())
                .makeModel(autoDTO.getMake() + " " + autoDTO.getModel())
                .renterName(renterDTO.getFirstName() + " " + renterDTO.getLastName())
                .renterAge(Period.between(renterDTO.getDob(),LocalDate.now()).getYears())
                .amount(dailyRate.multiply(BigDecimal.valueOf(timePeriod.getDays())))
                .streetAddress(autoDTO.getLocation())
                .username(username)
                .build();

        return rentalDTO;
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
        return dto.getUsername();
    }

    @Override
    public String getRentalId(AutoRentalBO bo) {
        return bo.getId();
    }

    @Override
    public String getAutoId(AutoRentalBO bo) {
        return bo.getAutoId();
    }

    @Override
    public LocalDate getStartDate(AutoRentalBO bo) {
        return bo.getStartDate();
    }

    @Override
    public LocalDate getEndDate(AutoRentalBO bo) {
        return bo.getEndDate();
    }

    @Override
    public Integer getRenterAge(AutoRentalBO bo) {
        return bo.getRenterAge();
    }

    @Override
    public BigDecimal getAmount(AutoRentalBO bo) {
        return bo.getAmount();
    }

    @Override
    public String getRenterId(AutoRentalBO bo) {
        return bo.getRenterId();
    }

    @Override
    public String getRenterName(AutoRentalBO bo) {
        return bo.getRenterName();
    }

    @Override
    public String getMakeModel(AutoRentalBO bo) {
        return bo.getMakeModel();
    }

    @Override
    public String getUsername(AutoRentalBO bo) {
        return bo.getUsername();
    }

    @Override
    public String getStreet(AutoRentalBO bo) {
        return bo.getStreet();
    }

    @Override
    public String getCity(AutoRentalBO bo) {
        return bo.getCity();
    }

    @Override
    public String getState(AutoRentalBO bo) {
        return bo.getState();
    }

    @Override
    public String getZip(AutoRentalBO bo) {
        return bo.getZip();
    }
}
