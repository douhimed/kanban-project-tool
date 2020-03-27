package org.adex.services.impl;

import org.adex.repositories.UserRepository;
import org.adex.services.ICustomUserDetailsService;
import org.adex.web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CustomUserDetailsServices implements ICustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userRepository.findByUsername(username);
        if(optionalUser.isEmpty()) throw new UsernameNotFoundException("User not found");
        return optionalUser.get();
    }

    public User loadUserById(int id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if(optionalUser.isEmpty()) throw new UsernameNotFoundException("User not found");
        return optionalUser.get();
    }
}
