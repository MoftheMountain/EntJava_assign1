package info.ejava_student.maryc.assignment5.mongo.autorentals;

import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;

public interface MongoAutoRentalsRepository extends MongoRepository<AutoRentalBO, String> {
    @Query("{ 'startDate':{$lte:?1}, 'endDate':{$gte:?0} }")
    Slice<AutoRentalBO> byDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<AutoRentalBO> findByAutoId(String autoId, Pageable pageable);
}
