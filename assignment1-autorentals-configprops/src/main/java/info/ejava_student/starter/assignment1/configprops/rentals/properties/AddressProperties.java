package info.ejava_student.starter.assignment1.configprops.rentals.properties;

import lombok.Value;

@Value
public class AddressProperties {
    //@NotBlank
    private final String city;
    private final String state;
}
