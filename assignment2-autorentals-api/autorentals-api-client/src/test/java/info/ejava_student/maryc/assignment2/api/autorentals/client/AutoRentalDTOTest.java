package info.ejava_student.maryc.assignment2.api.autorentals.client;

import info.ejava.assignments.api.autorenters.dto.autos.AutoDTOFactory;
import info.ejava.assignments.api.autorenters.dto.rentals.TimePeriod;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTOFactory;
import info.ejava.examples.common.dto.DtoUtil;
import info.ejava.examples.common.dto.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutoRentalDTOTest {
    private AutoDTOFactory autoFactory = new AutoDTOFactory();
    private RenterDTOFactory renterDTOFactory = new RenterDTOFactory();
    private DtoUtil dtoUtil = new JsonUtil();

    private Stream<Arguments> autoRentals() {
        return Stream.of(
                Arguments.of(new AutoRentalDTO(
                        autoFactory.make(AutoDTOFactory.withId),
                        renterDTOFactory.make(RenterDTOFactory.withId),
                        new TimePeriod(LocalDate.now(), 7))),
                Arguments.of(new AutoRentalListDTO())
        );
    }

    @ParameterizedTest
    @MethodSource("autoRentals")
    void can_marshal_demarshal(/*given*/Object original) {
        //when
        String payload = dtoUtil.marshal(original);
        log.info("{}", payload);
        Object copy = dtoUtil.unmarshal(payload, original.getClass());
        //then
        then(copy).isEqualTo(original);
    }
}
