package info.ejava_student.starter.assignment4.it.tc;

import info.ejava.examples.common.web.ServerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

//@SpringBootConfiguration
//@EnableAutoConfiguration
public class IntegrationTestConfiguration {
    @Bean
    @ConfigurationProperties("it.server")
    ServerConfig serverConfig() {
        return new ServerConfig();
    }

    @Bean
    URI rentalsURL(ServerConfig serverConfig) {
        return UriComponentsBuilder.fromUri(serverConfig.getBaseUrl())
                .path("/api/autorentals")
                .build()
                .toUri();
    }
}
