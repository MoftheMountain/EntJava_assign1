package info.ejava_student.maryc.assignment5.jpa.autorentals;

import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;


public interface JpaAutoRentalsRepository extends JpaRepository<AutoRentalBO, String> {
    @Query("select r from AutoRentalBO r where startDate <= :endDate and endDate >= :startDate")
    Slice<AutoRentalBO> byDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<AutoRentalBO> findByAutoId(String autoId, Pageable pageable);
}
