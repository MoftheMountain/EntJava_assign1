package info.ejava_student.starter.assignment4.docker;

import info.ejava.examples.common.web.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

//@SpringBootConfiguration
//@EnableAutoConfiguration
@Slf4j
public class IntegrationTestConfiguration {

    @Bean
    @ConfigurationProperties("it.server")
    ServerConfig serverConfig() {
    log.info("IT server port as configured: {}",System.getProperty("it.server.port"));
    ServerConfig serverConfig = new ServerConfig("http",
            System.getProperty("it.server.host"),
            Integer.parseInt(System.getProperty("it.server.port")),
            null,null,null);
    log.info("Host {}, Port {}, baseURL {}",serverConfig.getHost(),serverConfig.getPort(),serverConfig.getBaseUrl());
        return serverConfig;
    }

    @Bean
    URI rentalsUrl(ServerConfig serverConfig) {
        return UriComponentsBuilder.fromUri(serverConfig.getBaseUrl())
                .path("/api/autorentals")
                .build()
                .toUri();
    }
}
