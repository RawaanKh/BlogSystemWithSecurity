package com.example.bloog.Service;

import com.example.bloog.API.ApiException;
import com.example.bloog.Model.MyUser;
import com.example.bloog.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

    private final MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser=myUserRepository.findMyUserByUsername( username);
        if(myUser==null){
            throw new ApiException("Wrong Username or password");
        }
        return myUser;
    }
}
