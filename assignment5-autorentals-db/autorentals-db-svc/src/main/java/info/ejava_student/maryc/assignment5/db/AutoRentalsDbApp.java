package info.ejava_student.maryc.assignment5.db;

import info.ejava_student.maryc.assignment3.assignment3.aop.autorentals.AOPConfiguration;
import info.ejava_student.maryc.assignment3.assignment3.security.autorentals.SecureAutoRentalsConfiguration;
import info.ejava_student.maryc.assignment3.assignment3.security.config.SecurityConfiguration;
import info.ejava_student.maryc.assignment5.jpa.autorentals.JpaAutoRentalsConfiguration;
import info.ejava_student.maryc.assignment5.jpa.autorentals.JpaAutoRentalsRepository;
import info.ejava_student.maryc.assignment5.mongo.autorentals.MongoAutoRentalsConfiguration;
import info.ejava_student.maryc.assignment5.mongo.autorentals.MongoAutoRentalsRepository;
import info.ejava_student.maryc.assignment5.pageable.PageableConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication(scanBasePackageClasses = {
// some server-side configurations to activate as part of the application
            JpaAutoRentalsConfiguration.class, //JPA autorental implementation option
            MongoAutoRentalsConfiguration.class, //Mongo autorental implementation option
            PageableConfiguration.class, //end-to-end pageable controller extensions
            AOPConfiguration.class, //add in the Aspects
            SecurityConfiguration.class, //SecurityFilterChain supporting authorities and authorization
            SecureAutoRentalsConfiguration.class, //add autorentals programmatic security checks
})

public class AutoRentalsDbApp {
    public static void main(String...args) {
        SpringApplication.run(AutoRentalsDbApp.class, args);
    }

    @Autowired
    private MongoAutoRentalsRepository rentalsRepository;

    @Bean
    @Order(50)
    public SecurityFilterChain h2Configuration(HttpSecurity http) throws Exception {
            http.securityMatchers(cfg->cfg.requestMatchers("/h2-console/**","/login","/logout"));
            http.authorizeHttpRequests(cfg->cfg.requestMatchers("/login","/logout").permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers("/h2-console/**").hasAnyRole("ADMIN","MGR"));

            http.csrf(cfg->cfg.disable());
            http.headers(cfg->cfg.frameOptions(f->f.disable()));
            http.formLogin(cfg->cfg.successForwardUrl("/h2-console"));
            return http.build();
    }
}