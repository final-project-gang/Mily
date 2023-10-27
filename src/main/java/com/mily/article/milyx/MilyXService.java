package com.mily.article.milyx;

import com.mily.article.milyx.category.entity.FirstCategory;
import com.mily.article.milyx.category.entity.SecondCategory;
import com.mily.article.milyx.repository.MilyXRepository;
import com.mily.base.rsData.RsData;
import com.mily.user.MilyUser;
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

    public List<MilyX> getAllPosts() {
        return mxr.findAll();
    }

    public RsData<MilyX> create(MilyUser author, FirstCategory firstCategory, SecondCategory secondCategory, String subject, String body) {
        LocalDateTime now = LocalDateTime.now();

        MilyX mx = MilyX.builder()
                .firstCategory(firstCategory)
                .secondCategory(secondCategory)
                .subject(subject)
                .body(body)
                .author(author)
                .createDate(now)
                .build();

        mx = mxr.save(mx);

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