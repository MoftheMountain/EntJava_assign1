package info.ejava_student.starter.assignment1.configprops.rentals;

import info.ejava_student.starter.assignment1.configprops.rentals.properties.BoatRentalProperties;
import info.ejava_student.starter.assignment1.configprops.rentals.properties.RentalProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import java.util.List;
import java.util.stream.Collectors;

//@Component
@Getter
@RequiredArgsConstructor
public class PropertyPrinter implements CommandLineRunner {
    private final List<RentalProperties> autos;
    private final List<RentalProperties> tools;
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