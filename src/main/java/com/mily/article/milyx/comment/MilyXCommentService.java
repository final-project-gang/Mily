package com.mily.article.milyx.comment;

import com.mily.article.milyx.MilyX;
import com.mily.base.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MilyXCommentService {
    private final MilyXCommentRepository milyXCommentRepository;

    public RsData<MilyXComment> createComment (MilyX milyX, String body) {
        LocalDateTime now = LocalDateTime.now();

        MilyXComment milyXComment = MilyXComment.builder()
                .milyX(milyX)
                .body(body)
                .createDate(now)
                .build();

        milyXCommentRepository.save(milyXComment);

        return new RsData<>("S-1", "답변 등록 완료", milyXComment);
    }

    public List<MilyXComment> findAll () {
        return milyXCommentRepository.findAll();
    }

    public List<MilyXComment> findByMilyX (MilyX milyX) {
        return milyXCommentRepository.findByMilyX(milyX);
    }
}