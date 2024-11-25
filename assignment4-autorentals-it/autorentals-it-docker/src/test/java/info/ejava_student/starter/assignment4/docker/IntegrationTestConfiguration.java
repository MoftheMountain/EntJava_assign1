package info.ejava_student.starter.assignment4.docker;

import info.ejava.examples.common.web.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

//@SpringBootConfiguration
//@EnableAutoConfiguration
@Slf4j
@Configuration
@ConfigurationProperties("it.server")
public class IntegrationTestConfiguration {
    @Value("${it.server.host:}")
    private String host;

    @Value("${it.server.port:}")
    private String port;

    @Bean
    ServerConfig serverConfig() {
        log.info("IT server port as configured: {}",port);
    ServerConfig serverConfig = new ServerConfig("http",
            host, Integer.parseInt(port),
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
