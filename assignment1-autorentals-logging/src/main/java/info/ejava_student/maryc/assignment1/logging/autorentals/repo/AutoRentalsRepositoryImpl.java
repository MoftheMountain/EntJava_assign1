package info.ejava_student.maryc.assignment1.logging.autorentals.repo;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class AutoRentalsRepositoryImpl implements AutoRentalsRepository{

    public AutoRentalDTO getLeaderByAutoId(String autoID){
        AutoRentalDTO rental = new AutoRentalDTO(autoID, "renterId", new BigDecimal(10000));
        log.trace("Created rental by autoID: {}",rental); //log rental using inferred string
        return rental;
    }

    public AutoRentalDTO getByRenterId(String renterID){
        AutoRentalDTO rental = new AutoRentalDTO("AutoID",renterID, new BigDecimal(45000));
        log.trace("Created rental by renterID: {}",rental); //log rental using inferred string
        return rental;
    }
}
