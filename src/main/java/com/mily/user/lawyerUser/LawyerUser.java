package com.mily.user.lawyerUser;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
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

    private String name;

    private String password;

    @Email
    private String email;

    @Column(unique = true)
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