package com.mily.article.milyx;

import com.mily.article.milyx.category.entity.FirstCategory;
import com.mily.article.milyx.category.entity.SecondCategory;
import com.mily.article.milyx.repository.MilyXRepository;
import com.mily.base.rsData.RsData;
import com.mily.user.MilyUser;
import com.mily.user.MilyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MilyXService {
    private final MilyXRepository mxr;
    private final MilyUserRepository mur;

    public List<MilyX> getAllPosts() {
        return mxr.findAll();
    }

    @Transactional
    public RsData<MilyX> create(MilyUser author, FirstCategory firstCategory, SecondCategory secondCategory, String subject, String body, int milyPoint) {
        LocalDateTime now = LocalDateTime.now();
        int point = author.getMilyPoint();

        System.out.println(author.getUserNickName() + "의 포인트 : " + point);
        System.out.println(author.getUserNickName() + "(이)가 글 작성에 사용한 포인트 : " + milyPoint);

        MilyX mx = MilyX.builder()
                .firstCategory(firstCategory)
                .secondCategory(secondCategory)
                .subject(subject)
                .body(body)
                .author(author)
                .milyPoint(milyPoint)
                .createDate(now)
                .build();

        mx = mxr.save(mx);

        // 글을 작성한 유저의 포인트에서 글 작성에 소모한 포인트만큼 차감
        point -= milyPoint;

        // 차감한 값으로 세팅
        author.setMilyPoint(point);
        mur.save(author);

        System.out.println(author.getUserNickName() + "의 글 작성 후 유저의 포인트 : " + author.getMilyPoint());

        return new RsData<>("S-1", "게시물 생성 완료", mx);
    }

    public Optional<MilyX> findById(long id) {
        return mxr.findById(id);
    }

    @Transactional
    public void updateView(long id, MilyX milyX) {
        MilyX milyx = mxr.findById(id).orElseThrow((() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")));

        milyX.updateView(milyX.getView());
    }
}