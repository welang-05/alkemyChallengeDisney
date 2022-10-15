package Alkemy.Disney.configuration;

import Alkemy.Disney.models.Usuario;
import Alkemy.Disney.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UsuarioServices usuarioServices;


    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputEmail-> {

            Usuario usuario = usuarioServices.getUsuarioByEmail(inputEmail);

            if (usuario != null) {

                return new User(usuario.getEmail(), usuario.getPassword(),

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
