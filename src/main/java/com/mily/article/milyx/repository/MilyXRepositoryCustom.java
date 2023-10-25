package com.mily.article.milyx.repository;

import com.mily.article.milyx.MilyX;
import com.mily.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MilyXRepositoryCustom {
    Page<MilyX> findByKw(Board board, String kwType, String kw, Pageable pageable);
}