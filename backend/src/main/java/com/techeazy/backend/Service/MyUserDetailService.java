package com.techeazy.backend.Service;

import com.techeazy.backend.Entity.Users;
import com.techeazy.backend.Repo.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class MyUserDetailService  implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=userDao.findByUsername(username);
        if(users ==  null)
            throw new UsernameNotFoundException("user not found");
        return new User(users.getUsername(),users.getPassword(), Collections.singleton(new SimpleGrantedAuthority(users.getRole())));
    }
}
