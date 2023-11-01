package com.mily.user.lawyerUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawyerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userLoginId;

    private String userPassword;

    private String name;

    @Email
    private String email;

    private String phoneNumber;

    private String organization;

    private String organizationNumber;

    private String major;

    private String introduce;

    private String current;

    private String createDate;

    private String area;

    public boolean isApprove() {
        return "approve".equals(current);
    }

    public List<? extends GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("member"));

        if (isApprove()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("lawyer"));
        }

        return grantedAuthorities;
    }
}