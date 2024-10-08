package info.ejava_student.maryc.assignment2.api.api.autorentals;

import info.ejava.assignments.api.autorenters.client.autos.AutosAPIClient;
import info.ejava.assignments.api.autorenters.svc.autos.AutosService;
import info.ejava.assignments.api.autorenters.svc.renters.RentersService;
import info.ejava_student.maryc.assignment2.api.autorentals.client.client.AutoRentalDTO;
import info.ejava_student.maryc.assignment2.api.autorentals.client.client.AutoRentalsAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AutoRentalsServiceImpl implements AutoRentalsService{

    private final AutoRentalsDTORepository repo;
    private final AutosService autosService;
    private final RentersService rentersService;

    @Override
    public AutoRentalDTO createAutoRental(AutoRentalDTO newAutoRental) {
        if (null==newAutoRental ){
            //Throw Exception: rental is required
        }
        if (null==newAutoRental.getAutoID()){
            //Throw Exception: Missing valid Auto ID
            //log.debug("Auto ID not found: {}", newAutoRental
        }
        if (null==newAutoRental.getRenterID()){
            //Throw Exception: Missing valid Renter ID
            //log.debug("Renter ID not found: {}", newAutoRental
        }
        AutoRentalDTO savedAutoRental = repo.save(newAutoRental);
        //log.debug("added auto: {}",savedAutoRental);
        return savedAutoRental;
    }

    @Override
    public AutoRentalDTO getAutoRental(String id) {
        return null;
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
    public boolean existsAutoRental(String id) {
        return false;
    }

    @Override
    public AutoRentalDTO removeAllAutoRentals() {
        return null;
    }

    @Override
    public AutoRentalDTO removeAutoRental(String id) {
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