package info.ejava_student.maryc.assignment1.logging.autorentals.repo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;


import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class AutoRentalDTO {
    private String autoId;
    private String renterId;
    private BigDecimal amount;

    @Override
    @SneakyThrows
    public String toString() {
        String result = "AutoRental{" +
                "autoId='" + autoId + '\'' +
                ", renterId='" + renterId + '\'' +
                ", amount=" + amount +
                '}';
        try { Thread.sleep(750); } catch (Exception ex) {/*ignored*/}
        return result;
    }
}
