package info.ejava_student.maryc.assignment3.assignment3.security.autorentals;

import info.ejava.assignments.api.autorenters.svc.autos.AutosService;
import info.ejava.assignments.api.autorenters.svc.renters.RentersService;
import info.ejava.assignments.security.autorenters.svc.AuthorizationHelper;
import info.ejava_student.maryc.assignment2.api.autorentals.AutoRentalsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.util.Assert;

import java.util.List;

@Configuration(proxyBeanMethods = false)
@Slf4j
public class SecureAutoRentalsConfiguration {
    @Primary
    @Bean
    @Profile("nosecurity")
    public AutoRentalsService nosecureAutoRentalsService(List<AutoRentalsService> serviceImpls) {
        Assert.notEmpty(serviceImpls,"no RenterService impls found to secure");
        log.info("nosecurity profile active, allowing all calls to renter service");
        return serviceImpls.get(0); //TODO
        //return new SecureAutoRentalsServiceWrapper(serviceImpls.get(0)); //TODO
        //return new NoSecurityAutoRentalsServiceWrapper(null); //TODO
    }

    @Primary
    @Bean
    @Profile("!nosecurity")
    public AutoRentalsService secureAutoRentalsService(List<AutoRentalsService> serviceImpls,
                                                   AutosService autosService,
                                                   RentersService rentersService,
                                                   AuthorizationHelper authzHelper) {
        Assert.notEmpty(serviceImpls,"no RenterService impls found to secure");
        return new SecureAutoRentalsServiceWrapper(serviceImpls.get(0),rentersService,authzHelper);
    }
}
