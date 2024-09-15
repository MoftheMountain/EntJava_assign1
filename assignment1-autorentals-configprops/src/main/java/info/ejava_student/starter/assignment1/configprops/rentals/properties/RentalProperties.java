package info.ejava_student.starter.assignment1.configprops.rentals.properties;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class RentalProperties {
    private int id;
    private LocalDate rentalDate;
    private BigDecimal rentalAmount;
    private String renterName;
    private AddressProperties location;
}
