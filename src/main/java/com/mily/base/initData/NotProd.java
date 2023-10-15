package com.mily.base.initData;

import com.mily.user.milyUser.MilyUserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!prod")
public class NotProd {
    @Bean
    public ApplicationRunner init(MilyUserService milyUserService) {
        return args -> {
            milyUserService.signup("admin", "1234", "관리자", "관리자", "admin@email.com", "99999999999", null);
        };
    }
}