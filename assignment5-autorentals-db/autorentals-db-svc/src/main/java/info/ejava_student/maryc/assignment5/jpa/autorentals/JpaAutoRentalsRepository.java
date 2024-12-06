package info.ejava_student.maryc.assignment5.jpa.autorentals;

import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAutoRentalsRepository extends JpaRepository<AutoRentalBO, String> {
}
