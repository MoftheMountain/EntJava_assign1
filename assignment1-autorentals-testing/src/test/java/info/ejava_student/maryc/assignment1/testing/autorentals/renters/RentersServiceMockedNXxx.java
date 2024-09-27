package info.ejava_student.maryc.assignment1.testing.autorentals.renters;

import info.ejava.assignments.testing.rentals.renters.RenterDTO;
import info.ejava.assignments.testing.rentals.renters.RenterValidator;
import info.ejava.assignments.testing.rentals.renters.RentersProperties;
import info.ejava.assignments.testing.rentals.renters.RentersService;

public class RentersServiceMockedNXxx {
    private RentersService subject;
    private RentersProperties configProperties;
    private RenterDTO validRenter;
    private RenterValidator validatorMock;

    void can_create_valid_renter() {
        //given valid renter/validator conditions
        //when creating valid renter
        //then renter will be validated and assigned an ID
    }

    void rejects_invalid_renter() {
        //given renter/validator failure conditions
        //when attempting to create, will fail with exception
        //then exception will have validation information
    }
}
