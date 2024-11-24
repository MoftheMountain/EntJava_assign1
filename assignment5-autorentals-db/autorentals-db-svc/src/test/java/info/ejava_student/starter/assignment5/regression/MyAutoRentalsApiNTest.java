package info.ejava_student.starter.assignment5.regression;

import info.ejava.assignments.api.autorenters.svc.ProvidedApiAutoRenterTestConfiguration;
import info.ejava.assignments.api.autorenters.svc.rentals.AutoRentalsApiNTest;
import info.ejava_student.starter.assignment2.api.autorentals.impl.ApiImplNTestConfiguration;
import info.ejava_student.starter.assignment5.db.AutoRentalsDbApp;
import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes={AutoRentalsDbApp.class,
        //Support auto/renters API client resources
        //    public AutoDTOFactory autoFactory() {
        //    public RenterDTOFactory renterFactory() {
        //    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        //    public ServerConfig serverConfig(@LocalServerPort int port) {
        //    public AutosAPI autosAPI(RestTemplate restTemplate, ServerConfig serverConfig) {
        //    public RentersAPI rentersAPI(RestTemplate restTemplate, ServerConfig serverConfig) {
        ProvidedApiAutoRenterTestConfiguration.class,

        //My autorentals API client resources
        //    public ApiTestHelper testHelper(AutoRentalDTOFactory autoRentalDTOFactory, @Lazy AutosRentalsAPIClient autosRentalsAPIClient) {
        //    public AutoRentalDTOFactory autoRentalFactory() {
        //    public AutosRentalsAPIClient autoRentalsAPI(RestTemplate restTemplate, ServerConfig serverConfig) {
        ApiImplNTestConfiguration.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles({"nosecurity","test"})
@Disabled
public class MyAutoRentalsApiNTest extends AutoRentalsApiNTest {
}
