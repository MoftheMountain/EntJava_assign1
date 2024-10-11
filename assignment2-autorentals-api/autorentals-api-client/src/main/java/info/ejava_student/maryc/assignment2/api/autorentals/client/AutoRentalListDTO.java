package info.ejava_student.maryc.assignment2.api.autorentals.client;

import lombok.Data;

import java.util.List;

@Data
public class AutoRentalListDTO {
    private List<AutoRentalDTO> contents;
}
