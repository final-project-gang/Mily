package com.mily.security;

import com.mily.user.lawyerUser.LawyerUser;
import com.mily.user.lawyerUser.LawyerUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class CustomLawyerDetailService implements UserDetailsService {
    @Autowired
    private LawyerUserRepository lawyerUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userLoginId) throws UsernameNotFoundException {
        LawyerUser lu = lawyerUserRepository.findByUserLoginId(userLoginId).orElseThrow(() -> new UsernameNotFoundException("userLoginId(%s) not found".formatted(userLoginId)));
        return new User(lu.getUserLoginId(), lu.getUserPassword(), lu.getGrantedAuthorities());
    }

    private Set<GrantedAuthority> buildAdminAuthority() {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        setAuths.add(new SimpleGrantedAuthority("ROLE_LAWYER"));

        return setAuths;
    }
}
