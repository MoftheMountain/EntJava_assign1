package info.ejava_student.maryc.assignment2.api.autorentals.client.client;

import info.ejava.assignments.api.autorenters.client.autos.AutosAPIClient;
import info.ejava.assignments.api.autorenters.dto.autos.AutoDTO;
import info.ejava.assignments.api.autorenters.dto.autos.AutoSearchParams;
import info.ejava.examples.common.web.ServerConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Setter
@Getter
public class AutoRentalsAPIClient implements AutoRentalsAPI {
    private URI baseUrl;
    private RestTemplate restTemplate;
    private MediaType mediaType;

    public AutoRentalsAPIClient(RestTemplate restTemplate, ServerConfig serverConfig, MediaType mediaType){
        this.baseUrl = serverConfig.getBaseUrl();
        this.restTemplate = restTemplate;
        this.mediaType = mediaType;
    }

    @Override
    public AutoRentalsAPIClient withRestTemplate(RestTemplate restTemplate) {
        ServerConfig serverConfig = new ServerConfig().withBaseUrl(baseUrl).build();
        return new AutoRentalsAPIClient(restTemplate, serverConfig, mediaType);
    }

    @Override
    public ResponseEntity<AutoRentalDTO> createAutoRental(AutoRentalDTO autoRental) {
        URI url = UriComponentsBuilder.fromUri(baseUrl).path(AUTORENTALS_PATH).build().toUri();

        RequestEntity<AutoRentalDTO> request = RequestEntity.post(url)
                .accept(mediaType)
                .contentType(mediaType)
                .body(autoRental);
        ResponseEntity<AutoRentalDTO> response = restTemplate.exchange(request, AutoRentalDTO.class);
        return response;
    }

    @Override
    public ResponseEntity<AutoRentalDTO> getAutoRental(String id) {
        return null;
    }
/*
    @Override
    public ResponseEntity<AutoRentalListDTO> queryAutoRentals(AutoRentalDTO probe, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<AutoRentalListDTO> searchAutoRentals(AutoSearchParams searchParams) {
        return null;
    }



    @Override
    public ResponseEntity<AutoRentalDTO> getAutoRentalByAutoID(String id) {
        return null;
    }

    @Override
    public ResponseEntity<AutoRentalDTO> getAutoRentalByRenterID(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> existsAutoRental(String id) {
        return null;
    }

    @Override
    public ResponseEntity<AutoRentalDTO> updateAutoRental(String id, AutoDTO auto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> removeAutoRental(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> removeAllAutoRentals() {
        return null;
    }*/
}
