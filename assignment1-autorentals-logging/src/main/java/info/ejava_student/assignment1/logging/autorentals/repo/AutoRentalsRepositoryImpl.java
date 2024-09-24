package info.ejava_student.assignment1.logging.autorentals.repo;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class AutoRentalsRepositoryImpl implements AutoRentalsRepository{

    public AutoRentalDTO getLeaderByAutoId(String autoID){
        AutoRentalDTO rental = new AutoRentalDTO(autoID, "renterId", new BigDecimal(10000));
        log.trace("Entered getLeaderByAutoId"); //log rental
        return rental;
    }

    public AutoRentalDTO getByRenterId(String renterID){
        AutoRentalDTO rental = new AutoRentalDTO("AutoID",renterID, new BigDecimal(45000));
        log.trace("Entered getByRenterId"); //log rental
        return rental;
    }
}
