package Alkemy.Disney.configuration;

import Alkemy.Disney.models.User;
import Alkemy.Disney.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserServices userServices;


    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputEmail-> {

            User user = userServices.getUserByEmail(inputEmail);

            if (user != null) {

                return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),

                        AuthorityUtils.createAuthorityList("USER"));

            } else {

                throw new UsernameNotFoundException("Unknown user: " + inputEmail);

            }

        });

    }

    @Bean
    public DelegatingPasswordEncoder passwordEncoder() {

        return (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

}
