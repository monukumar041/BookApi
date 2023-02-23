package com.Rest.API.BookServices;

import com.Rest.API.Entitiy.CustomUserDetails;
import com.Rest.API.Entitiy.User;
import com.Rest.API.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=this.userRepository.findByuserName(username);
        if(user==null)
        {
            throw  new UsernameNotFoundException("No User Found");
        }
        return new CustomUserDetails(user);
    }
}
