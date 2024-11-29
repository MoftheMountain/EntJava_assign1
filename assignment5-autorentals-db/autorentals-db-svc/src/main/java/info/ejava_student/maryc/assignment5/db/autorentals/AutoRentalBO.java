package info.ejava_student.maryc.assignment5.db.autorentals;

import info.ejava.assignments.api.autorenters.dto.renters.RenterDTO;
import info.ejava.assignments.db.autorenters.svc.rentals.RentalBO;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

// NOTE: the entityName and name used in the query must match
//      query="select r from AutoRentalBO r where startDate >= :startDate and endDate <= :endDate")
public class AutoRentalBO implements RentalBO {
    // NOTE: the entityName and name used in the named query must match
    public static final String FIND_BY_DATES_RANGE_QUERY = "AutoRentalBO.findByDatesBetween";

//    @PrePersist
    void prePersist() {
        if (id==null) {
            id= UUID.randomUUID().toString();
        }
    }

    private String id;
    private String autoId;
    //...
    private String street;
    private String city;
    //...

}
