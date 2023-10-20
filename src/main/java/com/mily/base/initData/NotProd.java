package com.mily.base.initData;

import com.mily.article.milyx.category.CategoryService;
import com.mily.article.milyx.category.entity.FirstCategory;
import com.mily.user.MilyUserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@AllArgsConstructor
@Profile("!prod")
public class NotProd {
    private final MilyUserService milyUserService;
    private final CategoryService categoryService;
    private FirstCategory fc;

    @Bean
    public ApplicationRunner init () {
        return args -> {
            milyUserService.signup("admin123", "9a9a9a9a", "administrator", "administrator", "admin123@email.com", "99999999999", "1996-10-05");
            milyUserService.signup("testaccount", "qwerasdf", "testaccount", "testaccount", "testaccount@email.com", "88888888888", "1996-10-05");

            categoryService.addFC("성 범죄");
            categoryService.addFC("재산 범죄");
            categoryService.addFC("교통사고/범죄");
            categoryService.addFC("폭행/협박");
            categoryService.addFC("명예훼손/모욕");
            categoryService.addFC("기타 형사범죄");
            categoryService.addFC("부동산/임대차");
            categoryService.addFC("금전/계약 문제");
            categoryService.addFC("민사 절차");
            categoryService.addFC("기타 민사 문제");
            categoryService.addFC("가족");
            categoryService.addFC("회사");
            categoryService.addFC("의료/세금/행정");
            categoryService.addFC("IT/지식재산/금융");

            fc = categoryService.findByTitle("성 범죄");

            categoryService.addSC("성매매", fc);
            categoryService.addSC("성폭력/강제추행 등", fc);
            categoryService.addSC("미성년 대상 성범죄", fc);
            categoryService.addSC("디지털 성범죄", fc);
            
            fc = categoryService.findByTitle("재산 범죄");
            
            categoryService.addSC("횡령/배임", fc);
            categoryService.addSC("사기/공갈", fc);
            categoryService.addSC("기타 재산범죄", fc);
            
            fc = categoryService.findByTitle("교통사고/범죄");
            
            categoryService.addSC("교통사고/도주", fc);
            categoryService.addSC("음주/무면허", fc);

            fc = categoryService.findByTitle("폭행/협박");

            categoryService.addSC("폭행/협박/상해 일반", fc);
            
            fc = categoryService.findByTitle("명예훼손/모욕");

            categoryService.addSC("명예훼손/모욕 일반", fc);
            categoryService.addSC("사이버 명예훼손/모욕", fc);

            fc = categoryService.findByTitle("기타 형사범죄");

            categoryService.addSC("마약/도박", fc);
            categoryService.addSC("소년범죄/학교폭력", fc);
            categoryService.addSC("형사일반/기타범죄", fc);

            fc = categoryService.findByTitle("부동산/임대차");

            categoryService.addSC("건축/부동산 일반", fc);
            categoryService.addSC("재개발/재건축", fc);
            categoryService.addSC("매매/소유권 등", fc);
            categoryService.addSC("임대차", fc);

            fc = categoryService.findByTitle("금전/계약 문제");

            categoryService.addSC("손해배상", fc);
            categoryService.addSC("대여금/채권추심", fc);
            categoryService.addSC("계약일반/매매", fc);

            fc = categoryService.findByTitle("민사 절차");

            categoryService.addSC("소송/집행절차", fc);
            categoryService.addSC("가압류/가처분", fc);
            categoryService.addSC("회생/파산", fc);

            fc = categoryService.findByTitle("기타 민사 문제");

            categoryService.addSC("공증/내용증명/조합/국제문제 등", fc);

            fc = categoryService.findByTitle("가족");

            categoryService.addSC("이혼", fc);
            categoryService.addSC("상속", fc);
            categoryService.addSC("가사 일반", fc);

            fc = categoryService.findByTitle("회사");

            categoryService.addSC("기업법무", fc);
            categoryService.addSC("노동/인사", fc);

            fc = categoryService.findByTitle("의료/세금/행정");

            categoryService.addSC("세금/행정/헌법", fc);
            categoryService.addSC("의료/식품의약", fc);
            categoryService.addSC("병역/군형법", fc);

            fc = categoryService.findByTitle("IT/지식재산/금융");

            categoryService.addSC("소비자/공정거래", fc);
            categoryService.addSC("IT/개인정보", fc);
            categoryService.addSC("지식재산권/엔터", fc);
            categoryService.addSC("금융/보험", fc);
        };
    }
}