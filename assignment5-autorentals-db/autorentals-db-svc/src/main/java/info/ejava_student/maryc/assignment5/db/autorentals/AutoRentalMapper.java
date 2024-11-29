package info.ejava_student.maryc.assignment5.db.autorentals;


import info.ejava.assignments.db.autorenters.svc.rentals.RentalsMapper;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;

//NOTE: the structure of the DTO and BO do not have to match. That is one
// purpose of having the two data models. A flat BO can be persisted into a
// flat DB table and the DTO object tree can be marshalled to the client
// using a hierarchical JSON document.
public class AutoRentalMapper implements RentalsMapper<AutoRentalDTO, AutoRentalBO> {
    public AutoRentalBO map(AutoRentalDTO dto) {
        AutoRentalBO bo = null;
        if (dto!=null) {
            //...
        }
        return bo;
    }

    public AutoRentalDTO map(AutoRentalBO bo) {
        AutoRentalDTO dto = null;
        if (bo!=null) {
            //...
        }
        return dto;
    }

}
