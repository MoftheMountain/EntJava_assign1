package info.ejava_student.starter.assignment5.db.rentals.impl;

import info.ejava.assignments.db.autorentals.DbTestHelper;
import info.ejava.examples.common.web.ServerConfig;
import info.ejava_student.maryc.assignment2.api.autorentals.client.AutoRentalsAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

/**
 * This test configuration may be included by tests that are DB-centric
 * (without web resources) and web-oriented (making client/server
 * test helper calls). To try to support both environments -- instantiate
 * versions of the DBTestHelper that can be used with and without
 * web resources.
 */
@TestConfiguration
public class DbClientTestConfiguration {
    @Bean @Lazy
    @ConditionalOnBean({RestTemplate.class,ServerConfig.class})
    public DbTestHelper fullTestHelper(/*helper web dependencies*/) {
        return null;
    }

    //early version of DbTestHelper does not require web resources
    //that may not be available
    @Bean
    @ConditionalOnMissingBean({RestTemplate.class,ServerConfig.class})
    public DbTestHelper dbOnlyTestHelper() {
        return null;
    }


//anything to support the DbTestHelper

//    @Bean
//    any DbTestHelper dependencies having no resource requirements

//    @Bean @Lazy
//    @ConditionalOnBean({RestTemplate.class, ServerConfig.class})
//    any DbTestHelper dependencies needed for web

}
