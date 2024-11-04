package info.ejava_student.maryc.assignment3.assignment3.security.config;


import info.ejava.assignments.security.autorenters.svc.AccountProperties;
import info.ejava.assignments.security.autorenters.svc.Accounts;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

    @Configuration(proxyBeanMethods = false)
    @Profile("anonymous-access")
    public static class PartA1_AnonymousAccess {
        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {
            http.securityMatchers(cfg->cfg.requestMatchers("/api/autos/**","/api/renters/**","/api/autorentals/**"));

            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.GET,"/api/autos/**","/api/autorentals/**").permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.GET,"/api/renters/**").authenticated());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.POST,"/api/autos/query/**","/api/autorentals/query/**").permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.HEAD).permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.POST).authenticated());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.PUT).authenticated());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.DELETE).authenticated());

            http.httpBasic(Customizer.withDefaults());
            http.csrf(AbstractHttpConfigurer::disable);

            return http.build();
        }
    }


    @Configuration(proxyBeanMethods = false)
    @Profile({"authenticated-access", "userdetails"})
    public static class PartA2_AuthenticatedAccess {
        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {
            http.sessionManagement(cfg->cfg.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.securityMatchers(cfg->cfg.requestMatchers("/api/whoAmI/**","/api/autos/**","/api/renters/**","/api/autorentals/**"));

            http.authorizeHttpRequests(cfg->cfg.requestMatchers("/api/whoAmI/**").permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.GET,"/api/autos/**","/api/autorentals/**").permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.POST,"/api/autos/query/**","/api/autorentals/query/**").permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.GET,"/api/renters/**").authenticated());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.HEAD).permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.POST).authenticated());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.PUT).authenticated());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.DELETE).authenticated());

            http.httpBasic(Customizer.withDefaults());
            http.csrf(AbstractHttpConfigurer::disable);

            return http.build();
        }
    }


    @Configuration(proxyBeanMethods = false)
    @Profile("nosecurity")
    public static class PartA2b_NoSecurity {
        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {
            http.sessionManagement(cfg->cfg.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.securityMatchers(cfg->cfg.requestMatchers("/api/whoAmI/**","/api/autos/**","/api/renters/**","/api/autorentals/**"));
            http.httpBasic(Customizer.withDefaults());
            http.csrf(AbstractHttpConfigurer::disable);
            http.authorizeHttpRequests(cfg->cfg.anyRequest().permitAll());

            /*
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.GET).permitAll());
            //http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.POST,"/api/autos/query/**").permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.HEAD).permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.POST).permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.PUT).permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.DELETE).permitAll());
            */
            return http.build();
        }
    }


    @Configuration(proxyBeanMethods = false)
    @Profile({"nosecurity","userdetails", "authorities", "authorization"})
    public static class PartA3_UserDetailsPart {
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userDetailsService(PasswordEncoder encoder, Accounts accounts) {
            List<UserDetails> users = new ArrayList<>();
            List<AccountProperties> db = accounts.getAccounts();
            int idx=0;
            while (db.size()>users.size()){
                AccountProperties account=db.get(idx);
                users.add(
                    User.withUsername(account.getUsername())
                            .passwordEncoder(encoder::encode)
                            .password(account.getPassword())
                            .authorities(account.getAuthorities().toArray(new String[0]))
                            .build()
                );
                idx++;
            }
            return new InMemoryUserDetailsManager(users);
        }
    }


    @Configuration(proxyBeanMethods = false)
    @Profile("authorization")
    @EnableMethodSecurity(prePostEnabled = true)
    //enable global method security for prePostEnabled
    public static class PartA_Authorization {
        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {
            http.sessionManagement(cfg->cfg.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.securityMatchers(cfg->cfg.requestMatchers("/api/whoAmI/**","/api/autos/**","/api/renters/**","/api/autorentals/**"));
            http.httpBasic(Customizer.withDefaults());
            http.csrf(AbstractHttpConfigurer::disable);
            http.authorizeHttpRequests(cfg->cfg.requestMatchers("/api/whoAmI/**").permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.GET,"/api/renters/**").authenticated());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.GET,"/api/autos/**").permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.POST,"/api/autos/query/**","/api/autorentals/query/**").permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.HEAD).permitAll());
            http.authorizeHttpRequests(cfg->cfg.requestMatchers(HttpMethod.DELETE,"/api/autos","/api/renters").hasAnyAuthority("ROLE_ADMIN"));
            http.authorizeHttpRequests(cfg->cfg.anyRequest().authenticated());
            return http.build();
        }

        @Bean
        public RoleHierarchy roleHierarchy() {
            return RoleHierarchyImpl.fromHierarchy("ROLE_ADMIN > ROLE_MGR");
        }
    }
}
