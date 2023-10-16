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
            milyUserService.signup("admin123", "9a9a9a9a", "administrator", "administrator", "admin123@email.com", "99999999999", "1996-10-05");
        };
    }
}