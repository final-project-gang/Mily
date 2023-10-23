package com.mily.base.initData;

import com.mily.user.lawyerUser.LawyerUserService;
import com.mily.user.milyUser.MilyUserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!prod")
public class NotProd {
    @Bean
    public ApplicationRunner init(MilyUserService milyUserService, LawyerUserService lawyerUserService) {
        return args -> {
            milyUserService.signup("admin123", "9a9a9a9a", "administrator", "administrator", "admin123@email.com", "99999999999", "1996-10-05","");
            milyUserService.signup("testaccount", "qwerasdf", "testaccount", "testaccount", "testaccount@email.com", "88888888888", "1996-10-05", "대전");
            lawyerUserService.signup("test1111", "test1111", "01011111111", "test1111@email.com", "", "", "형사", "1111", "서울");
            lawyerUserService.signup("test2222", "test2222", "01022222222", "test2222@email.com", "", "", "형사", "2222", "대전");
            lawyerUserService.signup("test3333", "test3333", "01033333333", "test3333@email.com", "", "", "민사", "3333", "서울");
            lawyerUserService.signup("test4444", "test4444", "01044444444", "test4444@email.com", "", "", "민사", "4444", "대전");
        };
    }
}