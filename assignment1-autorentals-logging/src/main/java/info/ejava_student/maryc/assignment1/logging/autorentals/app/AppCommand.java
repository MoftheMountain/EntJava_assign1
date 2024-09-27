package info.ejava_student.maryc.assignment1.logging.autorentals.app;

import info.ejava_student.maryc.assignment1.logging.autorentals.svc.AutoRentalsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class AppCommand implements CommandLineRunner {
    private final AutoRentalsService service;
    private SecureRandom random = new SecureRandom();
    private static final Logger log = LoggerFactory.getLogger("X.Y");
    private static final String requestId = UUID.randomUUID().toString();

    @Override
    public void run(String... args) throws Exception {
        try {
            MDC.put("requestId", requestId);
            log.info("AutoRentals has started");
            String autoId = "2022-01";
            String renterId = Integer.valueOf(random.nextInt(1000)).toString();
            BigDecimal delta = service.calcDelta(autoId, renterId);
        }finally{
            MDC.clear();
        }
    }
}
