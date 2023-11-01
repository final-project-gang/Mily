package com.mily.base.initData;

import com.mily.user.lawyerUser.LawyerUserService;
import com.mily.user.milyUser.MilyUserService;
import com.mily.article.milyx.MilyXService;
import com.mily.article.milyx.category.CategoryService;
import com.mily.article.milyx.category.entity.FirstCategory;
import com.mily.article.milyx.category.entity.SecondCategory;
import com.mily.user.milyUser.MilyUser;
import com.mily.user.milyUser.MilyUserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

@Configuration
@AllArgsConstructor
@Profile("!prod")
public class NotProd {
    private final MilyUserService milyUserService;
    private final LawyerUserService lawyerUserService;
    private final CategoryService categoryService;
    private final MilyXService milyXService;
    private FirstCategory fc;
    @Bean
    public ApplicationRunner init() {
        return args -> {
            Optional<MilyUser> mu = milyUserService.findByUserLoginId("admin123");
            if (mu.isEmpty()) {
                MilyUser milyUser1 = milyUserService.signup("admin123", "9a9a9a9a", "administrator", "administrator", "admin123@email.com", "99999999999", "1996-10-05", "").getData();
                MilyUser milyUser2 = milyUserService.signup("testaccount", "qwerasdf", "testaccount", "testaccount", "testaccount@email.com", "88888888888", "1996-10-05", "대전").getData();
                lawyerUserService.signup("test1111","test1111", "test1111", "01011111111", "test1111@email.com", "", "", "형사", "1111", "서울");
                lawyerUserService.signup("test2222","test2222", "test2222", "01022222222", "test2222@email.com", "", "", "형사", "2222", "대전");
                lawyerUserService.signup("test3333","test3333", "test3333", "01033333333", "test3333@email.com", "", "", "민사", "3333", "서울");
                lawyerUserService.signup("test4444","test4444", "test4444", "01044444444", "test4444@email.com", "", "", "민사", "4444", "대전");

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

                fc = categoryService.findByFTitle("성 범죄");

                categoryService.addSC("성매매", fc);
                categoryService.addSC("성폭력/강제추행 등", fc);
                categoryService.addSC("미성년 대상 성범죄", fc);
                categoryService.addSC("디지털 성범죄", fc);

                fc = categoryService.findByFTitle("재산 범죄");

                categoryService.addSC("횡령/배임", fc);
                categoryService.addSC("사기/공갈", fc);
                categoryService.addSC("기타 재산범죄", fc);

                fc = categoryService.findByFTitle("교통사고/범죄");

                categoryService.addSC("교통사고/도주", fc);
                categoryService.addSC("음주/무면허", fc);

                fc = categoryService.findByFTitle("폭행/협박");

                categoryService.addSC("폭행/협박/상해 일반", fc);

                fc = categoryService.findByFTitle("명예훼손/모욕");

                categoryService.addSC("명예훼손/모욕 일반", fc);
                categoryService.addSC("사이버 명예훼손/모욕", fc);

                fc = categoryService.findByFTitle("기타 형사범죄");

                categoryService.addSC("마약/도박", fc);
                categoryService.addSC("소년범죄/학교폭력", fc);
                categoryService.addSC("형사일반/기타범죄", fc);

                fc = categoryService.findByFTitle("부동산/임대차");

                categoryService.addSC("건축/부동산 일반", fc);
                categoryService.addSC("재개발/재건축", fc);
                categoryService.addSC("매매/소유권 등", fc);
                categoryService.addSC("임대차", fc);

                fc = categoryService.findByFTitle("금전/계약 문제");

                categoryService.addSC("손해배상", fc);
                categoryService.addSC("대여금/채권추심", fc);
                categoryService.addSC("계약일반/매매", fc);

                fc = categoryService.findByFTitle("민사 절차");

                categoryService.addSC("소송/집행절차", fc);
                categoryService.addSC("가압류/가처분", fc);
                categoryService.addSC("회생/파산", fc);

                fc = categoryService.findByFTitle("기타 민사 문제");

                categoryService.addSC("공증/내용증명/조합/국제문제 등", fc);

                fc = categoryService.findByFTitle("가족");

                categoryService.addSC("이혼", fc);
                categoryService.addSC("상속", fc);
                categoryService.addSC("가사 일반", fc);

                fc = categoryService.findByFTitle("회사");

                categoryService.addSC("기업법무", fc);
                categoryService.addSC("노동/인사", fc);

                fc = categoryService.findByFTitle("의료/세금/행정");

                categoryService.addSC("세금/행정/헌법", fc);
                categoryService.addSC("의료/식품의약", fc);
                categoryService.addSC("병역/군형법", fc);

                fc = categoryService.findByFTitle("IT/지식재산/금융");

                categoryService.addSC("소비자/공정거래", fc);
                categoryService.addSC("IT/개인정보", fc);
                categoryService.addSC("지식재산권/엔터", fc);
                categoryService.addSC("금융/보험", fc);

                FirstCategory firstCategory = categoryService.findByFTitle("성 범죄");
                SecondCategory secondCategory = categoryService.findBySTitle("성폭력/강제추행 등");
                milyXService.create(milyUser1, firstCategory, secondCategory, "대학교 후배와 성관계 이후 상대방이 고소를 진행할 예정이랍니다.", "올해 초에 처음 관계를 맺었으며, 관계 이후에도 연락을 주고 받으면서 지냈습니다. 친하게 지냈음에도 불구하고, 5월에 상대방이 교수님과 상담하며 본인과 관계한 것에 대해 자궁에 상처가 났고, 성폭력을 당한 것처럼 주장했습니다. 처음 연락을 주고받은 시점부터 연락한 내용은 남아있습니다. 좋게 지내다가 돌연 사이가 소원해지면서 이전의 성관계에 대해 증거가 없다 생각해서 저러는 것 같은데, 어떻게 대처해야할까요? 무죄를 입증할만한 증거가 있다면 무죄 판정 받은 후에 무고죄도 성립될까요?");

                firstCategory = categoryService.findByFTitle("폭행/협박");
                secondCategory = categoryService.findBySTitle("폭행/협박/상해 일반");
                milyXService.create(milyUser1, firstCategory, secondCategory, "술집에서 싸움에 휘말려 양방 폭행이 나왔습니다.", "사건 일시 : 2023년 10월 24일");

                firstCategory = categoryService.findByFTitle("명예훼손/모욕");
                secondCategory = categoryService.findBySTitle("사이버 명예훼손/모욕");
                milyXService.create(milyUser1, firstCategory, secondCategory, "test", "test");
            }
        };
    }
}