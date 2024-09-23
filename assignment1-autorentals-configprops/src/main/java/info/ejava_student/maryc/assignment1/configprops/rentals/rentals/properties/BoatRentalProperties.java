package info.ejava_student.maryc.assignment1.configprops.rentals.rentals.properties;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;
import java.time.LocalDate;

@ConfigurationProperties(prefix = "boatrental")
@Value
public class BoatRentalProperties {
    private int id;
    private LocalDate rentalDate;
    private BigDecimal rentalAmount;
    private String renterName;
    private AddressProperties location;
}
