package com.blog.react_spring_blog.security.jwt;

import com.blog.react_spring_blog.common.exception.ResourceNotFoundException;
import com.blog.react_spring_blog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepo;

    @Override
    public UserDetials loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.memberRepo.findByEmail(username).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Email: ", username));
    }

}