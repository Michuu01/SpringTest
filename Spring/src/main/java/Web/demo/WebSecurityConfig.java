package Web.demo;


import Web.demo.model.AppUser;
import Web.demo.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private userDetailsServiceWeb userDetailsServiceWeb;
    private AppUserRepo appUserRepo;

    @Autowired
    public WebSecurityConfig(Web.demo.userDetailsServiceWeb userDetailsServiceWeb, AppUserRepo appUserRepo) {
        this.userDetailsServiceWeb = userDetailsServiceWeb;
        this.appUserRepo = appUserRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsServiceWeb);

    }@Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/test1").authenticated()
                .and().formLogin().permitAll();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        AppUser appUser = new AppUser("Michal", passwordEncoder().encode("michals"), "user");
        appUserRepo.save(appUser);

    }
}
