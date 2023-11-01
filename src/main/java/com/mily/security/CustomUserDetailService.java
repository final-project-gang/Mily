package com.mily.security;

import com.mily.user.milyUser.MilyUser;
import com.mily.user.milyUser.MilyUserRepository;
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
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private MilyUserRepository milyUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userLoginId) throws UsernameNotFoundException {
        MilyUser mu = milyUserRepository.findByUserLoginId(userLoginId).orElseThrow(() -> new UsernameNotFoundException("userLoginId(%s) not found".formatted(userLoginId)));
        return new User(mu.getUserLoginId(), mu.getUserPassword(), mu.getGrantedAuthorities());
    }

    private Set<GrantedAuthority> buildAdminAuthority() {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        setAuths.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        return setAuths;
    }
}