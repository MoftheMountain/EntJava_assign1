package info.ejava_student.maryc.assignment1.logging.autorentals.repo;

public interface AutoRentalsRepository {
    AutoRentalDTO getLeaderByAutoId(String autoId);
    AutoRentalDTO getByRenterId(String renterId);
}
