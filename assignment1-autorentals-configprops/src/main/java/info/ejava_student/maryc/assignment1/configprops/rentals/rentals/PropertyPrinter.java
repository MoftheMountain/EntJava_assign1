package info.ejava_student.maryc.assignment1.configprops.rentals.rentals;

import info.ejava_student.maryc.assignment1.configprops.rentals.rentals.properties.RentalProperties;
import info.ejava_student.maryc.assignment1.configprops.rentals.rentals.properties.BoatRentalProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
@RequiredArgsConstructor
public class PropertyPrinter implements CommandLineRunner {
    @Autowired
    private final List<RentalProperties> autos;
    @Autowired
    private final List<RentalProperties> tools;
    @Autowired
    private final BoatRentalProperties boat;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("autos:" + format(autos));
        System.out.println("tools:" + format(tools));
        System.out.println("boat:" + format(null==boat ? null : List.of(boat)));
    }

    private String format(List<?> rentals) {
        return null==rentals ? "(null)" :
            String.format("%s", rentals.stream()
                .map(r->"*" + r.toString())
                .collect(Collectors.joining(System.lineSeparator(), System.lineSeparator(), "")));
    }
}