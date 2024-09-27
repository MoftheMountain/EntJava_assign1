package info.ejava_student.maryc.assignment1.logging.autorentals.svc;

import info.ejava_student.maryc.assignment1.logging.autorentals.repo.AutoRentalDTO;
import java.math.BigDecimal;

import info.ejava_student.maryc.assignment1.logging.autorentals.repo.AutoRentalsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class AutoRentalsServiceImpl implements AutoRentalsService{
    private final AutoRentalsRepository repo;
    private final AutoRentalsHelper helper;
    public BigDecimal calcDelta(String autoId, String renterId){
        AutoRentalDTO leader=repo.getLeaderByAutoId(autoId);
        log.info("leader values are: autoID: {},renterID: {}, amt:{}",leader.getAutoId(), leader.getRenterId(), leader.getAmount());
        AutoRentalDTO target=repo.getByRenterId(renterId);
        log.info("target values are: autoID: {},renterID: {}, amt:{}",target.getAutoId(), target.getRenterId(), target.getAmount());
        log.debug("leader - target = {} - {} = {}",leader.getAmount(),target.getAmount(),leader.getAmount().subtract(target.getAmount()));

        return helper.calcDelta(leader,target);
        }

}