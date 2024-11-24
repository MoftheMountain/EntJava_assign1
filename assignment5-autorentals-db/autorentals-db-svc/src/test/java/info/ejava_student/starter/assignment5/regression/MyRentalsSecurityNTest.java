package info.ejava_student.starter.assignment5.regression;

import info.ejava.assignments.api.autorenters.svc.ProvidedApiAutoRenterTestConfiguration;
import info.ejava.assignments.security.autorenters.svc.ProvidedAuthorizationTestHelperConfiguration;
import info.ejava.assignments.security.autorenters.svc.rentals.B2_AuthorizationNTest;
import info.ejava_student.starter.assignment2.api.autorentals.impl.ApiImplNTestConfiguration;
import info.ejava_student.starter.assignment5.db.AutoRentalsDbApp;
import info.ejava_student.starter.assignment5.db.rentals.TestProfileResolver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes={AutoRentalsDbApp.class,
        //Support authentication/authority helpers
        //        public Accounts rentalAccounts() {
        //        public AccountProperties anAccount(Accounts accounts) {
        //        public AccountProperties altAccount(Accounts accounts) {
        //        public AccountProperties mgrAccount(Accounts accounts) {
        //        public AccountProperties proxyAccount(Accounts accounts) {
        //        public AccountProperties adminAccount(Accounts accounts) {
        //        public ClientHttpRequestFactory requestFactory() {
        //        public Map<String, RestTemplate> authnUsers(RestTemplateBuilder builder,
        //        public RestTemplate authnUser(@Qualifier("usernameMap") Map<String, RestTemplate> authnUsers, AccountProperties anAccount) {
        //        public RestTemplate altUser(@Qualifier("usernameMap") Map<String, RestTemplate> authnUsers, AccountProperties altAccount) {
        //        public RestTemplate adminUser(@Qualifier("usernameMap") Map<String, RestTemplate> authnUsers, AccountProperties adminAccount) {
        //        public RestTemplate mgrUser(@Qualifier("usernameMap") Map<String, RestTemplate> authnUsers, AccountProperties mgrAccount) {
        //        public RestTemplate proxyUser(@Qualifier("usernameMap") Map<String, RestTemplate> authnUsers, AccountProperties proxyAccount) {
        ProvidedAuthorizationTestHelperConfiguration.class,

        //My autorentals API client resources
        //    public ApiTestHelper testHelper(AutoRentalDTOFactory autoRentalDTOFactory, @Lazy AutosRentalsAPIClient autosRentalsAPIClient) {
        //    public AutoRentalDTOFactory autoRentalFactory() {
        //    public AutosRentalsAPIClient autoRentalsAPI(RestTemplate restTemplate, ServerConfig serverConfig) {
        //Security configuration provided with many authenticated/authorized beans
        ApiImplNTestConfiguration.class,

        //Support auto/renters API client resources
        //    public AutoDTOFactory autoFactory() {
        //    public RenterDTOFactory renterFactory() {
        //    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        //    public ServerConfig serverConfig(@LocalServerPort int port) {
        //    public AutosAPI autosAPI(RestTemplate restTemplate, ServerConfig serverConfig) {
        //    public RentersAPI rentersAPI(RestTemplate restTemplate, ServerConfig serverConfig) {
        ProvidedApiAutoRenterTestConfiguration.class,
},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@ActiveProfiles(profiles={"authorities","authorization","test"}, resolver = TestProfileResolver.class)
//@ActiveProfiles(profiles={"test", "mongodb"})
@Disabled
public class MyRentalsSecurityNTest extends B2_AuthorizationNTest {
}
