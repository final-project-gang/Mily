package com.mily.user.milyUser;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@Component
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@SuperBuilder
public class MilyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String userLoginId;

    private String userPassword;

    @Column(unique = true)
    private String userNickName;

    private String userName;

    @Column(unique = true)
    private String userEmail;

    @Column(unique = true)
    private String userPhoneNumber;

    private String userDateOfBirth;

    private String userCreateDate;

    private String area;

    public boolean isAdmin() {
        return "admin123".equals(userLoginId);
    }

    public List<? extends GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        // 모든 멤버는 member 권한을 가집니다.
        grantedAuthorities.add(new SimpleGrantedAuthority("member"));

        // userLoginId 가 admin인 회원은 admin 권한도 가집니다.
        if (isAdmin()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("admin123"));
        }

        return grantedAuthorities;
    }
}