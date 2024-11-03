package info.ejava_student.maryc.assignment3.assignment3.security.autorentals;

import info.ejava.assignments.api.autorenters.dto.rentals.SearchParams;
import info.ejava_student.maryc.assignment2.api.autorentals.AutoRentalsService;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class SecureAutoRentalsServiceWrapper implements AutoRentalsService {
    private final AutoRentalsService serviceImpl;
    //...
    //anything unique to this solution can be @Autowired


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "admin", produces = {MediaType.TEXT_PLAIN_VALUE})
    public AutoRentalDTO createAutoRental(AutoRentalDTO proposedRental) {
        AutoRentalDTO rental = serviceImpl.createAutoRental(proposedRental);
        return rental;
    }

    @Override
    public boolean hasAutoRental(String id) {
        return false;
    }

    @PreAuthorize("hasAuthority('PROXY')")
    public AutoRentalDTO updateAutoRental(String autoRentalId, AutoRentalDTO rental) {
        //return serviceImpl.updateAutoRental(autoRentalId, purchaseInfo);
        return serviceImpl.updateAutoRental(autoRentalId,rental);
    }

    public Optional<AutoRentalDTO> getAutoRental(String id) {
        return serviceImpl.getAutoRental(id);
    }

    @Override
    public Page<AutoRentalDTO> searchAutoRentals(SearchParams searchParams, Pageable pageable) {
        return serviceImpl.searchAutoRentals(searchParams,pageable);
    }

    @Override
    public Page<AutoRentalDTO> queryAutoRentals(AutoRentalDTO probe, Pageable pageable) {
        return serviceImpl.queryAutoRentals(probe,pageable);
    }

    public Page<AutoRentalDTO> findAutoRentals(SearchParams searchParams, Pageable pageable) {
        //return serviceImpl.findAutoRentals(searchParams, pageable);
        return null;
    }

    @PreAuthorize("hasRole('MGR')")
    public void deleteAutoRentalDTO(String id) {
        serviceImpl.deleteAutoRentalDTO(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllAutoRentals() {
        serviceImpl.deleteAllAutoRentals();
    }
}
