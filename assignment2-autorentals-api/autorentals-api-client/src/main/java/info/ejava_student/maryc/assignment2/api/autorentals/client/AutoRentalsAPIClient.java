package info.ejava_student.maryc.assignment2.api.autorentals.client;

import info.ejava.assignments.api.autorenters.dto.rentals.SearchParams;
import info.ejava.examples.common.web.ServerConfig;
import lombok.Getter;
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
        URI url = UriComponentsBuilder.fromUri(baseUrl).path(AUTORENTALS_PATH).build(autoRental.getId());//.toUri();

        RequestEntity<AutoRentalDTO> request = RequestEntity.post(url)
                .accept(mediaType)
                .contentType(mediaType)
                .body(autoRental);
        ResponseEntity<AutoRentalDTO> response = restTemplate.exchange(request, AutoRentalDTO.class);
        return response;
    }

    @Override
    public ResponseEntity<AutoRentalDTO> getAutoRental(String id) {
        URI url = UriComponentsBuilder.fromUri(baseUrl).path(AUTORENTAL_PATH).build(id);

        RequestEntity<Void> request = RequestEntity.get(url)
                .accept(mediaType)
                .build();
        ResponseEntity<AutoRentalDTO> response = restTemplate.exchange(request, AutoRentalDTO.class);
        return response;
    }

    @Override
    public ResponseEntity<Void> hasAutoRental(String id) {
        URI url = UriComponentsBuilder.fromUri(baseUrl).path(AUTORENTALS_PATH).build(id);

        RequestEntity<Void> request = RequestEntity.head(url).build();
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
        return response;
    }

    @Override
    public ResponseEntity<Void> removeAutoRental(String id) {
        URI url = UriComponentsBuilder.fromUri(baseUrl).path(AUTORENTAL_PATH).build(id);

        RequestEntity<Void> request = RequestEntity.delete(url).build();
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
        return response;
    }

    @Override
    public ResponseEntity<Void> removeAllAutoRentals() {
        URI url = UriComponentsBuilder.fromUri(baseUrl).path(AUTORENTALS_PATH).build().toUri();

        RequestEntity<Void> request = RequestEntity.delete(url).build();
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
        return response;
    }

    @Override
    public ResponseEntity<AutoRentalDTO> updateAutoRental(String id, AutoRentalDTO autoRental) {
        URI url = UriComponentsBuilder.fromUri(baseUrl).path(AUTORENTAL_PATH).build(id);

        RequestEntity<AutoRentalDTO> request = RequestEntity.put(url)
                .accept(mediaType)
                .contentType(mediaType)
                .body(autoRental);
        ResponseEntity<AutoRentalDTO> response = restTemplate.exchange(request, AutoRentalDTO.class);
        return response;
    }

    @Override
    public ResponseEntity<AutoRentalListDTO> queryAutoRentals(AutoRentalDTO probe, Integer pageNumber, Integer pageSize) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(baseUrl).path(AUTORENTAL_QUERY_PATH);
        if (probe.getAutoID()!=null) {
            uriBuilder = uriBuilder.queryParam("autoId", probe.getAutoID());
        }
        if (probe.getRenterID()!=null) {
            uriBuilder = uriBuilder.queryParam("renterId", probe.getRenterID());
        }
        if (probe.getEndDate()!=null) {
            uriBuilder = uriBuilder.queryParam("endDate", probe.getEndDate());
        }
        if (probe.getStartDate()!=null) {
            uriBuilder = uriBuilder.queryParam("startDate", probe.getStartDate());
        }
        if (null!=pageNumber && null!=pageSize) {
            uriBuilder = uriBuilder.queryParam("pageNumber", pageNumber);
            uriBuilder = uriBuilder.queryParam("pageSize", pageSize);
        }

        URI url = uriBuilder.build().toUri();

        RequestEntity<AutoRentalDTO> request = RequestEntity.post(url)
                .accept(mediaType)
                //.build();
                .body(probe);
        ResponseEntity<AutoRentalListDTO> response = restTemplate.exchange(request, AutoRentalListDTO.class);
        return response;
    }

    @Override
    public ResponseEntity<AutoRentalListDTO> searchAutoRentals(SearchParams searchParams) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(baseUrl).path(AUTORENTALS_PATH);
        if (searchParams.getAutoId()!=null) {
            uriBuilder = uriBuilder.queryParam("autoId", searchParams.getAutoId());
        }
        if (searchParams.getRenterId()!=null) {
            uriBuilder = uriBuilder.queryParam("renterId", searchParams.getRenterId());
        }
        if (searchParams.getEndDate()!=null) {
            uriBuilder = uriBuilder.queryParam("endDate", searchParams.getTimePeriod());
        }
        if (searchParams.getStartDate()!=null) {
            uriBuilder = uriBuilder.queryParam("startDate", searchParams.getStartDate());
        }
        if (null!=searchParams.getPageNumber() && null!=searchParams.getPageSize()) {
            uriBuilder = uriBuilder.queryParam("pageNumber", searchParams.getPageNumber());
            uriBuilder = uriBuilder.queryParam("pageSize", searchParams.getPageSize());
        }
        URI url = uriBuilder.build().toUri();

        RequestEntity<Void> request = RequestEntity.get(url)
                .accept(mediaType)
                //.body(searchParams);
                .build();
        ResponseEntity<AutoRentalListDTO> response = restTemplate.exchange(request, AutoRentalListDTO.class);
        return response;
    }

}
