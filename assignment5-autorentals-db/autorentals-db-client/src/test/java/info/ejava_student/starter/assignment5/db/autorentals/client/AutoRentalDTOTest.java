package info.ejava_student.maryc.assignment5.db.autorentals.client;

import info.ejava.examples.common.dto.DtoUtil;
import info.ejava.examples.common.dto.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class AutoRentalDTOTest {
    private final DtoUtil dtoUtil = new JsonUtil();
    private Stream<Arguments> rentals() {
        return Stream.of(
                Arguments.of(new AutoRentalPageDTO())
        );
    }

    @ParameterizedTest
    @MethodSource("rentals")
    void can_marshal_unmarshal(/*given*/Object original) {
        //when
        String payload = dtoUtil.marshal(original);
        log.info("{}", payload);
        Object copy = dtoUtil.unmarshal(payload, original.getClass());
        //then
        then(copy).isEqualTo(original);
    }
}
