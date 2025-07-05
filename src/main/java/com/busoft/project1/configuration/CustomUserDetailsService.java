package com.busoft.project1.configuration;

import com.busoft.project1.constant.UserStatusEnum;
import com.busoft.project1.entity.User;
import com.busoft.project1.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmailAndStatus(email, UserStatusEnum.ACTIVE.getKey());
        return new CustomUserDetails(user);
    }
}

