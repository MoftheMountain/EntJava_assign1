package info.ejava_student.maryc.assignment2.api.autorentals.impl;

import info.ejava.assignments.api.autorenters.dto.StreetAddressDTO;
import info.ejava.assignments.api.autorenters.dto.autos.AutoDTO;
import info.ejava.assignments.api.autorenters.dto.rentals.SearchParams;
import info.ejava.assignments.api.autorenters.dto.rentals.TimePeriod;
import info.ejava.assignments.api.autorenters.dto.renters.RenterDTO;
import info.ejava.assignments.api.autorenters.svc.rentals.ApiTestHelper;
import info.ejava.examples.common.web.ServerConfig;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalDTO;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalListDTO;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalsAPI;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalsAPIClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

//TODO: implement this component for MyAutoRentalsAPINTest
/**
 * This class maps the RentalDTO marker factory and interface calls as well
 * as server API calls to solution-specific methods.
 *
 * "make" methods are client-side POJO factories.
 * "getter/setter" methods translate between requests and your concrete rental DTO class.
 * server-side calls return ResponseEntity except the finder(s)
 * "finder(s)" simply return the List of DTOs
 */
public class ApiTestHelperImpl implements ApiTestHelper<AutoRentalDTO> {
private URI baseUrl;
private RestTemplate restTemplate;
private MediaType mediaType;
private AutoRentalsAPI autoRentalsAPI;
    public ApiTestHelperImpl(){
    }
    public ApiTestHelperImpl(RestTemplate restTemplate, ServerConfig serverConfig){
        this.baseUrl = serverConfig.getBaseUrl();
        this.restTemplate = restTemplate;
        this.mediaType = MediaType.APPLICATION_JSON;
        this.autoRentalsAPI = new AutoRentalsAPIClient(restTemplate, new ServerConfig().withBaseUrl(baseUrl).build(),mediaType);
    }
    //you may need a reusable mechanism to construct DTO instances

    @Override
    public ApiTestHelper<AutoRentalDTO> withRestTemplate(RestTemplate restTemplate) {
        ServerConfig serverConfig = new ServerConfig().withBaseUrl(baseUrl).build();
        return new ApiTestHelperImpl(restTemplate, serverConfig); //new instance of this helper, with clients using provided restTemplate
    }

    @Override
    public AutoRentalDTO makeProposal(AutoDTO auto, RenterDTO renter, TimePeriod timePeriod) {
        return AutoRentalDTO.builder()
                .autoID(auto.getId())
                .renterID(renter.getId())
                .startDate(timePeriod.getStartDate())
                .endDate(timePeriod.getEndDate())
                .build();
    }
    public AutoRentalDTO makePopulatedFake() {
        StreetAddressDTO streetAddress=StreetAddressDTO.builder()
                .street("123 MyStreet")
                .city("Anytown")
                .state("CA")
                .zip("90210")
                .build();

        return AutoRentalDTO.builder()
                .makeModel("Mercury Mystique")
                .renterName("Joe Schmo")
                .amount(new BigDecimal(123456))
                .streetAddress(streetAddress)
                .build();
    }

    @Override
    public String getRentalId(AutoRentalDTO autoRental) {
        return autoRental.getId();
    }

    @Override
    public void setRentalId(AutoRentalDTO autoRental, String rentalId) {
        autoRental.setId(rentalId);
    }

    @Override
    public String getAutoId(AutoRentalDTO autoRental) {
        return autoRental.getAutoID();
    }

    @Override
    public void setAutoId(AutoRentalDTO autoRental, String autoId) {
        autoRental.setAutoID(autoId);
    }

    @Override
    public String getRenterId(AutoRentalDTO autoRental) {
        return autoRental.getRenterID();
    }

    @Override
    public void setRenterId(AutoRentalDTO autoRental, String renterId) {
        autoRental.setRenterID(renterId);
    }

    @Override
    public LocalDate getStartDate(AutoRentalDTO autoRental) {
        return autoRental.getStartDate();
    }
    @Override
    public void setStartDate(AutoRentalDTO autoRental, LocalDate startDate) {
        autoRental.setStartDate(startDate);
    }

    @Override
    public LocalDate getEndDate(AutoRentalDTO autoRental) {
        return autoRental.getEndDate();
    }
    @Override
    public void setEndDateDate(AutoRentalDTO autoRental, LocalDate endDate) {
        autoRental.setEndDate(endDate);
    }

    @Override
    public String getAutoMakeModel(AutoRentalDTO autoRental) {
        return autoRental.getMakeModel();
    }

    @Override
    public String getRenterName(AutoRentalDTO autoRental) {
        return autoRental.getRenterName();
    }

    @Override
    public BigDecimal getAmount(AutoRentalDTO autoRental) {
        return autoRental.getAmount();
    }

    @Override
    public int getRenterAge(AutoRentalDTO autoRental) {
        return autoRental.getRenterAge();
    }

    @Override
    public StreetAddressDTO getStreetAddress(AutoRentalDTO autoRental) {
        return autoRental.getStreetAddress();
    }

    @Override
    public TimePeriod getTimePeriod(AutoRentalDTO autoRental) {
        return ApiTestHelper.super.getTimePeriod(autoRental);
    }

    ////////// calls to server-side API


    @Override
    public ResponseEntity<AutoRentalDTO> createContract(AutoRentalDTO proposedRental) {
        ResponseEntity<AutoRentalDTO> savedRental = autoRentalsAPI.createAutoRental(proposedRental);
        return savedRental;
    }

    @Override
    public ResponseEntity<AutoRentalDTO> modifyContract(AutoRentalDTO proposedRental) {
        ResponseEntity<AutoRentalDTO> updatedRental = autoRentalsAPI.updateAutoRental(proposedRental.getId(),proposedRental);
        return updatedRental;
    }

    @Override
    public ResponseEntity<AutoRentalDTO> getRental(AutoRentalDTO rentalContract) {
        ResponseEntity<AutoRentalDTO>foundRental = autoRentalsAPI.getAutoRental(rentalContract.getId());
        return foundRental;

    }

    @Override
    public ResponseEntity<AutoRentalDTO> getRentalById(String rentalId) {
        ResponseEntity<AutoRentalDTO>foundRental = autoRentalsAPI.getAutoRental(rentalId);
        return foundRental;

    }

    @Override
    public List<AutoRentalDTO> findRentalsBy(SearchParams searchParams) {
        AutoRentalDTO probe = AutoRentalDTO.builder()
                .autoID(searchParams.getAutoId())
                .renterID(searchParams.getRenterId())
                .startDate(searchParams.getStartDate())
                .endDate(searchParams.getEndDate())
                .build();
        ResponseEntity<AutoRentalListDTO> response = autoRentalsAPI.queryAutoRentals(probe, searchParams.getPageNumber(), searchParams.getPageSize());
        return response.getBody().getContents();
    }

    @Override
    public ResponseEntity<Void> removeRental(String rentalId) {
        ResponseEntity<Void> response = autoRentalsAPI.removeAutoRental(rentalId);
        return response;
    }

    @Override
    public ResponseEntity<Void> removeAllRentals() {
        ResponseEntity<Void> response =  autoRentalsAPI.removeAllAutoRentals();
        return response;
    }
}
