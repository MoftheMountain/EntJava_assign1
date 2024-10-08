package info.ejava_student.maryc.assignment2.api.api.autorentals.impl;

import info.ejava.assignments.api.autorenters.svc.rentals.ApiTestHelper;
import info.ejava.examples.common.web.RestTemplateConfig;
import info.ejava.examples.common.web.ServerConfig;
import info.ejava_student.maryc.assignment2.api.autorentals.client.client.AutoRentalsAPI;
import info.ejava_student.maryc.assignment2.api.autorentals.client.client.AutoRentalsAPIClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

import java.awt.*;

//TODO: implement this @TestConfiguration for MyAutoRentalsAPINTest
/**
 * This test configuration will provide a factory bean for
 * the test-helper and any additional injections the test-helper
 * requires.
 */
@TestConfiguration(proxyBeanMethods = false)
public class ApiImplNTestConfiguration {
    @Bean
    public ApiTestHelper testHelper(/*dependencies of your helper*/) {
        return new ApiTestHelperImpl(/*required args*/);
    }

/*    @Bean
    public AutoRentalsAPI autoRentalsAPIClient(RestTemplate restTemplate, ServerConfig serverConfig, MediaType mediaType){
        return new AutoRentalsAPIClient(restTemplate,serverConfig,mediaType);
    }*/

    //you may want a DTO factory @Bean
}
