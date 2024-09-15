package info.ejava_student.assignment1.logging.autorentals;

import info.ejava_student.assignment1.logging.autorentals.app.AppCommand;
import info.ejava_student.assignment1.logging.autorentals.repo.AutoRentalsRepository;
import info.ejava_student.assignment1.logging.autorentals.svc.AutoRentalsHelper;
import info.ejava_student.assignment1.logging.autorentals.svc.AutoRentalsService;

public class RentalsConfig {
    public AutoRentalsRepository repo() {
        return null;
    }
    public AutoRentalsHelper helper() {
        return null;
    }
    public AutoRentalsService service(AutoRentalsRepository repo, AutoRentalsHelper helper) {
        return null;
    }
    public AppCommand appCommand(AutoRentalsService service) {
        return null;
    }
}
