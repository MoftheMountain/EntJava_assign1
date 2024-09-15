package info.ejava_student.assignment1.logging.autorentals.app;

import info.ejava_student.assignment1.logging.autorentals.svc.AutoRentalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.security.SecureRandom;

@RequiredArgsConstructor
public class AppCommand implements CommandLineRunner {
    private final AutoRentalsService service;
    private SecureRandom random = new SecureRandom();

    @Override
    public void run(String... args) throws Exception {
        String autoId = "2022-01";
        String renterId = Integer.valueOf(random.nextInt(1000)).toString();
        BigDecimal delta = service.calcDelta(autoId, renterId);
    }
}
