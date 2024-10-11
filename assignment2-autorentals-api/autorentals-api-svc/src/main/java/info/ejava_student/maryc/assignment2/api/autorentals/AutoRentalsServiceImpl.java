package info.ejava_student.maryc.assignment2.api.autorentals;

import info.ejava.assignments.api.autorenters.dto.autos.AutoDTO;
import info.ejava.assignments.api.autorenters.dto.rentals.TimePeriod;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTO;
import info.ejava.assignments.api.autorenters.svc.autos.AutosService;
import info.ejava.assignments.api.autorenters.svc.renters.RentersService;
import info.ejava.examples.common.exceptions.ClientErrorException;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Period;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class AutoRentalsServiceImpl implements AutoRentalsService{

    private final AutoRentalsDTORepository repo;
    private final AutosService autosService;
    private final RentersService rentersService;

    @Override
    public AutoRentalDTO createAutoRental(AutoRentalDTO newAutoRental) {
        AutoDTO auto = null;
        RenterDTO renter = null;
        if (null == newAutoRental ){
            throw new ClientErrorException.NotFoundException("Rental is missing.") {
            };
        }

        //check for auto
        String autoId = newAutoRental.getAutoID();
        if (null == newAutoRental.getAutoID()){
            log.debug("Auto ID not found: {}", newAutoRental);
            throw new ClientErrorException.NotFoundException("Auto is missing.");
        }else {
            try{
                auto = autosService.getAuto(autoId);
            }catch (Exception e) {
                throw new ClientErrorException.BadRequestException("Auto {} is not in auto repository. " + e, autoId);
            }
        }

        //check for renter
        String renterId = newAutoRental.getRenterID();
        if (null == newAutoRental.getRenterID()){
            log.debug("Renter ID not found: {}", newAutoRental);
            throw new ClientErrorException.NotFoundException("Renter is missing from rental.");
        }else {
            try {
                renter = rentersService.getRenter(renterId);
            }catch (Exception e){
                throw new ClientErrorException.BadRequestException("Renter {} is not in renter repository. " + e, renterId);
            }
        }

        //check for dates
        if (null==newAutoRental.getStartDate() || null == newAutoRental.getEndDate()) {
            log.debug("Missing rental date: Start or End date is missing.");
            throw new ClientErrorException.NotFoundException("Rental date is missing");
        }else {
            TimePeriod rentalLength = new TimePeriod(newAutoRental.getStartDate(), newAutoRental.getEndDate());
            if (true){//TimePeriod(dbAR.startDate, dbAR.EndDate).isBetween(newAutoRental.getStartDate()) & TimePeriod(dbAR.startDate, dbAR.EndDate).isBetween(newAutoRental.getEndDate()
                BigDecimal dailyRate = auto.getDailyRate();
                newAutoRental.setAmount(dailyRate.multiply(BigDecimal.valueOf(rentalLength.getDays())));
            }else{
                throw new ClientErrorException.InvalidInputException("Rental is not available during this time period.");
            }
        }
        //get renter parameters
        //TimePeriod renterAge = new TimePeriod(renter.getDob(), LocalDate.now());
        Period renterAge = Period.between(renter.getDob(),LocalDate.now());
        if (renterAge.getYears() < 21){
            log.debug("Renters must be 21. Renter age: {}",renterAge.getYears());
            throw new ClientErrorException.InvalidInputException("Renter is too young. Age must be 21.");
        }else {
            newAutoRental.setRenterAge(renterAge.getYears());
        }

        newAutoRental.setRenterName(renter.getFirstName() + " " + renter.getLastName());

        //get auto parameters
        newAutoRental.setMakeModel(auto.getMake() + " " + auto.getModel());

        AutoRentalDTO savedAutoRental = repo.save(newAutoRental);
        log.debug("added auto: {}",savedAutoRental);
        return savedAutoRental;

    }

    @Override
    public Optional<AutoRentalDTO> getAutoRental(String id) {
        Optional<AutoRentalDTO> rental = repo.getAutoRental(id);
        rental.ifPresentOrElse(AutoRentalDTO -> {},
                        () -> {throw new ClientErrorException.NotFoundException("Rental id {} was not found.",id);}
        );
        return rental;
    }

    @Override
    public boolean hasAutoRental(String id) {
        return repo.hasById(id);
    }

    @Override
    public void removeAllAutoRentals() {
        repo.deleteAll();
    }

    @Override
    public void removeAutoRental(String id) {
        repo.deleteById(id);
    }


/*
    @Override
    public AutoRentalDTO getAutoRentalByAutoID(String id) {
        return null;
    }

    @Override
    public AutoRentalDTO getAutoRentalByRenterID(String id) {
        return null;
    }





    @Override
    public Page<AutoRentalDTO> queryAutoRentals(AutoRentalDTO probe, Pageable pageable) {
        return null;
    }

    @Override
    public Page<AutoRentalDTO> searchAutoRentals(AutoRentalDTO probe, Pageable pageable) {
        return null;
    }*/
}