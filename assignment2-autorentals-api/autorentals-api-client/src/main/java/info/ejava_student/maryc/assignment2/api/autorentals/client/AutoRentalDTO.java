package info.ejava_student.maryc.assignment2.api.autorentals.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import info.ejava.assignments.api.autorenters.dto.StreetAddressDTO;
import info.ejava.assignments.api.autorenters.dto.autos.AutoDTO;
import info.ejava.assignments.api.autorenters.dto.rentals.RentalDTO;
import info.ejava.assignments.api.autorenters.dto.rentals.TimePeriod;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutoRentalDTO implements RentalDTO {
    private String id;
    private String autoID;
    private String renterID;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal amount;
    String makeModel;//make and model separated by space
    String renterName;//First and Last separated by space
    Integer renterAge; //in Days
    StreetAddressDTO streetAddress;
    @JsonIgnore
    String username;

    public AutoRentalDTO(AutoDTO auto, RenterDTO renter, TimePeriod timePeriod) {
        setAuto(auto);
        setRenter(renter);
        setTimePeriod(timePeriod);
    }

    public void setAuto(AutoDTO auto) {
        this.autoID = auto.getId();
        this.makeModel = auto.getMake() + " " + auto.getModel();
    }

    public void setRenter(RenterDTO renter) {
        this.renterID = renter.getId();

        TimePeriod tp = new TimePeriod(renter.getDob(),LocalDate.now());
        this.renterAge = tp.getPeriod().getYears();

    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.startDate = timePeriod.getStartDate();
        this.endDate = timePeriod.getEndDate();
    }
    public TimePeriod makeTimePeriod(){
        if (this.startDate == null & this.endDate == null){
            return null;
        }else {
            return new TimePeriod(this.startDate, this.endDate);
        }
    }
}
