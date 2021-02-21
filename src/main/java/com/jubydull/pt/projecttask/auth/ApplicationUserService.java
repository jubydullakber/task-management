package com.jubydull.pt.projecttask.auth;

import com.jubydull.pt.projecttask.entity.User;
import com.jubydull.pt.projecttask.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.jubydull.pt.projecttask.enums.ApplicationUserRole.*;
@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = applicationUserRepository
                .findByUserName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username))
                );
        if (user.getRole().equals("USER")) {
            user.setGrantedAuthorities(USER.getGrantedAuthorities());
        } else if (user.getRole().equals("ADMIN")) {
            user.setGrantedAuthorities(ADMIN.getGrantedAuthorities());
        } else {

        }
        return user;
    }
}