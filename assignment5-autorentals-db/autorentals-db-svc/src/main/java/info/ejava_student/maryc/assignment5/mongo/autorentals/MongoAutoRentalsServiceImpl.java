package info.ejava_student.maryc.assignment5.mongo.autorentals;

import info.ejava.assignments.api.autorenters.dto.autos.AutoDTO;
import info.ejava.assignments.api.autorenters.dto.rentals.SearchParams;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTO;
import info.ejava.assignments.api.autorenters.svc.autos.AutosService;
import info.ejava.assignments.api.autorenters.svc.renters.RentersService;
import info.ejava.assignments.db.autorenters.svc.rentals.RentalsMapper;
import info.ejava.examples.common.exceptions.ClientErrorException;
import info.ejava_student.maryc.assignment2.api.autorentals.AutoRentalsService;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalBO;
import info.ejava_student.maryc.assignment5.mongo.autorentals.MongoAutoRentalsRepository;
import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class MongoAutoRentalsServiceImpl implements AutoRentalsService {
    private MongoAutoRentalsRepository repo;
    private AutosService autosService;
    private RentersService rentersService;
    private RentalsMapper<AutoRentalDTO, AutoRentalBO> mapper;





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
        if (null == autoId){
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
        if (null == renterId){
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
            SearchParams searchParams = SearchParams.builder()
                    .autoId(newAutoRental.getAutoID())
                    .renterId(newAutoRental.getRenterID())
                    .timePeriod(newAutoRental.makeTimePeriod())
                    .build();
            Page<AutoRentalDTO> rentalConflicts = searchAutoRentals(searchParams,Pageable.unpaged());
            if (!rentalConflicts.hasContent()){
                BigDecimal dailyRate = auto.getDailyRate();
                newAutoRental.setAmount(dailyRate.multiply(BigDecimal.valueOf(newAutoRental.makeTimePeriod().getDays())));
            }else{
                throw new ClientErrorException.InvalidInputException("Time conflict. Rental is not available during this time period.");
            }
        }
        //get renter parameters
        //TimePeriod renterAge = new TimePeriod(renter.getDob(), LocalDate.now());
        Period renterAge = Period.between(renter.getDob(), LocalDate.now());
        if (renterAge.getYears() < 21){
            log.debug("Renters must be 21. Renter age: {}",renterAge.getYears());
            throw new ClientErrorException.InvalidInputException("Renter is too young. Age must be 21.");
        }else {
            newAutoRental.setRenterAge(renterAge.getYears());
        }

        newAutoRental.setRenterName(renter.getFirstName() + " " + renter.getLastName());
        newAutoRental.setUsername(renter.getUsername());

        //get auto parameters
        newAutoRental.setMakeModel(auto.getMake() + " " + auto.getModel());
        newAutoRental.setStreetAddress(auto.getLocation());

        AutoRentalBO rentalBO = mapper.map(newAutoRental);
        repo.save(rentalBO);
        return mapper.map(rentalBO);
    }

    @Override
    public boolean hasAutoRental(String id) {
        return repo.existsById(id);
    }

    @Override
    public Optional<AutoRentalDTO> getAutoRental(String id) {

        Optional<AutoRentalBO> rentalBO = repo.findById(id);
        rentalBO.ifPresentOrElse (bo -> {},
                () -> {throw new ClientErrorException.NotFoundException("Rental id {} was not found.",id);}
        );
        return null; //mapper.map(rentalBO);
    }

    @Override
    public void deleteAllAutoRentals() {
        repo.deleteAll();
    }

    @Override
    public void deleteAutoRentalDTO(String id) {
        repo.deleteById(id);
    }

    @Override
    public AutoRentalDTO updateAutoRental(String id, AutoRentalDTO newAutoRental) {
        AutoDTO auto = null;

        if (null == newAutoRental) {
            throw new ClientErrorException.InvalidInputException("Rental is required.");
        }
        if(null == id){
            throw new ClientErrorException.InvalidInputException("Rental ID is required.");
        } else {
            if (!repo.existsById(newAutoRental.getId())){
                throw new ClientErrorException.NotFoundException("Rental ID {} does not exist, rental cannot be updated.", newAutoRental.getId());
            }
        }

        //check for renter
        String renterId = newAutoRental.getRenterID();
        if (null == renterId) {
            log.debug("Renter ID not found: {}", renterId);
            throw new ClientErrorException.NotFoundException("Renter is required.");
        }
        if (null == newAutoRental.getStartDate() || null == newAutoRental.getEndDate()) {
            log.debug("Missing rental date: Start or End date is missing.");
            throw new ClientErrorException.NotFoundException("Rental date is missing");
        }

        //check for auto
        String autoId = newAutoRental.getAutoID();
        if (null == autoId){
            log.debug("Auto ID not found: {}", newAutoRental);
            throw new ClientErrorException.NotFoundException("Auto is missing.");
        }else {
            try{
                auto = autosService.getAuto(autoId);
            }catch (Exception e) {
                throw new ClientErrorException.BadRequestException("Auto {} is not in auto repository. " + e, autoId);
            }
        }

        SearchParams searchParams = SearchParams.builder()
                .autoId(newAutoRental.getAutoID())
                .renterId(newAutoRental.getRenterID())
                .timePeriod(newAutoRental.makeTimePeriod())
                .build();
        Page<AutoRentalDTO> rentalConflicts = searchAutoRentals(searchParams, Pageable.unpaged());
        if (!rentalConflicts.hasContent()) {
            BigDecimal dailyRate = auto.getDailyRate();
            newAutoRental.setAmount(dailyRate.multiply(BigDecimal.valueOf(newAutoRental.makeTimePeriod().getDays())));
        } else {
            throw new ClientErrorException.InvalidInputException("Time conflict. Rental is not available during this time period.");
        }

        AutoRentalBO rentalBO = mapper.map(newAutoRental);
        repo.save(rentalBO);
        return mapper.map(rentalBO);

    }

    @Override
    public Page<AutoRentalDTO> searchAutoRentals(SearchParams searchParams, Pageable pageable) {
        return null;
    }

    @Override
    public Page<AutoRentalDTO> queryAutoRentals(AutoRentalDTO probe, Pageable pageable) {
        Page<AutoRentalBO> rentalBOPage=null;
        if((probe.getAutoID()==null &&
                probe.getRenterID()==null) &&
                probe.makeTimePeriod()==null){
            rentalBOPage = repo.findAll(pageable);
        } else {
            AutoRentalBO probeBO  = mapper.map(probe);
            rentalBOPage = repo.findAll(Example.of(probeBO), pageable);
        }
        if (pageable.isPaged()) {
            log.debug("pageSize {}/pageNumber {}, returns {}", pageable.getPageSize(), pageable.getPageNumber(), rentalBOPage.getNumberOfElements());
        } else {
            log.debug("nonPaged returns {}", rentalBOPage.getNumberOfElements());
        }
        return rentalBOPage.map(bo->mapper.map(bo));
    }
}
