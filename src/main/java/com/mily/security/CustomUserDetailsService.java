package com.mily.security;

import com.mily.user.lawyerUser.LawyerUser;
import com.mily.user.lawyerUser.LawyerUserRepository;
import com.mily.user.milyUser.MilyUser;
import com.mily.user.milyUser.MilyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
    private final MilyUserRepository milyUserRepository;
    private final LawyerUserRepository lawyerUserRepository;

    @Override
    public UserDetails loadUserByUsername(String UserLoginId) throws UsernameNotFoundException {
        MilyUser mu = milyUserRepository.findByUserLoginId(UserLoginId).orElse(null);
        LawyerUser lu = lawyerUserRepository.findByName(UserLoginId).orElse(null);

        if (mu != null) {
            return new User(mu.getUserLoginId(), mu.getUserPassword(), mu.getGrantedAuthorities());
        } else if (lu != null) {
            return new User(lu.getName(), lu.getPassword(), lu.getGrantedAuthorities());
        } else {
            throw new UsernameNotFoundException("UserLoginId(%s) not found".formatted(UserLoginId));
        }
    }
}