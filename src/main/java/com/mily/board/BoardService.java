package com.mily.board;

import com.mily.base.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    public RsData<Board> make(String code, String name, String icon) {
        Board board = Board.builder()
                .code(code)
                .name(name)
                .build();

        boardRepository.save(board);

        return new RsData<>("S-1", name = " 게시판이 생성되었습니다.", board);
    }

    public Optional<Board> findByCode(String boardCode) {
        return boardRepository.findByCode(boardCode);
    }
}