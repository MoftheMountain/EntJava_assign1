package info.ejava_student.maryc.assignment5.db.autorentals;


import info.ejava.assignments.api.autorenters.dto.StreetAddressDTO;
import info.ejava.assignments.db.autorenters.svc.rentals.RentalsMapper;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

//NOTE: the structure of the DTO and BO do not have to match. That is one
// purpose of having the two data models. A flat BO can be persisted into a
// flat DB table and the DTO object tree can be marshalled to the client
// using a hierarchical JSON document.
public class AutoRentalMapper implements RentalsMapper<AutoRentalDTO, AutoRentalBO> {
    public AutoRentalBO map(AutoRentalDTO dto) {
        AutoRentalBO bo = null;
        if (dto!=null) {
            bo=AutoRentalBO.builder()
                    .id(dto.getId())
                    .autoId(dto.getAutoID())
                    .renterId(dto.getRenterID())
                    .startDate(dto.getStartDate())
                    .endDate(dto.getEndDate())
                    .amount(dto.getAmount())
                    .makeModel(dto.getMakeModel())
                    .renterName(dto.getRenterName())
                    .renterAge(dto.getRenterAge())
                    .street(dto.getStreetAddress().getStreet())
                    .city(dto.getStreetAddress().getCity())
                    .state(dto.getStreetAddress().getState())
                    .zip(dto.getStreetAddress().getZip())
                    .username(dto.getUsername())
                    .build();
        }
        return bo;
    }

    public AutoRentalDTO map(AutoRentalBO bo) {
        AutoRentalDTO dto = null;
        if (bo!=null) {

            dto=AutoRentalDTO.builder()
                    .id(dto.getId())
                    .autoID(bo.getAutoId())
                    .renterID(bo.getRenterId())
                    .startDate(bo.getStartDate())
                    .endDate(bo.getEndDate())
                    .amount(bo.getAmount())
                    .makeModel(bo.getMakeModel())
                    .renterName(bo.getRenterName())
                    .renterAge(bo.getRenterAge())
                    .streetAddress(new StreetAddressDTO(bo.getStreet(),bo.getCity(),bo.getState(),bo.getZip()))
                    .username(bo.getUsername())
                    .build();
        }
        return dto;
    }
}
