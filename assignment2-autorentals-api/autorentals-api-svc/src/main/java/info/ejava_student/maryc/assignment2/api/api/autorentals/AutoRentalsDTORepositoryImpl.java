package info.ejava_student.maryc.assignment2.api.api.autorentals;

import info.ejava_student.maryc.assignment2.api.autorentals.client.client.AutoRentalDTO;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AutoRentalsDTORepositoryImpl implements AutoRentalsDTORepository{

    private final Map<String, AutoRentalDTO> autoRentals = new ConcurrentHashMap<>();
    private final AtomicInteger ID = new AtomicInteger(new SecureRandom().nextInt(1000));

    @Override
    public AutoRentalDTO save(AutoRentalDTO autoRental) {
        if (autoRental!=null){
            String id = autoRental.getId()!=null ?
                    autoRental.getId() :
                    "rental-" +Integer.valueOf(ID.incrementAndGet()).toString();
            autoRental.setId(id);
            autoRentals.put(autoRental.getId(), autoRental);
        }
        return autoRental;
    }

    public Optional<AutoRentalDTO> getAutoRental(String id){
        AutoRentalDTO autoRental = autoRentals.get(id);
        return autoRental!=null ? Optional.of(autoRental) : Optional.empty();
    }

/*
    @Override
    public Page<AutoRentalDTO> findByAutoId(String autoId) {
        //Has to use querying to get all back
        //Page<AutoRentalDTO> rental = getAutoRental(autoId,"rental", new TimePeriod(LocalDate.of(2024,5,4),7));
        //log.trace("Created rental by autoID: {}",rental); //log rental using inferred string
        return null;//return rental;
    }

    @Override
    public Page<AutoRentalDTO> findByRenterId(String renterId) {
        //Page<AutoRentalDTO> rental = getAutoRental("auto",renterId,new TimePeriod(LocalDate.of(2024,5,4),7));
        return null;//return rental;
    }

    @Override
    public boolean existsById(String rentalId) {

        return false;
    }

    @Override
    public Page<AutoDTO> findall(Pageable pageable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String rentalId) {

    }

    @Override
    public void deleteAll() {

    }*/
}
