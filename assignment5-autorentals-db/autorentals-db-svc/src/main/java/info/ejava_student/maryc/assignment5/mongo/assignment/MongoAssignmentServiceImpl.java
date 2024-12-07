package info.ejava_student.maryc.assignment5.mongo.assignment;

import info.ejava.assignments.db.autorenters.rentals.MongoAssignmentService;
import info.ejava.assignments.db.autorenters.svc.rentals.RentalsMapper;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalBO;
import info.ejava_student.maryc.assignment5.mongo.autorentals.MongoAutoRentalsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class MongoAssignmentServiceImpl implements MongoAssignmentService<AutoRentalDTO, AutoRentalBO> {

    private RentalsMapper<AutoRentalDTO,AutoRentalBO> mapper;
    private MongoOperations mongoOps;
    private MongoAutoRentalsRepository repo;

    /**
     * Map the DTO to a BO and persist.
     * @param rentalDTO
     * @return DTO mapped from the persisted BO with its primary key
     */
    @Override
    public AutoRentalDTO mapAndPersist(AutoRentalDTO rentalDTO) {
        AutoRentalBO rentalBO = mapper.map(rentalDTO);
        mongoOps.save(rentalBO);
        return mapper.map(rentalBO);
    }

    /**
     * Implement a MongoTemplate query.
     * @param startInclusive
     * @param endInclusive
     * @return List of found entries
     */
    @Override
    public List<AutoRentalDTO> queryByRentalDateRange(LocalDate startInclusive, LocalDate endInclusive) {
        Query filter =Query.query(new Criteria().andOperator(
                Criteria.where("StartDate").gte(startInclusive),
                Criteria.where("endDate").lte(endInclusive)
        ));

        return mongoOps.find(filter,AutoRentalBO.class).stream()
                .map(bo->mapper.map(bo)).toList();
    }

    /**
     * Implement a derived query using special method keywords to trigger the correct query.
     * @param autoId search only by this ID
     * @param pageable
     * @return page with found entries
     */
    @Override
    public Page<AutoRentalBO> findByAutoIdByDerivedQuery(String autoId, Pageable pageable) {
        return repo.findByAutoId(autoId,pageable);
    }

    /**
     * Implement a query by example using the probe provided as an input parameter.
     * @param probe match provide fields
     * @param pageable
     * @return page with found entries
     */
    @Override
    public Page<AutoRentalBO> findByExample(AutoRentalBO probe, Pageable pageable) {
        return repo.findAll(Example.of(probe),pageable);
    }

    /**
     * Implement a query that uses @Query annotation on the repository interface method.
     * @param startDate inclusive
     * @param endDate inclusive
     * @param pageable
     * @return slice with found entries
     */
    @Override
    public Slice<AutoRentalBO> findByDateRangeByAnnotatedQuery(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return repo.byDateRange(startDate,endDate,pageable);
    }
}
