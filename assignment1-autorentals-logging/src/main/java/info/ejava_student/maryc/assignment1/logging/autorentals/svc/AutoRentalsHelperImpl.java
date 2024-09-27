package info.ejava_student.maryc.assignment1.logging.autorentals.svc;

import info.ejava_student.maryc.assignment1.logging.autorentals.repo.AutoRentalDTO;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class AutoRentalsHelperImpl implements AutoRentalsHelper{
        public BigDecimal calcDelta(AutoRentalDTO leader, AutoRentalDTO targetResult){
            log.info("leader values are: autoID: {},renterID: {}, amt:{}",leader.getAutoId(), leader.getRenterId(), leader.getAmount());
            log.debug("leader amount: {}, target amount: {}",leader.getAmount(),targetResult.getAmount());
            return leader.getAmount().subtract(targetResult.getAmount());
        }

}