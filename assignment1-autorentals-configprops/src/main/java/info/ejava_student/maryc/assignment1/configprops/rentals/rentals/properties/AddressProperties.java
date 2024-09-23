package info.ejava_student.maryc.assignment1.configprops.rentals.rentals.properties;

import lombok.Value;

@Value
public class AddressProperties {
    //@NotBlank
    private final String city;
    private final String state;
}
