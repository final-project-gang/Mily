package com.mily.milyx;

import com.mily.milyxcomment.MilyXComment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class MilyX {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    private LocalDateTime createDate;

//    @ManyToOne
//    private Member memberNickName;
//    // 멤버 구현 시 사용 가능
//
//    // 사이버 머니도 구현 필요

    @OneToMany(mappedBy = "milyX", cascade = CascadeType.REMOVE)
    private List<MilyXComment> mxc;
    // 변호사 답변 기능
}