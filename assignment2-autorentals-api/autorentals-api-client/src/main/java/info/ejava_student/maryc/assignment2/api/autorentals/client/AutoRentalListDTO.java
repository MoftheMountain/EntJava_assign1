package info.ejava_student.maryc.assignment2.api.autorentals.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoRentalListDTO {
    private List<AutoRentalDTO> contents = new ArrayList<>();
}
