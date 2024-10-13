package info.ejava_student.maryc.assignment2.api.autorentals;

import info.ejava.assignments.api.autorenters.dto.autos.AutoDTO;
import info.ejava.assignments.api.autorenters.dto.rentals.TimePeriod;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AutoRentalsDTORepositoryImpl implements AutoRentalsDTORepository{

    private final Map<String, AutoRentalDTO> autoRentals = new ConcurrentHashMap<>();
    private final AtomicInteger ID = new AtomicInteger(new SecureRandom().nextInt(1000));

    @Override
    public AutoRentalDTO save(AutoRentalDTO autoRental) {
        if (autoRental!=null){
            String id = autoRental.getId()!=null ?
                    autoRental.getId() :
                    "rental-" +Integer.valueOf(ID.incrementAndGet()).toString();
            autoRental.setId(id);
            autoRentals.put(autoRental.getId(), autoRental);
        }
        return autoRental;
    }

    public Optional<AutoRentalDTO> getAutoRental(String id){
        AutoRentalDTO autoRental = autoRentals.get(id);
        return autoRental!=null ? Optional.of(autoRental) : Optional.empty();
    }

    public boolean hasById(String id){
        return id!=null ? autoRentals.containsKey(id) : false ;
    }
    @Override
    public void deleteById(String id) {
        if (id!=null) {
            autoRentals.remove(id);
        }
    }

    @Override
    public void deleteAll() {
        autoRentals.clear();
    }

    protected Page<AutoRentalDTO> find(Predicate<AutoRentalDTO> predicate, Pageable pageable) {
        List<AutoRentalDTO> foundObjects = pageable.isUnpaged() ?
                autoRentals.values().stream()
                        .filter(predicate)
                        .toList() :
                autoRentals.values().stream()
                        .filter(predicate)
                        //TODO: sort
                        .sorted((l,r)->StringUtils.compare(l.getId(), r.getId()))
                        .skip(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .toList();
        long total = pageable.isUnpaged() ?
                autoRentals.size() :
                autoRentals.values().stream().filter(predicate).count();

        return new PageImpl<>(foundObjects, pageable, total);
    }

    @Override
    public Page<AutoRentalDTO> findAll(Pageable pageable) {
        Predicate<AutoRentalDTO> matchAll = candidate -> true;
        return find(matchAll, pageable);
    }

    @Override
    public Page<AutoRentalDTO> findAll(Example<AutoRentalDTO> example, Pageable pageable) {
        AutoRentalDTO probe = example.getProbe();
        List<Predicate<AutoRentalDTO>> predicates = new ArrayList<>();
        if (probe.getId()!=null) {
            predicates.add(candidate-> Objects.equals(probe.getId(), candidate.getId()));
        }
        if (probe.getAutoID()!=null) {
            predicates.add(candidate-> Objects.equals(probe.getAutoID(), candidate.getAutoID()));
        }
        if (probe.getRenterID()!=null) {
            predicates.add(candidate-> Objects.equals(probe.getRenterID(), candidate.getRenterID()));
        }
        if (probe.getStartDate()!=null) {
            predicates.add(candidate-> Objects.equals(probe.getStartDate(), candidate.getStartDate()));
        }
        if (probe.getEndDate()!=null) {
            predicates.add(candidate-> Objects.equals(probe.getEndDate(), candidate.getEndDate()));
        }

        Predicate<AutoRentalDTO> query = predicates.stream().reduce(Predicate::and).orElse(x->true);
        return find(query, pageable);
    }

    @Override
    public Page<AutoRentalDTO> findConflicts(String autoId, String renterId, TimePeriod timePeriod, Pageable pageable) {
        AutoRentalDTO probe = AutoRentalDTO.builder()
                .autoID(autoId)
                .renterID(renterId)
                .startDate(timePeriod.getStartDate())
                .endDate(timePeriod.getEndDate())
                .build();
        List<Predicate<AutoRentalDTO>> predicates = new ArrayList<>();
        predicates.add(candidate -> StringUtils.equals(probe.getAutoID(),candidate.getAutoID()));
        predicates.add(candidate-> !StringUtils.equals(probe.getRenterID(),candidate.getRenterID()));
        predicates.add(candidate-> probe.makeTimePeriod().isOverlap(candidate.makeTimePeriod()));

        Predicate<AutoRentalDTO> query = predicates.stream().reduce(Predicate::and).orElse(x->true);
        return find(query, pageable);
    }

}
