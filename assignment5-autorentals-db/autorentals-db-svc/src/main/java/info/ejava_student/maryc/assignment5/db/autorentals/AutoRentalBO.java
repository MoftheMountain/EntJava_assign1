package info.ejava_student.maryc.assignment5.db.autorentals;

import info.ejava.assignments.api.autorenters.dto.renters.RenterDTO;
import info.ejava.assignments.db.autorenters.svc.rentals.RentalBO;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name ="RENTALS_AUTORENTALS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

// NOTE: the entityName and name used in the query must match
//      query="select r from AutoRentalBO r where startDate >= :startDate and endDate <= :endDate")
public class AutoRentalBO implements RentalBO {
    // NOTE: the entityName and name used in the named query must match
    public static final String FIND_BY_DATES_RANGE_QUERY = "AutoRentalBO.findByDatesBetween";

@PrePersist
    void prePersist() {
        if (id==null) {
            id= UUID.randomUUID().toString();
        }
    }
    @Id
    private String id;
    private String autoId;
    private String renterId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal amount;
    private String makeModel;
    private String renterName;
    private Integer renterAge;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String username;

}
