package info.ejava_student.starter.assignment5.db;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;

/**
 * Enable this configuration to turn off embedded mongo during. It automatically
 * is enabled when the Spring Data MongoDB URL is present in the properties, which
 * can be supplied when the mongodb profile is activated.
 */
@TestConfiguration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix="spring.data.mongodb",name="uri",matchIfMissing=false)
@EnableAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class DisableEmbeddedMongoConfiguration {
}