package info.ejava_student.maryc.assignment2.api.autorentals.impl;

import info.ejava.assignments.api.autorenters.svc.rentals.ApiTestHelper;
import info.ejava.examples.common.web.ServerConfig;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalsAPI;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalsAPIClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

//TODO: implement this @TestConfiguration for MyAutoRentalsAPINTest
/**
 * This test configuration will provide a factory bean for
 * the test-helper and any additional injections the test-helper
 * requires.
 */
@TestConfiguration(proxyBeanMethods = false)
public class ApiImplNTestConfiguration {
    @Bean
    @Lazy
    public ApiTestHelper testHelper(RestTemplate restTemplate, ServerConfig serverConfig) {
        return new ApiTestHelperImpl(restTemplate, serverConfig);
    }

    @Bean
    @Lazy
    @ConditionalOnMissingBean
    public AutoRentalsAPIClient autoRentalsAPIClient(RestTemplate restTemplate, ServerConfig serverConfig){
        return new AutoRentalsAPIClient(restTemplate,serverConfig,MediaType.APPLICATION_JSON);
    }

    //you may want a DTO factory @Bean
}
