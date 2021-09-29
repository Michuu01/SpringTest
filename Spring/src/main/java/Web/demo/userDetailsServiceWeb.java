package Web.demo;

import Web.demo.model.AppUser;
import Web.demo.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userDetailsServiceWeb implements UserDetailsService {
    private AppUserRepo appUserRepo;


    @Autowired
    public userDetailsServiceWeb(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return appUserRepo.findByUsername(s);
    }



}
