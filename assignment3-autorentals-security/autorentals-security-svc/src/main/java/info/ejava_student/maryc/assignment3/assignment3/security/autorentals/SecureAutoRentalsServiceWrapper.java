package info.ejava_student.maryc.assignment3.assignment3.security.autorentals;

import info.ejava.assignments.api.autorenters.dto.rentals.SearchParams;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTO;
import info.ejava.assignments.api.autorenters.svc.renters.RentersService;
import info.ejava.assignments.security.autorenters.svc.AuthorizationHelper;
import info.ejava.examples.common.exceptions.ClientErrorException;
import info.ejava_student.maryc.assignment2.api.autorentals.AutoRentalsService;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class SecureAutoRentalsServiceWrapper implements AutoRentalsService {
    private final AutoRentalsService serviceImpl;
    //private final AutosService autosService;
    private final RentersService rentersService;
    private final AuthorizationHelper authzHelper;


    public AutoRentalDTO createAutoRental(AutoRentalDTO proposedRental) {
       RenterDTO renter = rentersService.getRenter(proposedRental.getRenterID());
       String currentUser = authzHelper.getUsername().get();
       boolean isSameUser = (renter.getUsername() == currentUser);
       log.debug("renter username: {}, current username: {}",renter.getUsername(),authzHelper.getUsername());
       if ((isSameUser) | (authzHelper.hasAuthority("PROXY"))) {
               return serviceImpl.createAutoRental(proposedRental);
           }else{
               throw new ClientErrorException.NotAuthorizedException("You are not authorized to create another user's rental.");
           }
   }

    @Override
    public boolean hasAutoRental(String id) {
        return false;
    }
    public AutoRentalDTO updateAutoRental(String autoRentalId, AutoRentalDTO rental) {

        final String[] existingUsername = {null};
        boolean is_proxy = authzHelper.hasAuthority("PROXY");
        String username = authzHelper.getUsername().get();

        Optional<AutoRentalDTO> existingRental = getAutoRental(autoRentalId);
        existingRental.ifPresent(r-> existingUsername[0] = r.getUsername());

        if ((existingUsername[0] == username) | is_proxy ) {
            return serviceImpl.updateAutoRental(autoRentalId, rental);
            //return serviceImpl.updateAutoRental(autoRentalId, purchaseInfo);
        }else {
            throw new ClientErrorException.NotAuthorizedException("You are not authorized to change another user's rental.");

        }
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

    public void deleteAutoRentalDTO(String id) {
        String username = authzHelper.getUsername().get();
        final String[] existingUsername = {null};

        Optional<AutoRentalDTO> existingRental = getAutoRental(id);
        existingRental.ifPresent(r-> existingUsername[0] = r.getUsername());

        if (existingUsername[0] == username) {
            serviceImpl.deleteAutoRentalDTO(id);
        }else if (authzHelper.assertMgr()) {
            serviceImpl.deleteAutoRentalDTO(id);
        }else{
            throw new ClientErrorException.NotAuthorizedException("You are not authorized to delete another user's rental.");
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteAllAutoRentals() {
        serviceImpl.deleteAllAutoRentals();
    }
}
