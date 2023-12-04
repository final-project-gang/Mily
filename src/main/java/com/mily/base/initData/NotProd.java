package com.mily.base.initData;

import com.mily.article.milyx.MilyX;
import com.mily.article.milyx.MilyXService;
import com.mily.article.milyx.category.CategoryService;
import com.mily.article.milyx.category.entity.FirstCategory;
import com.mily.article.milyx.category.entity.SecondCategory;
import com.mily.article.milyx.comment.MilyXCommentService;
import com.mily.payment.PaymentService;
import com.mily.user.MilyUser;
import com.mily.user.MilyUserRepository;
import com.mily.user.MilyUserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

@Configuration
@AllArgsConstructor
@Profile("!prod")
public class NotProd {
    private final MilyUserService milyUserService;
    private final MilyUserRepository milyUserRepository;
    private final CategoryService categoryService;
    private final MilyXService milyXService;
    private final MilyXCommentService milyXCommentService;
    private final PaymentService paymentService;
    private FirstCategory fc;

    @Bean
    public ApplicationRunner init() {
        return args -> {
            Optional<MilyUser> mu = milyUserService.findByUserLoginId("admin999");
            if (mu.isEmpty()) {
                MilyUser milyUser1 = milyUserService.userSignup("admin999", "qweasdzxc", "administrator", "admin999@admin.com", "01099999999", "1975-01-21").getData();
                milyUser1.setRole("admin");
                milyUserRepository.save(milyUser1);
                MilyUser milyUser2 = milyUserService.userSignup("leewowns1005", "a7586898", "이재준", "leewowns1005@naver.com", "01020105481", "1996-10-05").getData();
                MilyUser milyUser3 = milyUserService.userSignup("oizill5481", "a7586898", "이재준", "oizill5481@icloud.com", "01045702579", "1996-10-05").getData();
                MilyUser milyUser4 = milyUserService.userSignup("test1111", "test1111", "한문철", "test1111@email.com", "01011111111", "1996-01-01").getData();
                milyUserService.lawyerSignup("교통사고/범죄", "교통 사고, 상해 전문 승소율 97% 이상 / 처리까지 최저 수임료로", "법무법인 아로", "1111-1111-1111", "서울", milyUser4, "https://i.postimg.cc/J7sVTCpC/Vudz-Wb-LYPYgc-OBwt32ap-Na-ZBHt5b8-AXA.webp");
                MilyUser milyUser5 = milyUserService.userSignup("test2222", "test2222", "박추어", "test2222@email.com", "01022222222", "1999-04-08").getData();
                milyUserService.lawyerSignup("민사 절차", "<1,400건 이상의 후기> 검증된 변호사 / 합리적 수임료", "법무법인 새긴", "2222-2222-2222", "대구", milyUser5, "https://i.postimg.cc/63sjzFqt/SE-8dd832e9-2fa4-4cb4-9868-f1ebc.jpg");
                MilyUser milyUser6 = milyUserService.userSignup("test3333", "test3333", "이정건", "test3333@email.com", "01033333333", "1999-09-24").getData();
                milyUserService.lawyerSignup("성 범죄", "[성매매/성범죄] 초기 대응부터 확실하게", "법률사무소 M&Y", "3333-3333-3333", "강원", milyUser6, "https://i.postimg.cc/0QkzDyvS/Kakao-Talk-20231125-230349223.jpg");
                MilyUser milyUser7 = milyUserService.userSignup("test4444", "test4444", "이재영", "test4444@email.com", "01044444444", "1997-12-07").getData();
                milyUserService.lawyerSignup("가족", "TV출연/대형 로펌 출신/이혼 관련 상담 2000건 이상", "MILY L&C", "4444-4444-4444", "경기", milyUser7, "https://i.postimg.cc/dtYHGRCT/Kakao-Talk-20231125-222200034.jpg");
                MilyUser milyUser8 = milyUserService.userSignup("test5555", "test5555", "정명준", "test5555@email.com", "01055555555", "1997-08-11").getData();
                milyUserService.lawyerSignup("교통사고/범죄", "교통 사고, 상해 전문 승소율 90% 이상 / 처리까지 최저 수임료로", "MILY L&C", "5555-5555-5555", "대전", milyUser8, "https://i.postimg.cc/Kc90KKbn/IMG-1638.jpg");
                MilyUser milyUser9 = milyUserService.userSignup("test6666", "test6666", "조승근", "test6666@email.com", "01066666666", "1999-03-04").getData();
                milyUserService.lawyerSignup("재산 범죄", "전세 사기 피해 관련 상담 313건의 노하우로 철저하게 도와드립니다.", "법률사무소 SPRING", "6666-6666-6666", "대구", milyUser9, "https://i.postimg.cc/FsDJtqbK/18449-26167-5550-1.jpg");

                milyUser4.setRole("approve");
                milyUser5.setRole("approve");
                milyUser6.setRole("approve");
                milyUser7.setRole("approve");
                milyUserRepository.save(milyUser4);
                milyUserRepository.save(milyUser5);
                milyUserRepository.save(milyUser6);
                milyUserRepository.save(milyUser7);

                MilyUser milyUser10 = milyUserService.userSignup("user1111", "user1111", "user1111", "user1111@email.com", "01010101010", "1992-09-21").getData();
                MilyUser milyUser11 = milyUserService.userSignup("user2222", "user2222", "user2222", "user2222@email.com", "01020202020", "1989-05-30").getData();
                MilyUser milyUser12 = milyUserService.userSignup("user3333", "user3333", "user3333", "user3333@email.com", "01030303030", "1989-02-10").getData();
                MilyUser milyUser13 = milyUserService.userSignup("user4444", "user4444", "user4444", "user4444@email.com", "01040404040", "1988-04-12").getData();
                MilyUser milyUser14 = milyUserService.userSignup("user5555", "user5555", "user5555", "user5555@email.com", "01050505050", "1990-01-31").getData();
                MilyUser milyUser15 = milyUserService.userSignup("user6666", "user6666", "user6666", "user6666@email.com", "01060606060", "1998-12-06").getData();

                paymentService.dummyPayment("161251211", milyUser2, 300, "밀리 포인트 300", (long) 4800);
                paymentService.dummyPayment("161251212", milyUser3, 50, "밀리 포인트 50", (long) 900);
                paymentService.dummyPayment("161251213", milyUser3, 100, "밀리 포인트 100", (long) 1700);

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
                milyXService.dummyCreate(milyUser2, firstCategory, secondCategory, "대학교 후배와 성관계 이후 상대방이 고소를 진행할 예정이랍니다.", "올해 초에 처음 관계를 맺었으며, 관계 이후에도 연락을 주고 받으면서 지냈습니다. 친하게 지냈음에도 불구하고, 5월에 상대방이 교수님과 상담하며 본인과 관계한 것에 대해 자궁에 상처가 났고, 성폭력을 당한 것처럼 주장했습니다. 처음 연락을 주고받은 시점부터 연락한 내용은 남아있습니다. 좋게 지내다가 돌연 사이가 소원해지면서 이전의 성관계에 대해 증거가 없다 생각해서 저러는 것 같은데, 어떻게 대처해야할까요? 무죄를 입증할만한 증거가 있다면 무죄 판정 받은 후에 무고죄도 성립될까요?", 50);
                milyUserService.sendEstimate(firstCategory, secondCategory, "대전", "내용", milyUser2);

                firstCategory = categoryService.findByFTitle("폭행/협박");
                secondCategory = categoryService.findBySTitle("폭행/협박/상해 일반");
                milyXService.dummyCreate(milyUser3, firstCategory, secondCategory, "술집에서 싸움에 휘말려 양방 폭행이 나왔습니다.", "사건 일시 : 2023년 10월 24일\n사건 경위 : 모르는 남성이 옆 테이블에 앉은 여성 일행에게 번호를 물었고, 일행이 완강하게 거부 의사를 밝혔으나 돌아가지 않고 계속 머뭇거려서 '자리로 돌아가세요 싫다잖아요'라고 한 마디 하자마자 얼굴을 한 대 맞았습니다.\n이후 저는 하지 말라는 의사를 두 차례 전달하였음에도 불구하고 밀치는 등의 행위를 계속 취해 와서 바닥에 넘어뜨렸는데 쌍방이랍니다.", 50);
                milyUserService.sendEstimate(firstCategory, secondCategory, "대전", "내용", milyUser3);

                firstCategory = categoryService.findByFTitle("명예훼손/모욕");
                secondCategory = categoryService.findBySTitle("사이버 명예훼손/모욕");
                milyXService.dummyCreate(milyUser12, firstCategory, secondCategory, "게임하다가 심한 욕설을 들었습니다.", "사건 발생 : 2023.09.10 11:35 a.m.\n" +
                        "본인 챔피언 : 카이사\n가해 챔피언 : 리 신\n내용 : 리그오브레전드 일반 (팀)게임을 하면서" +
                        "'만년브론즈희생폿(본인)아, 니 엄마는 너를 낳지 말았어야 한다', '이게 피임의 중요성이다 시', '발아 ㅋㅋ'" +
                        "라고 하며 저를 능욕했고, 수치심과 모욕감에 한참을 진정하지 못 했습니다.\n본인 챔피언인 카이사를 특정하며 말을 해서" +
                        "특정성과 팀 게임 특성상 공연성은 성립될 거 같은데, 모욕성까지 해서 모욕죄 성립 요건 3가지를 충족했으니 고소할 수 있을까요?", 50);
                milyUserService.sendEstimate(firstCategory, secondCategory, "대전", "내용", milyUser12);

                firstCategory = categoryService.findByFTitle("가족");
                secondCategory = categoryService.findBySTitle("이혼");
                milyXService.dummyCreate(milyUser10, firstCategory, secondCategory, "양육비와 재산분할에 대한 상담", "협의 이혼 준비 중이며\n" +
                        "양육비 산정표에 있는 금액이 한명의 아이에 대한 금액인가요?! 아니면 아이가 두명이면 2를 곱하면 되나요?!\n" +
                        "\n" +
                        "하기 조건인 경우에 월 양육비를 대략 어느정도 받을 수 있는지 계산 부탁드립니다.\n" +
                        "1. 2명의 자녀 19년 22년생(만1세, 만 4세) \n" +
                        "2. 남편 세후 통장에 찍히는 금액 약 450~460만원 \n" +
                        "와이프 21년도 연봉 5300만원 22년도 육아휴직 중간에 들어가서 2400만원 23년도 육아휴직 후 5월 퇴사하여 0원\n" +
                        "3. 대도시 거주 \n" +
                        "\n" +
                        "이 조건이면 약 월 양육비가 어느정도 되나요?", 70);

                firstCategory = categoryService.findByFTitle("교통사고/범죄");
                secondCategory = categoryService.findBySTitle("음주/무면허");
                MilyX milyX1 = milyXService.dummyCreate(milyUser14, firstCategory, secondCategory, "비접촉 사고의 보상 방법은?", "아침 출근길 정체구간 무리한 차량 끼어들기가 있어 피하기위해 피하려했으나 해당차량이 저를 차선 밖으로 밀어내 차량에 스크래치가 생겼습니다 블랙박스 영상 가지고있으며 해당 운전자를 신고해 보상을 받을수있는 방법이 있을까요?\n" +
                        "\n" +
                        "상대 차량 카니발 리무진 이며\n" +
                        "제 차량은 아우디 차량 입니다\n" +
                        "우측면 차량 도장 부분에 손상부분이 보이며\n" +
                        "해당 부분을 수리해야할것 같은데 해당 차량은 그냥 도망간 상황 입니다", 100);

                milyXCommentService.dummyCreate(milyX1, "상담자분께서 상대차량을 피하려고 핸들을 돌리지 않았다면 상대차량과 사고가 날 수 밖에 없었던 상황으로 판단된다면 비접촉사고에 해당될 수 있을 것으로 판단됩니다. 이와 같은 관점에서 사고에 대한 판단을 해보시면 좋을 것 같고, 이후 비접촉사고로 판단될 경우에는 상대방에게 손해의 배상을 청구하실 수 있을 것으로 보입니다.\n" +
                        "관련하여 궁금하신 사항이 있으시거나 조력이 필요하실 경우 편하게 연락주십시오. 감사합니다.", milyUser4);

                firstCategory = categoryService.findByFTitle("명예훼손/모욕");
                secondCategory = categoryService.findBySTitle("사이버 명예훼손/모욕");
                milyXService.dummyCreate(milyUser11, firstCategory, secondCategory, "고소접수 후 수사관 연락 시기와 절차에 대한 질문", "안녕하세요 저는 피고소인 입장입니다. 디시인사이드 마이너 갤러리에서 성적 발언을 해서 통매음으로 고소당한 사건입니다. 저번 주에 갤러리를 통해 고소접수를 했다는 글을 올려서 알게되었습니다. 고소인은 pdf, 아카이브를 다 따놔있는 상태라서 현재 저는 문제되는 해당 계정을 삭제하고 탈퇴를 진행중입니다. (원본 삭제) 고소인은 고소 다음주에 바로 진술하러간다고 하는데 보통 이렇게 빨리 진술하러 가는 걸까요? 피고소인인 저에게 수사관이 연락오려면 어느 정도 시간이 걸릴까요?\n" +
                        "\n" +
                        "그리고 이관될 때 수사관이 전화로\n" +
                        "제 신상을 물어본 다음 우편으로 이관됐다고 나오는 게 맞는 걸까요?", 50);
            }
        };
    }
}