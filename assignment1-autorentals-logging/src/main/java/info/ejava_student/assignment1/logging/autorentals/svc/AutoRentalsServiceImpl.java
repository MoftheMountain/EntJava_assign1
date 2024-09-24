package info.ejava_student.assignment1.logging.autorentals.svc;

import info.ejava_student.assignment1.logging.autorentals.repo.AutoRentalDTO;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class AutoRentalsServiceImpl implements AutoRentalsService{
    public BigDecimal calcDelta(String autoId, String renterId){
        AutoRentalDTO leader=new AutoRentalDTO(autoId,"RenterID",new BigDecimal(456798));
        AutoRentalDTO target=new AutoRentalDTO("AutoID",renterId,new BigDecimal(90987631));

        return leader.getAmount().subtract(target.getAmount());
        }

}