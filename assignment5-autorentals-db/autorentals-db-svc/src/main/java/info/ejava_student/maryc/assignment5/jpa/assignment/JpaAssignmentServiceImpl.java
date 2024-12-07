package info.ejava_student.maryc.assignment5.jpa.assignment;

import info.ejava.assignments.db.autorenters.rentals.JpaAssignmentService;
import info.ejava.assignments.db.autorenters.svc.rentals.RentalsMapper;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalBO;
import info.ejava_student.maryc.assignment5.db.autorentals.AutoRentalMapper;
import info.ejava_student.maryc.assignment5.jpa.autorentals.JpaAutoRentalsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDate;
import java.util.List;

@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class JpaAssignmentServiceImpl implements JpaAssignmentService<AutoRentalDTO, AutoRentalBO> {

    private RentalsMapper<AutoRentalDTO,AutoRentalBO> mapper;
    private EntityManager entityManager;
    private JpaAutoRentalsRepository repo;
    /**
     * Map the DTO to a BO and persist.
     * @param rentalDTO
     * @return DTO mapped from the persisted BO with its primary key
     */
    @Override
    public AutoRentalDTO mapAndPersist(AutoRentalDTO rentalDTO) {
        AutoRentalBO rentalBO = mapper.map(rentalDTO);
        entityManager.persist(rentalBO);
        return mapper.map(rentalBO);
    }

    /**
     * Implement a JPQL named query.
     * @param startInclusive
     * @param endInclusive
     * @return List of found entries
     */
    @Override
    public List<AutoRentalDTO> queryByRentalDateRange(LocalDate startInclusive, LocalDate endInclusive) {
        TypedQuery<AutoRentalBO> query = entityManager
                .createNamedQuery("AutoRentalBO.findByDatesBetween", AutoRentalBO.class)
                .setParameter("startDate", startInclusive)
                .setParameter("endDate",endInclusive);

        return query.getResultList().stream()
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
