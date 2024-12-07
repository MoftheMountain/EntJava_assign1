package info.ejava_student.maryc.assignment5.db.autorentals;
import info.ejava.assignments.db.autorenters.svc.rentals.RentalBO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name ="RENTALS_AUTORENTAL")
@NamedQuery(name="AutoRentalBO.findByDatesBetween", query="select r from AutoRentalBO r where startDate >= :startDate and endDate <= :endDate order by id ASC")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="rentals")

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
    @org.springframework.data.annotation.Id
    private String id;
    private String autoId;
    private String renterId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal amount;
    private String makeModel;
    private String renterName;
    private Integer renterAge;
    @Column(name="ADDRESS_STREET")
    private String street;
    @Column(name="ADDRESS_CITY")
    private String city;
    @Column(name="ADDRESS_STATE")
    private String state;
    @Column(name="ADDRESS_ZIP")
    private String zip;
    private String username;

}
