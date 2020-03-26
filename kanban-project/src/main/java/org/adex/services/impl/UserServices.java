package org.adex.services.impl;

import org.adex.repositories.UserRepository;
import org.adex.services.IUserServices;
import org.adex.utilities.exceptions.UsernameAlreadyExistsException;
import org.adex.web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServices implements IUserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        try {
            user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
            user.setUsername(user.getUsername());
            return this.userRepository.save(user);
        }catch (Exception e){
            throw  new UsernameAlreadyExistsException("Username : " + user.getUsername() +" already exists");
        }
    }
}
