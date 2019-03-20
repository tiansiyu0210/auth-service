package com.onevote.authservice.service;

import com.onevote.User;
import com.onevote.authservice.model.OneVoteUserDetails;
import com.onevote.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OneVoteUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByName(name);


        user.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        OneVoteUserDetails oneVoteUserDetails = user
                .map(OneVoteUserDetails::new)
                .get();

        oneVoteUserDetails.setPassword(oneVoteUserDetails.getPassword());
        return oneVoteUserDetails;
    }
}
